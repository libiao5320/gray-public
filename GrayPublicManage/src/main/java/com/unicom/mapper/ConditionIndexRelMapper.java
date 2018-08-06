package com.unicom.mapper;

import com.unicom.pojo.condition.rel.ConditionIndexRel;
import com.unicom.pojo.rule.IRule;
import org.springframework.stereotype.Repository;

@Repository
public interface ConditionIndexRelMapper {

  public int add(ConditionIndexRel conditionIndexRel);


}
