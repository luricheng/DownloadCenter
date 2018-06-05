package com.example.demo.controller;

import com.example.demo.dao.DownloadlogMapper;
import com.example.demo.dao.SoftwareMapper;
import com.example.demo.model.Downloadlog;
import com.example.demo.model.DownloadlogKey;
import com.example.demo.model.Software;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
public class DownloadLogController {

  @Autowired
  private DownloadlogMapper downloadlogMapper;
  @Autowired
  private SoftwareMapper softwareMapper;

  @RequestMapping(value = "/getDownloadLog", method = RequestMethod.GET)
  @ResponseBody
  public List<Map<String,String>> getDownloadLog(HttpServletRequest request){
    List<Map<String,String>>list = new ArrayList<>();
    //获取用户id
    HttpSession session = request.getSession();
    String accountId = (String)session.getAttribute("id");
    if(accountId==null){
      return list;
    }
    //获取用户下载记录
    List<Downloadlog>result = downloadlogMapper.selectByAccountId(new Downloadlog(0,accountId,new Date()));
    for(int i=0;i<result.size();++i){
      Downloadlog downloadlog = result.get(i);
      Map<String,String>map = new HashMap<>();
      //获取下载记录中的软件信息
      Software software = softwareMapper.selectByPrimaryKey(downloadlog.getSoftwareid());
      map.put("id",software.getId().toString());
      map.put("name", software.getName());
      map.put("source", software.getSource());
      map.put("introduce", software.getIntroduce());
      map.put("downloadLink", software.getDownloadlink());
      map.put("imgLink", software.getImglink());
      map.put("time",downloadlog.getTime().toString());
      list.add(map);
    }
    return list;
  }

  @RequestMapping(value = "/addDownloadLog", method = RequestMethod.GET)
  public void addDownloadLog(String softwareId, String accountId){
    Downloadlog downloadlog = downloadlogMapper.selectByPrimaryKey(new DownloadlogKey(Integer.valueOf(softwareId),accountId));
    if(downloadlog==null) {
      downloadlogMapper.insert(new Downloadlog(Integer.valueOf(softwareId), accountId, new Date()));
    }
    else{
      downloadlog.setTime(new Date());
      downloadlogMapper.updateByPrimaryKey(downloadlog);
    }
  }

  @RequestMapping(value = "/rmDownloadLog", method = RequestMethod.GET)
  public void rmDownloadLog(HttpServletRequest request){
    HttpSession session = request.getSession();
    String id = (String)session.getAttribute("id");
    if(null!=id){
      downloadlogMapper.deleteByAccountId(new DownloadlogKey(0,id));
    }
  }
}
