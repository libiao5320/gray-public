package com.unicom.pojo.route;

import java.io.Serializable;

/**
 * 抽象Route
 *
 * @author
 * @create 2018-06-25 15:24
 **/
public class AbstractRoute implements Serializable {


  private String id;
  private String routeKey;
  private String path;
  private int stripPrefix;
  private String url;
  private String serviceId;
  private int retryable;

  private Integer crtUser;
  private Integer uptUser;
  private String crtTime;
  private String uptTime;
  private int index;

  private Integer state = 1;


  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getPath() {
    return path;
  }

  public void setPath(String path) {
    this.path = path;
  }

  public int getStripPrefix() {
    return stripPrefix;
  }

  public void setStripPrefix(int stripPrefix) {
    this.stripPrefix = stripPrefix;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public String getServiceId() {
    return serviceId;
  }

  public void setServiceId(String serviceId) {
    this.serviceId = serviceId;
  }

  public int getRetryable() {
    return retryable;
  }

  public void setRetryable(int retryable) {
    this.retryable = retryable;
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

  public String getRouteKey() {
    return routeKey;
  }

  public void setRouteKey(String routeKey) {
    this.routeKey = routeKey;
  }

  public int getIndex() {
    return index;
  }

  public void setIndex(int index) {
    this.index = index;
  }
}
