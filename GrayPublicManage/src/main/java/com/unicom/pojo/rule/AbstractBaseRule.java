package com.unicom.pojo.rule;

import com.unicom.pojo.condition.AbstractCondition;
import com.unicom.pojo.condition.ICondition;
import java.util.List;

/**
 * @author
 * @create 2018-06-15 16:26
 **/
public class AbstractBaseRule<T extends AbstractCondition> implements IRule {

  private Integer state = 1;
  private String ruleId;
  private Integer crtUser;
  private Integer uptUser;
  private String crtTime;
  private String uptTime;
  private List<T> conditions;
  private RuleResult ruleResult;
  private String url;




  public List<T> getConditions() {
    return conditions;
  }

  public void setConditions(List<T> conditions) {
    this.conditions = conditions;
  }

  public Integer getState() {
    return state;
  }

  public void setState(Integer state) {
    this.state = state;
  }

  public String getRuleId() {
    return ruleId;
  }

  public void setRuleId(String ruleId) {
    this.ruleId = ruleId;
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

  public RuleResult getRuleResult() {
    return ruleResult;
  }

  public void setRuleResult(RuleResult ruleResult) {
    this.ruleResult = ruleResult;
  }

  public AbstractBaseRule() {
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }
}
