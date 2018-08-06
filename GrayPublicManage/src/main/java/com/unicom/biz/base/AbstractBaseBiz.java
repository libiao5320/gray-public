package com.unicom.biz.base;

import com.google.common.base.Optional;
import com.unicom.mapper.base.BaseMapper;
import com.unicom.pojo.route.AbstractRoute;
import java.util.List;

/**
 * @author
 * @create 2018-06-28 10:24
 **/
public abstract class AbstractBaseBiz<T> implements BaseBiz<T> {

  private BaseMapper baseMapper;

  @Override
  public boolean add(T t) {
    return Optional.fromNullable(baseMapper.add(t)).get() > 0;
  }

  @Override
  public boolean update(T t) {
    return Optional.fromNullable(baseMapper.update(t)).get() > 0;
  }


  public T get(String id) {
    Object result = baseMapper.get(id);
    if (Optional.fromNullable(result).isPresent()) {
      return (T) result;
    } else {
      return null;
    }
  }

  @Override
  public List<T> fetchAll() throws Exception {

    return baseMapper.fetchAll();
  }

  public BaseMapper getBaseMapper() {
    return baseMapper;
  }

  public void setBaseMapper(BaseMapper baseMapper) {
    this.baseMapper = baseMapper;
  }


  public boolean del(String[] ids) {
    return Optional.fromNullable(baseMapper.del(ids)).get() > 0;
  }
}
