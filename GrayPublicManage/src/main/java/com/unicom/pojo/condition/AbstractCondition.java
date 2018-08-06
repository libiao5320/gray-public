package com.unicom.pojo.condition;

import com.unicom.pojo.condition.index.AbstractIndex;

import com.unicom.constant.LogicSymbol;
import com.unicom.model.BaseModel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author
 * @create 2018-05-30 10:19
 **/
public class AbstractCondition<T extends BaseModel> implements ICondition<T> {

  private LogicSymbol logicSymbol;

  private Integer state = 1;
  private String conditionId;
  private String conditionName;
  private String ruleId;
  private Integer crtUser;
  private Integer uptUser;
  private String crtTime;
  private String uptTime;
  private String strategy;


  public LogicSymbol getLogicSymbol() {
    return logicSymbol;
  }

  public void setLogicSymbol(LogicSymbol logicSymbol) {
    this.logicSymbol = logicSymbol;
  }

  public String getRuleId() {
    return ruleId;
  }

  public void setRuleId(String ruleId) {
    this.ruleId = ruleId;
  }

  public Integer getState() {
    return state;
  }

  public void setState(Integer state) {
    this.state = state;
  }

  public String getConditionId() {
    return conditionId;
  }

  public void setConditionId(String conditionId) {
    this.conditionId = conditionId;
  }

  public Integer getCrtUser() {
    return crtUser;
  }

  public void setCrtUser(Integer crtUser) {
    this.crtUser = crtUser;
  }

  public Integer getUptUser() {
    return uptUser;
  }

  public void setUptUser(Integer uptUser) {
    this.uptUser = uptUser;
  }

  public String getCrtTime() {
    return crtTime;
  }

  public void setCrtTime(String crtTime) {
    this.crtTime = crtTime;
  }

  public String getUptTime() {
    return uptTime;
  }

  public void setUptTime(String uptTime) {
    this.uptTime = uptTime;
  }

  protected List<AbstractIndex> indexs = null;


  public void addAllIndex(Collection<AbstractIndex> indexs) {
    if (this.indexs == null) {
      this.indexs = new ArrayList<>();
    }
    this.indexs.addAll(indexs);
  }

  public void addIndex(AbstractIndex index) {
    if (this.indexs == null) {
      this.indexs = new ArrayList<>();
    }
    this.indexs.add(index);
  }

  public List<AbstractIndex> getIndexs() {
    return indexs;
  }

  public void setIndexs(List<AbstractIndex> indexs) {
    this.indexs = indexs;
  }


  public String getConditionName() {
    return conditionName;
  }

  public void setConditionName(String conditionName) {
    this.conditionName = conditionName;
  }

  public String getStrategy() {
    return strategy;
  }

  public void setStrategy(String strategy) {
    this.strategy = strategy;
  }
}
