package com.unicom.pojo.rule;

import com.unicom.constant.GrayType;



/**
 * @author
 * @create 2018-05-30 10:33
 **/
public class RuleResult {


  private String resultId;
  private String ruleId;
  private String grayValue;
  private Integer state;
  private boolean isMatch;
  private GrayType graytype;


  private Integer crtUser;
  private Integer uptUser;
  private String crtTime;
  private String uptTime;



  public RuleResult() {
  }

  public String getResultId() {
    return resultId;
  }

  public void setResultId(String resultId) {
    this.resultId = resultId;
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

  public boolean isMatch() {
    return isMatch;
  }

  public void setMatch(boolean match) {
    isMatch = match;
  }

  public String getGrayValue() {
    return grayValue;
  }

  public void setGrayValue(String grayValue) {
    this.grayValue = grayValue;
  }

  public GrayType getGraytype() {
    return graytype;
  }

  public void setGraytype(GrayType graytype) {
    this.graytype = graytype;
  }
}
