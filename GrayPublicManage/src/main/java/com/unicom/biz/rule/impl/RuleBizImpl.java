package com.unicom.biz.rule.impl;

import com.unicom.biz.base.AbstractBaseBiz;
import com.unicom.biz.rule.IRuleBiz;

import com.unicom.pojo.condition.AbstractCondition;
import com.unicom.pojo.condition.ICondition;
import com.unicom.pojo.condition.index.AbstractIndex;
import com.unicom.pojo.condition.index.IIndex;
import com.unicom.pojo.condition.rel.ConditionIndexRel;
import com.unicom.mapper.ConditionIndexRelMapper;
import com.unicom.mapper.ConditionMapper;
import com.unicom.mapper.IndexMapper;
import com.unicom.mapper.RuleMapper;
import com.unicom.mapper.RuleResultMapper;
import com.unicom.pojo.rule.AbstractBaseRule;
import com.unicom.pojo.rule.IRule;
import com.unicom.pojo.rule.RuleResult;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author
 * @create 2018-05-30 16:37
 **/
@Service
public class RuleBizImpl extends AbstractBaseBiz<IRule> implements IRuleBiz {

  @Autowired
  private RuleMapper ruleMapper;
  @Autowired
  private ConditionMapper conditionMapper;
  @Autowired
  private IndexMapper indexMapper;
  @Autowired
  private ConditionIndexRelMapper conditionIndexRelMapper;


  @Autowired
  private RuleResultMapper resultMapper;



  @Autowired
  public void init(RuleMapper ruleMapper) {
    super.setBaseMapper(ruleMapper);
  }


  @Override
  public boolean update(IRule rule)
  {
    boolean flag = true;


    RuleResult ruleResult =  ((AbstractBaseRule)rule).getRuleResult();


    flag = Optional.ofNullable(ruleMapper.update(rule)).get() > 0;
    flag = Optional.ofNullable(resultMapper.update(ruleResult)).get() > 0;


    return flag;
  }

  @Override
  public boolean del(String[] ids) {
    return Optional.ofNullable(ruleMapper.del(ids)).get() > 0;
  }

  @Override
  public boolean add(IRule rule) {


    boolean flag = true;
    //新增 Rule 成功 ，在添加 Condtion
    if (Optional.ofNullable(ruleMapper.add(rule)).get() > 0) {
      String ruleId = ((AbstractBaseRule) rule).getRuleId();
      RuleResult  ruleResult = ((AbstractBaseRule) rule).getRuleResult();

      ruleResult.setRuleId(ruleId);
      //新增RuleResult
      resultMapper.add(ruleResult);



      List<ICondition> conditions = Optional.ofNullable(((AbstractBaseRule) rule).getConditions())
          .orElse(new ArrayList());
      if (conditions.size() > 0) {
          flag = conditions.stream().allMatch((condtion) -> {
          AbstractCondition _condition = ((AbstractCondition) condtion);
          _condition.setRuleId(ruleId);
          //添加Condtion 新增成功的情况下 再添加关系
          if (com.google.common.base.Optional.fromNullable(conditionMapper.add(_condition)).get() > 0) {
            String _conditionId = _condition.getConditionId();

            List<IIndex> iIdexList = _condition.getIndexs();

            if (com.google.common.base.Optional.fromNullable(iIdexList).isPresent() )
            return iIdexList.stream().allMatch((_index) -> {
              indexMapper.add(_index);
              ConditionIndexRel conditionIndexRel = new ConditionIndexRel();
              conditionIndexRel.setConditionId(_conditionId);
              conditionIndexRel.setIndexId(((AbstractIndex) _index).getIndexId());
              return conditionIndexRelMapper.add(conditionIndexRel) > 0;
            });
            else
              return true;
          }
          else
            return false;
        });
      }

    }
    else {
      flag = false;
    }


    return flag;
  }


}
