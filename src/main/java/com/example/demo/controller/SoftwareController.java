package com.example.demo.controller;

import com.example.demo.dao.AccountMapper;
import com.example.demo.dao.SoftwareMapper;
import com.example.demo.model.Account;
import com.example.demo.model.Software;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.*;

@Controller
public class SoftwareController {

  @Autowired
  private SoftwareMapper softwareMapper;

  @Autowired
  private AccountMapper accountMapper;

  @RequestMapping(value = "/getDownloadRank", method = RequestMethod.POST)
  @ResponseBody
  public List<Map<String, String>> getDownloadRank() {
    List<Map<String, String>> list = new ArrayList<>();
    List<Software> softwareList = softwareMapper.selectAll();
    Collections.sort(softwareList, new Comparator<Software>() {
      @Override
      public int compare(Software software, Software t1) {
        return software.getDownloadcount() - t1.getDownloadcount();
      }
    });
    for (int i = softwareList.size() - 1; i >= 0; --i) {
      Software software = softwareList.get(i);
      Map<String, String> map = new HashMap<>();
      map.put("name", software.getName());
      map.put("count", software.getDownloadcount().toString());
      list.add(map);
      if (list.size() >= 100) {
        break;
      }
    }
    return list;
  }

  @RequestMapping(value = "/getSoftwareList", method = RequestMethod.GET)
  @ResponseBody
  public List<Map<String, String>> getSoftwareList() {
    try {
      ArrayList<Map<String, String>> result = new ArrayList<>();
      List<Software>softwareList = softwareMapper.selectAll();
      for (int i = 0; i < softwareList.size(); ++i) {
        Software software = softwareList.get(i);
        if (software == null) {
          break;
        }
        Map<String, String> map = new HashMap<String, String>();
        map.put("id", software.getId().toString());
        map.put("name", software.getName());
        map.put("source", software.getSource());
        map.put("introduce", software.getIntroduce());
        map.put("downloadLink", software.getDownloadlink());
        map.put("imgLink", software.getImglink());
        result.add(map);
      }
      return result;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  @RequestMapping(value = "/addDownloadCount", method = RequestMethod.GET)
  public synchronized void addDownloadCount(String id) {
//    System.out.println(id);
    Software result = softwareMapper.selectByPrimaryKey(Integer.valueOf(id));
    result.setDownloadcount(result.getDownloadcount() + 1);
    softwareMapper.updateByPrimaryKey(result);
  }

  @RequestMapping(value = "/fileUpload", method = RequestMethod.GET)
  public String fileUpload() {
    return "fileUpload";
  }

  @RequestMapping("/fileUpload")
  @ResponseBody
  public String fileUpload(String id,
                           String name,
                           String introduce,
                           @RequestParam("fileName") MultipartFile file,
                           @RequestParam("imgName") MultipartFile img,
                           HttpServletRequest request) {
    String accountId = (String)request.getSession().getAttribute("id");
    Account account = accountMapper.selectByPrimaryKey(accountId);
    if(account==null||account.getType()<=1){
      return "用户未登录或权限不足";
    }

    if(softwareMapper.selectByPrimaryKey(Integer.valueOf(id))!=null){
      return "软件ID已存在";
    }

    if (file.isEmpty()||img.isEmpty()) {
      return "文件/图片上传出错";
    }
    //file
    String fileName = file.getOriginalFilename();
    int size = (int) file.getSize();//size b
    System.out.println(fileName + "-->" + size);
    String path = "/home/lu/code/IdeaProjects/downloardcenter/src/main/resources/static/software";
    File dest = new File(path + "/" + fileName);
    if (!dest.getParentFile().exists()) { //判断文件父目录是否存在
      dest.getParentFile().mkdir();
    }
    //img
    String imgName = img.getOriginalFilename();
    int imgSize = (int) img.getSize();//size b
    System.out.println(imgName + "-->" + imgSize);
    String imgPath = "/home/lu/code/IdeaProjects/downloardcenter/src/main/resources/static/softwareImg";
    File imgDest = new File(imgPath + "/" + imgName);
    if (!imgDest.getParentFile().exists()) { //判断文件父目录是否存在
      imgDest.getParentFile().mkdir();
    }

    //保存
    try {
      file.transferTo(dest); //保存文件
      img.transferTo(imgDest); //保存文件

      softwareMapper.insert(new Software(Integer.valueOf(id),
          name,
          accountId,
          introduce,
          "software/"+fileName,
          "softwareImg/"+imgName,
          0
          ));

      return "软件上传成功";
    } catch (IllegalStateException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
      return "软件上传失败";
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
      return "软件上传失败";
    }
  }

  @RequestMapping(value = "/managerSoftware", method = RequestMethod.GET)
  public String managerSoftware(){
    return "managerSoftware";
  }

  @RequestMapping(value = "/getManagerSoftwareList", method = RequestMethod.POST)
  @ResponseBody
  public List<Map<String,String>>getManagerSoftwareList(HttpServletRequest request){
    HttpSession session = request.getSession();
    List<Map<String,String>>list = new ArrayList<>();
    String accountId = (String)session.getAttribute("id");
    if(accountId==null){
      return list;
    }
    int type = accountMapper.selectByPrimaryKey(accountId).getType();
    List<Software>softwareList = null;
    if(type==3){ // admin
      softwareList = softwareMapper.selectAll();
    }
    if(type==2){
      softwareList = softwareMapper.selectBySourceKey(accountId);
    }
    for(int i = 0; i<softwareList.size();++i){
      Software software = softwareList.get(i);
      Map<String,String>map = new HashMap<>();
      map.put("id", software.getId().toString());
      map.put("name", software.getName());
      map.put("source", software.getSource());
      map.put("introduce", software.getIntroduce());
      map.put("downloadLink", software.getDownloadlink());
      map.put("imgLink", software.getImglink());
      list.add(map);
    }
    return list;
  }

  private boolean deleteFile(String fileName){
    File file = new File(fileName);
    if(file.exists()){
      if(file.isFile()){
        return file.delete();
      }
    }
    return false;
  }

  @RequestMapping(value = "/underCarriage", method = RequestMethod.GET)
  @ResponseBody
  public String underCarriage(String softwareId){
    int id=Integer.valueOf(softwareId);
    Software software = softwareMapper.selectByPrimaryKey(id);
    deleteFile(software.getDownloadlink());
    deleteFile(software.getImglink());
    if(softwareMapper.deleteByPrimaryKey(id)>0){
      return "success";
    }
    return "failed";
  }
}