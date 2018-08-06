package com.unicom.rest;

import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Optional;

import com.unicom.biz.rule.impl.RuleBizImpl;
import com.unicom.command.ServiceCommandInvoker;
import com.unicom.command.rx.BaseAbstractCommand;

import com.unicom.model.UserModel;
import com.unicom.pojo.rule.AbstractBaseRule;
import com.unicom.pojo.rule.IRule;

import com.unicom.pojo.rule.UserRule;
import com.unicom.util.JsonResultUtil;
import com.unicom.valiedate.AbstractValidateFactory;
import com.unicom.valiedate.IValidate;
import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author
 * @create 2018-05-08 11:34
 **/

@RestController
@RequestMapping("/graypublic")
public class GrayPublicRest {

  @Autowired
  private RuleBizImpl ruleBiz;
  private AntPathMatcher antPathMatcher = new AntPathMatcher();


  @RequestMapping(value = "/matchGray", method = RequestMethod.GET)
  @ResponseBody
  public JSONObject matchGray(@RequestParam("requestURI") String requestURL) {


    List<IRule> ruleList  = new ServiceCommandInvoker<List>().execute(
        new BaseAbstractCommand<List>(() -> {
          List l = ruleBiz.fetchAll();
          return l;
        }, () -> {
          return Collections.emptyList();
        }));

    IRule result = ruleList.stream().filter((_rule) -> {

      boolean flag = false;
      AbstractBaseRule rule = (AbstractBaseRule) _rule;
      if (Optional.fromNullable(rule.getUrl()).isPresent()) {
        if (antPathMatcher.match(rule.getUrl(), requestURL)) {
          flag = true;
        }
      } else {
        return flag;
      }
      return flag;
    }).findFirst().orElse(null);

    if (Optional.fromNullable(result).isPresent()) {
      return JsonResultUtil.success(result);
    } else {
      return JsonResultUtil.success("");
    }

  }


  @RequestMapping(value = "/validateuserstrategy", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
  @ResponseBody
  public JSONObject validate(@RequestBody UserModel userModel, @RequestParam String ruleId) {

    UserRule rule = (UserRule) ruleBiz.get(ruleId);

    IValidate validate = AbstractValidateFactory.getValidate(rule);

    return JsonResultUtil.success(validate.validate(rule, userModel));

  }


}
