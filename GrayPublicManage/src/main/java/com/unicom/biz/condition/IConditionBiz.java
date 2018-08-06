package com.unicom.biz.condition;

import com.unicom.biz.base.BaseBiz;
import com.unicom.pojo.condition.AbstractCondition;
import java.util.List;

/**
 * @author
 * @create 2018-06-28 10:12
 **/
public interface IConditionBiz<T> extends BaseBiz<T> {

  public List fetchConditionByRuleId(String ruleId);
}
