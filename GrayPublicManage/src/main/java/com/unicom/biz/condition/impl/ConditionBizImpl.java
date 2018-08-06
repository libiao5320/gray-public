package com.unicom.biz.condition.impl;

import com.unicom.biz.base.AbstractBaseBiz;
import com.unicom.biz.condition.IConditionBiz;
import com.unicom.pojo.condition.AbstractCondition;
import com.unicom.mapper.ConditionMapper;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author
 * @create 2018-06-28 10:13
 **/
@Service
public class ConditionBizImpl<AbstractCondition> extends AbstractBaseBiz implements IConditionBiz {

  @Autowired
  public void init(ConditionMapper conditionMapper) {
    super.setBaseMapper(conditionMapper);
  }


  @Override
  public List fetchConditionByRuleId(String ruleId) {
    return ((ConditionMapper)super.getBaseMapper()).getByRuleId(ruleId);
  }


}
