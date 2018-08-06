package com.unicom.model;

import com.unicom.annotion.FieldDesc;
import com.unicom.annotion.ModelDesc;

/**
 * @author
 * @create 2018-05-08 9:57
 **/
@ModelDesc("用户模型")
public class UserModel extends BaseModel {

  @FieldDesc("用户id")
  private String userId;
  @FieldDesc("用户手机号码")
  private String mobilePhone;
  @FieldDesc("用户标签")
  private String tag;


  public UserModel(){}


  public UserModel(String userId){
    this.userId = userId;
  }

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public String getMobilePhone() {
    return mobilePhone;
  }

  public void setMobilePhone(String mobilePhone) {
    this.mobilePhone = mobilePhone;
  }

  public String getTag() {
    return tag;
  }

  public void setTag(String tag) {
    this.tag = tag;
  }
}
