package com.unicom.pojo.condition.index;

import com.google.common.base.Objects;
import com.unicom.constant.OpEnum;
import com.unicom.model.BaseModel;
import java.lang.reflect.Field;

/**
 * @author
 * @create 2018-05-08 10:44
 **/
public  class AbstractIndex<T extends BaseModel> implements IIndex<T> {

  private String field;
  private OpEnum op;
  private Object value;
  private String conditionId;
  private String indexId;
  private Integer crtUser;
  private Integer uptUser;
  private String crtTime;
  private String uptTime;
  private Integer state = 1;


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

  public String getField() {
    return field;
  }

  public void setField(String field) {
    this.field = field;
  }

  public OpEnum getOp() {
    return op;
  }

  public void setOp(OpEnum op) {
    this.op = op;
  }

  public Object getValue() {
    return value;
  }

  public void setValue(Object value) {
    this.value = value;
  }


  protected Field getField(T t) throws NoSuchFieldException {
    return t.getClass().getDeclaredField(field);
  }

  protected Field[] getFields(T t) {
    return t.getClass().getDeclaredFields();
  }


  protected boolean compareObjToIndex(T t)
      throws NoSuchFieldException, IllegalAccessException {
    boolean flag = false;
    switch (op) {
      case _EQ:
        System.out.println("Compare value"+value.toString());
        System.out.println("Compare filed"+getFieldValue(t, this.getField(t)).toString());
        flag = Objects.equal(getFieldValue(t, this.getField(t)), value);
        break;
      case _NEQ:
        System.out.println("Compare value"+value.toString());
        System.out.println("Compare filed"+getFieldValue(t, this.getField(t)).toString());
        flag = !Objects.equal(getFieldValue(t, this.getField(t)), value);
        break;
      case _IN:
        System.out.println("Compare value"+value.toString());
        System.out.println("Compare filed"+getFieldValue(t, this.getField(t)).toString());
        flag = value.toString().indexOf(getFieldValue(t, this.getField(t)).toString()) != -1;
        break;
      case _NIN:
        System.out.println("Compare value"+value.toString());
        System.out.println("Compare filed"+getFieldValue(t, this.getField(t)).toString());
        flag = value.toString().indexOf(getFieldValue(t, this.getField(t)).toString()) == -1;
        break;
      default:
        flag = true;
        break;

    }
    return flag;
  }


  protected Object getFieldValue(T t, Field field) throws IllegalAccessException {

    field.setAccessible(true);

    return field.get(t);
  }


  @Override
  public boolean expression(T  t)
      throws NoSuchFieldException, IllegalAccessException {

    return compareObjToIndex(t);

  }

//  private Object autoGetFileValue(Field field, Object obj) throws IllegalAccessException {
//
//    switch ( field.getType() ) {
//      case String.class:
//        return field.getInt(obj);
//      default:
//        return field.get(obj);
//    }
//
//  }

}
