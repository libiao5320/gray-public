package com.unicom.mapper;

import com.unicom.mapper.base.BaseMapper;
import com.unicom.pojo.rule.RuleResult;

public interface RuleResultMapper<RuleResult> extends BaseMapper {


  public RuleResult getByRuleId(String ruleId);


}
