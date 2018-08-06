package com.unicom.mapper.base;

import com.unicom.pojo.route.AbstractRoute;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BaseMapper<T> {

  public int add(T  t);
  public int update(T t);
  public List<T> fetchAll();
  public T get(String id);

  public int del(@Param("ids") String [] ids);

}
