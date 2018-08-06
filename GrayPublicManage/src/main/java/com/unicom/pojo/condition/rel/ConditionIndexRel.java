package com.unicom.pojo.condition.rel;

/**
 * @author
 * @create 2018-06-19 15:25
 **/
public class ConditionIndexRel {

  private String relId;
  private String conditionId;
  private String indexId;
  private Integer crtUser;
  private Integer uptUser;
  private String crtTime;
  private String uptTime;
  private Integer state;

  public String getRelId() {
    return relId;
  }

  public void setRelId(String relId) {
    this.relId = relId;
  }

  public String getConditionId() {
    return conditionId;
  }

  public void setConditionId(String conditionId) {
    this.conditionId = conditionId;
  }

  public String getIndexId() {
    return indexId;
  }

  public void setIndexId(String indexId) {
    this.indexId = indexId;
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

  public Integer getState() {
    return state;
  }

  public void setState(Integer state) {
    this.state = state;
  }
}
