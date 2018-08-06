package com.unicom.mapper;

import com.unicom.pojo.route.AbstractRoute;
import com.unicom.pojo.rule.IRule;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author
 * @create 2018-06-25 15:23
 **/

@Repository
public interface RouteMapper {

  public int add(AbstractRoute route);
  public int update(AbstractRoute route);
  public List<AbstractRoute> fetchAll();
  public AbstractRoute getRoute(@Param("routeId") String routeId);
  public int del(@Param("ids") String [] ids);

}
