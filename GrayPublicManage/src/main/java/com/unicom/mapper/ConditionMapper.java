package com.unicom.mapper;


import com.unicom.pojo.condition.ICondition;
import com.unicom.mapper.base.BaseMapper;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface ConditionMapper<ICondition> extends BaseMapper {


  public List<ICondition> getByRuleId(String ruleId);

}
