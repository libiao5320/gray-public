package com.unicom.pojo.other;

import java.io.Serializable;

/**
 * @author
 * @create 2018-07-03 16:49
 **/
public class OpLog implements Serializable {


  private String id;
  private String remoteIp;
  private String opUser;
  private String opClass;
  private String opMethod;
  private String opParam;
  private String crTime;


  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getRemoteIp() {
    return remoteIp;
  }

  public void setRemoteIp(String remoteIp) {
    this.remoteIp = remoteIp;
  }

  public String getOpUser() {
    return opUser;
  }

  public void setOpUser(String opUser) {
    this.opUser = opUser;
  }

  public String getOpClass() {
    return opClass;
  }

  public void setOpClass(String opClass) {
    this.opClass = opClass;
  }

  public String getOpMethod() {
    return opMethod;
  }

  public void setOpMethod(String opMethod) {
    this.opMethod = opMethod;
  }

  public String getOpParam() {
    return opParam;
  }

  public void setOpParam(String opParam) {
    this.opParam = opParam;
  }



  public String getCrTime() {
    return crTime;
  }

  public void setCrTime(String crTime) {
    this.crTime = crTime;
  }
}
