package com.unicom.valiedate;

import com.unicom.pojo.condition.AbstractCondition;

import com.unicom.pojo.rule.AbstractBaseRule;
import com.unicom.pojo.rule.RuleResult;


/**
 * @createUser Lee
 * @createTime 2018/7/10 11:26
 * @Package UserValidate.java
 * @ClassName UserValidate
 * @ClassDesc UserValidate 校验类 ，用于校验UserModel的灰度策略
**/
public class UserValidate extends AbstractValidate {

  private static final String TAG = "UserValidate";
  private static UserValidate instance = null;

  private UserValidate() {
    //no instance
  }

  public static UserValidate getInstance() {
    return instance;
  }


  static {
    instance = new UserValidate();
  }

}
