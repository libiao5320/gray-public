package com.unicom.ctrl;

import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Objects;
import com.google.common.base.Optional;
import com.unicom.biz.loginaccount.ILoginAccountBiz;
import com.unicom.command.ServiceCommandInvoker;
import com.unicom.command.rx.BaseAbstractCommand;
import com.unicom.constant.Constants;
import com.unicom.pojo.manager.LoginAccount;
import com.unicom.pojo.manager.Manager;
import com.unicom.util.AESUtil;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author
 * @create 2018-07-04 15:38
 **/
@Controller
public class LoginCtrl {

  @Autowired
  private ILoginAccountBiz loginAccountBiz;


  @RequestMapping("/login")
  public String login() {
    return "login";
  }


  @RequestMapping("/nosession")
  public String nosession() {
    return "nosession";
  }


  @RequestMapping(value = "/validateLogin", method = RequestMethod.POST)
  public ModelAndView validateLogin(HttpServletRequest request,
      @RequestParam String managerLoginName, @RequestParam String managerLoginPwd) {

    ModelAndView modelAndView = new ModelAndView();


    LoginAccount loginAccount = null;

    boolean flag = false;
    String loginMsg = null;

    loginAccount = new ServiceCommandInvoker<LoginAccount>().execute(
        new BaseAbstractCommand<LoginAccount>(() -> {
          return loginAccountBiz.getByLoginName(managerLoginName);
        }, () -> {
          LoginAccount _tmp = new LoginAccount();
          _tmp.setRealName("error");
          return _tmp;
        }));

    if (Optional.fromNullable(loginAccount).isPresent()) {

      if (Objects.equal(AESUtil.encrypt(managerLoginPwd), loginAccount.getLoginPassword())) {
        flag = true;
      } else if (Objects.equal("error", loginAccount.getRealName())) {
        loginMsg = "登陆失败，系统异常";
      } else {
        loginMsg = "登陆失败，登陆密码错误";
      }
    } else {
      loginMsg = "登陆失败，登陆账户不存在";
    }

    if (flag) {
      request.getSession().setAttribute(Constants.SESSION_USERINFO,
          new Manager());

      request.setAttribute(Constants.SESSION_USERINFO, loginAccount);
      modelAndView.setViewName("redirect:index");
    } else {
      modelAndView.setViewName("redirect:login");
      modelAndView.addObject("loginMsg", loginMsg);
    }

    return modelAndView;
  }


}
