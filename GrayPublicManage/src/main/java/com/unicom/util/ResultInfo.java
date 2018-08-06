package com.unicom.util;

import java.io.Serializable;

/**
 * @author
 * @create 2018-07-06 11:04
 **/
public class ResultInfo implements Serializable {

  private String msg;
  private String code;
  private Object value;


  public ResultInfo(String code, String msg, Object value) {
    this.code = code;
    this.msg = msg;
    this.value = value;
  }

  public String getMsg() {
    return msg;
  }

  public void setMsg(String msg) {
    this.msg = msg;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public Object getValue() {
    return value;
  }

  public void setValue(Object value) {
    this.value = value;
  }
}
