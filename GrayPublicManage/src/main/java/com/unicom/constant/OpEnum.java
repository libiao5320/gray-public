package com.unicom.constant;

public enum OpEnum {


  _EQ("等于","="),
  _NEQ("不等于","!="),
  _GT("大于",">"),
  _LT("小于","<"),
  _GTEQ("大于等于",">="),
  _LTEQ("小于等于","<="),
  _IN("包含","in"),
  _NIN("不包含","not in");

  private String value;
  private String op;


  private OpEnum(String value,String op) {
    this.value = value;
    this.op = op;
  }

  public String value() {
    return this.op;
  }
}
