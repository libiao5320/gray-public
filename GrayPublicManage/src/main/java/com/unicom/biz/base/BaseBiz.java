package com.unicom.biz.base;

import com.unicom.pojo.route.AbstractRoute;
import java.util.List;

/**
 * @author
 * @create 2018-06-28 10:22
 **/
public interface BaseBiz<T> {

  public boolean add(T route) throws Exception;
  public boolean update(T route) throws Exception;
  public boolean del(String [] ids) throws Exception;
  public T get(String id) throws Exception;
  public List<T> fetchAll() throws Exception;


}
