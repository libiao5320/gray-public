package com.unicom.exception;

/**
 * @author
 * @create 2018-07-06 10:50
 **/
public class LogicBizException extends Exception {


  private String errorDesc;
  private String errorCode;
  private Throwable throwable;


  public LogicBizException(Throwable throwable) {
    super(throwable);
    this.throwable = throwable;
  }


  public LogicBizException(String errorCode, String errorDesc, Throwable throwable) {
    super(errorDesc,throwable);
    this.errorCode = errorCode;
    this.errorDesc = errorDesc;
    this.throwable = throwable;
  }

  public String getErrorDesc() {
    return errorDesc;
  }

  public void setErrorDesc(String errorDesc) {
    this.errorDesc = errorDesc;
  }

  public String getErrorCode() {
    return errorCode;
  }

  public void setErrorCode(String errorCode) {
    this.errorCode = errorCode;
  }

  public Throwable getThrowable() {
    return throwable;
  }

  public void setThrowable(Throwable throwable) {
    this.throwable = throwable;
  }


}
