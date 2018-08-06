package com.unicom.biz.route;

import com.unicom.pojo.route.AbstractRoute;
import com.unicom.pojo.rule.IRule;
import java.util.List;

public interface IRouteBiz {




  public boolean add(AbstractRoute route);
  public boolean update(AbstractRoute route);
  public boolean del(String [] ids);
  public AbstractRoute getRoute(String id);
  public List<AbstractRoute> fetchAll();

}
