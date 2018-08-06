package com.unicom.valiedate;

import com.unicom.pojo.rule.IRule;
import com.unicom.pojo.rule.UserRule;

/**
 * @createUser Lee
 * @createTime 2018/7/10 11:28
 * @Package AbstractValidateFactory.java
 * @ClassName AbstractValidateFactory
 * @ClassDesc Validate工厂类
**/
public class AbstractValidateFactory {

  // FIXME: 2018/7/10
  private static final String TAG = "AbstractValidateFactory";
  
  
  
  private static IValidate validate;

  public static IValidate getValidate(IRule iRule) {

    if (iRule instanceof UserRule) {
      validate = UserValidate.getInstance();
    }

    return validate;
  }

}
