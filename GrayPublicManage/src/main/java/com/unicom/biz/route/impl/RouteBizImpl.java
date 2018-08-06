package com.unicom.biz.route.impl;

import com.google.common.base.Optional;
import com.unicom.biz.route.IRouteBiz;
import com.unicom.mapper.RouteMapper;
import com.unicom.pojo.route.AbstractRoute;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author
 * @create 2018-06-25 15:42
 **/

@Service
public class RouteBizImpl implements IRouteBiz {

  @Autowired
  private RouteMapper routeMapper;


  @Override
  public boolean add(AbstractRoute route) {
    return Optional.fromNullable(routeMapper.add(route)).get() > 0;
  }

  @Override
  public boolean update(AbstractRoute route) {
    return Optional.fromNullable(routeMapper.update(route)).get() > 0;
  }

  @Override
  public boolean del(String[] ids) {
    return Optional.fromNullable(routeMapper.del(ids)).get() > 0;
  }

  @Override
  public AbstractRoute getRoute(String id) {
    return Optional.fromNullable(routeMapper.getRoute(id)).orNull();
  }

  @Override
  public List<AbstractRoute> fetchAll() {
    return routeMapper.fetchAll();
  }
}
