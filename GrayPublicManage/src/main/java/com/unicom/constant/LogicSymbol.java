package com.unicom.constant;

public enum LogicSymbol {


  ANY("any"),
  ALL("all");

  private String op;

  LogicSymbol(String op) {
    this.op = op;
  }
}
