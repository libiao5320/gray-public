package com.unicom.valiedate;


import com.unicom.model.BaseModel;
import com.unicom.pojo.rule.IRule;
import com.unicom.pojo.rule.RuleResult;

public interface IValidate<T extends IRule , D extends BaseModel> {
  boolean validate(T  t , D d );


}
