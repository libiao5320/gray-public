package com.unicom.pojo.manager;

import java.io.Serializable;
import java.util.List;

public class Manager implements Serializable {


  private Long mamangerId;
  private String managerName;

  private String managerCt;
  private String managerRoleName;
  private String managerRemark;
  private String managerPhone;
  private String managerQQ;
  private String managerMail;
  private String managerLoginName;
  private String managerLoginPwd;


  public Long getMamangerId() {
    return mamangerId;
  }

  public void setMamangerId(Long mamangerId) {
    this.mamangerId = mamangerId;
  }

  public String getManagerName() {
    return managerName;
  }

  public void setManagerName(String managerName) {
    this.managerName = managerName;
  }


  public String getManagerCt() {
    return managerCt;
  }

  public void setManagerCt(String managerCt) {
    this.managerCt = managerCt;
  }

  public String getManagerRoleName() {
    return managerRoleName;
  }

  public void setManagerRoleName(String managerRoleName) {
    this.managerRoleName = managerRoleName;
  }

  public String getManagerRemark() {
    return managerRemark;
  }

  public void setManagerRemark(String managerRemark) {
    this.managerRemark = managerRemark;
  }

  public String getManagerPhone() {
    return managerPhone;
  }

  public void setManagerPhone(String managerPhone) {
    this.managerPhone = managerPhone;
  }

  public String getManagerQQ() {
    return managerQQ;
  }

  public void setManagerQQ(String managerQQ) {
    this.managerQQ = managerQQ;
  }

  public String getManagerMail() {
    return managerMail;
  }

  public void setManagerMail(String managerMail) {
    this.managerMail = managerMail;
  }

  public String getManagerLoginName() {
    return managerLoginName;
  }

  public void setManagerLoginName(String managerLoginName) {
    this.managerLoginName = managerLoginName;
  }

  public String getManagerLoginPwd() {
    return managerLoginPwd;
  }

  public void setManagerLoginPwd(String managerLoginPwd) {
    this.managerLoginPwd = managerLoginPwd;
  }


}
