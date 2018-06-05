package com.example.demo.controller;

import com.example.demo.dao.SoftwareMapper;
import com.example.demo.model.Software;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.print.DocFlavor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class IndexController {

  @Autowired
  private SoftwareMapper softwareMapper;

  @RequestMapping(value = "/index", method = RequestMethod.GET)
  public String index() {
    return "index";
  }

  @RequestMapping(value = "/downloadLog", method = RequestMethod.GET)
  public String downloadLog() {
    return "downloadLog";
  }



  @RequestMapping(value = "/downloadRank", method = RequestMethod.GET)
  public String downloadRank(){
    return "downloadRank";
  }
}