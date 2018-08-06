package com.unicom.biz.index.impl;

import com.unicom.biz.base.AbstractBaseBiz;
import com.unicom.biz.index.IIndexBiz;
import com.unicom.mapper.ConditionMapper;
import com.unicom.mapper.IndexMapper;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author
 * @create 2018-06-28 10:13
 **/
@Service
public class IndexBizImpl<AbstractIndex> extends AbstractBaseBiz implements IIndexBiz {


  @Autowired
  public void init(IndexMapper indexMapper) {
    super.setBaseMapper(indexMapper);
  }

  @Override
  public List fetchIndexByConditionId(String conditionId) {
    return ((IndexMapper)super.getBaseMapper()).getByConditionId(conditionId);
  }
}
