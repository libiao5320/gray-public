package com.unicom.pojo.manager;

import java.io.Serializable;

/**
 * @author
 * @create 2018-07-16 14:11
 **/
public class LoginAccount implements Serializable {

  private String id;
  private String realName;
  private String crtTime;
  private String uptTime;
  private String loginAccount;
  private String loginPassword;


  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getRealName() {
    return realName;
  }

  public void setRealName(String realName) {
    this.realName = realName;
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

  public String getLoginAccount() {
    return loginAccount;
  }

  public void setLoginAccount(String loginAccount) {
    this.loginAccount = loginAccount;
  }

  public String getLoginPassword() {
    return loginPassword;
  }

  public void setLoginPassword(String loginPassword) {
    this.loginPassword = loginPassword;
  }
}
