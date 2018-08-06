package com.unicom.ctrl;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author
 * @create 2018-05-29 11:08
 **/

@Controller
public class IndexCtrl {


  @RequestMapping("/index")
  public String index() {
    return "index";
  }



  @RequestMapping("/top")
  public String top() {
    return "/base/top";
  }

  @RequestMapping("/left")
  public String left() {
    return "/base/left";
  }

  @RequestMapping("/bottom")
  public String bottom() {
    return "bottom";
  }


}
