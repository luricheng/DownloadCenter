package com.example.demo.controller;


import com.example.demo.dao.AccountMapper;
import com.example.demo.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.rmi.server.RMIClassLoader;
import java.util.HashMap;
import java.util.Map;

@Controller
public class AccountController {

  @Autowired
  private AccountMapper accountMapper;

  @RequestMapping(value = "/login", method = RequestMethod.GET)
  public String login() {
    return "login";
  }

  @RequestMapping(value = "/loginSubmit", method = RequestMethod.POST)
  public ModelAndView loginSubmit(String id, String password, HttpServletRequest request) {
    ModelAndView m = new ModelAndView();
    try {
      HttpSession session = request.getSession();
      session.setAttribute("id", id);
      Account result = accountMapper.selectByPrimaryKey(id);
      if (result != null && result.getPassword().equals(password)) {
        m.setViewName("/index");
        m.addObject("msg", id);
      } else {
        m.setViewName("login");
        m.addObject("msg", "账号或密码错误!");
      }
      return m;
    } catch (Exception e) {
      m.addObject("msg", "登录失败");
      m.setViewName("login");
      return m;
    }
  }

  @RequestMapping(value = "/register", method = RequestMethod.GET)
  public String register() {
    return "register";
  }

  @RequestMapping(value = "/registerSubmit", method = RequestMethod.POST)
  public ModelAndView register(String id, String password) {
    ModelAndView m = new ModelAndView();
    String msg = null;
    int rows = 0;
    try {
//      rows=accountMapper.insert(new Account(id,password,1, null,0,null));
      rows = accountMapper.insert(new Account(id, password, 1));
    } catch (Exception e) {
      System.out.println("插入失败！");
    }
    if (rows != 0) {
      m.setViewName("login");
      msg = id + " 注册成功！";
      System.out.println("插入成功！");
    } else {
      m.setViewName("register");
      msg = id + " 注册失败！";
    }
    m.addObject("msg", msg);
    return m;
  }

  @RequestMapping(value = "/userInfo", method = RequestMethod.GET)
  public ModelAndView userInfo(HttpServletRequest request) {
    HttpSession session = request.getSession();
    String id = (String) session.getAttribute("id");
    Account result = accountMapper.selectByPrimaryKey(id);
    session.setAttribute("id", result.getId());
    switch (result.getType()) {
      case 1:
        session.setAttribute("type", "普通用户");
        break;
      case 2:
        session.setAttribute("type", "开发者");
        break;
      case 3:
        session.setAttribute("type", "管理员");
        break;
    }

    session.setAttribute("nick", result.getNick());
    session.setAttribute("age", result.getAge());
    session.setAttribute("mail", result.getMail());
    ModelAndView m = new ModelAndView();
    m.setViewName("userInfo");
    return m;
  }

  @RequestMapping(value = "/changeInfo", method = RequestMethod.GET)
  @ResponseBody
  public String changInfo(String nick, String age, String mail, HttpServletRequest request) {
    try {
      HttpSession session = request.getSession();
      String id = (String) session.getAttribute("id");
      Account result = accountMapper.selectByPrimaryKey(id);
      result.setNick(nick);
      result.setAge(Integer.valueOf(age));
      result.setMail(mail);
      accountMapper.updateByPrimaryKey(result);
      session.setAttribute("nick", nick);
      session.setAttribute("age", Integer.valueOf(age));
      session.setAttribute("mail", mail);
    } catch (Exception e) {
      System.out.println("info update failed");
      return "更新失败";
    }
    System.out.println("info update success");
    return "更新成功";
  }

  @RequestMapping(value = "/getInfo", method = RequestMethod.GET)
  @ResponseBody
  public Map<String, String> getInfo(HttpServletRequest request) {
    try {
      HttpSession session = request.getSession();
      String id = (String) session.getAttribute("id");
      Account result = accountMapper.selectByPrimaryKey(id);
      Map<String, String> map = new HashMap<>();
      map.put("id", result.getId());
      switch (result.getType()) {
        case 1:
          map.put("type", "普通用户");
          break;
        case 2:
          map.put("type", "开发者");
          break;
        case 3:
          map.put("type", "管理员");
          break;
      }
      map.put("nick", result.getNick());
      map.put("age", result.getAge().toString());
      map.put("mail", result.getMail());
      return map;
    } catch (Exception e) {
      return null;
    }
  }

  @RequestMapping(value = "/changePassword", method = RequestMethod.GET)
  public String changePassword() {
    return "changePassword";
  }

  @RequestMapping(value = "/changePasswordSubmit", method = RequestMethod.POST)
  public ModelAndView changePasswordSubmit(String id, String oldPassword, String newPassword) {
    ModelAndView m = new ModelAndView();
    String msg = null;
    Account result = accountMapper.selectByPrimaryKey(id);
    if (result != null && result.getPassword().equals(oldPassword)) {
      result.setPassword(newPassword);
      accountMapper.updateByPrimaryKey(result);
      msg = "修改密码成功!";
      m.setViewName("login");
    } else {
      msg = "账号或密码不正确!";
      m.setViewName("changePassword");
    }
    m.addObject("msg", msg);
    return m;
  }

  @RequestMapping(value = "/logout", method = RequestMethod.GET)
  public ModelAndView logout(HttpServletRequest request) {
    request.getSession().removeAttribute("id");
    ModelAndView m = new ModelAndView();
    m.setViewName("index");
    return m;
  }

  @RequestMapping(value = "/secureQuestion", method = RequestMethod.GET)
  public String secureQuestion() {
    return "secureQuestion";
  }

  @RequestMapping(value = "/updateSecureQuestion", method = RequestMethod.POST)
  @ResponseBody
  public String updateSecureQuestion(String question, String answer, HttpServletRequest request) {
    HttpSession session = request.getSession();
    try {
      String id = (String) session.getAttribute("id");
      Account result = accountMapper.selectByPrimaryKey(id);
      result.setQuestion(question);
      result.setAnswer(answer);
      accountMapper.updateByPrimaryKey(result);
      return "更新成功";
    } catch (Exception e) {
      e.printStackTrace();
    }
    return "更新失败";
  }

  @RequestMapping(value = "/getSecureQuestion", method = RequestMethod.POST)
  @ResponseBody
  public Map<String, String> getSecureQuestion(String inputId) {
    Map<String, String> map = new HashMap<>();
    Account result = accountMapper.selectByPrimaryKey(inputId);
    if(result==null){
      map.put("idNotFound","true");
      return map;
    }
    map.put("idNotFound","false");
    try {
      map.put("question", result.getQuestion());
      map.put("answer", result.getAnswer());
      return map;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  @RequestMapping(value = "/retrievePassword", method = RequestMethod.GET)
  public String retrievePassword(){
    return "retrievePassword";
  }

  @RequestMapping(value = "/updatePassword", method = RequestMethod.POST)
  @ResponseBody
  public String updatePassword(String id,String password,HttpServletRequest request){
    Account result = accountMapper.selectByPrimaryKey(id);
    if(result==null){
      return "用户不存在";
    }
    result.setPassword(password);
    try {
      accountMapper.updateByPrimaryKey(result);
      HttpSession session = request.getSession();
      session.setAttribute("id",id);
      return "success";
    }catch (Exception e){
      e.printStackTrace();
    }
    return "更新密码失败";
  }
}