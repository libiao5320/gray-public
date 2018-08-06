package com.unicom.biz.oplog.impl;

import com.unicom.biz.base.AbstractBaseBiz;
import com.unicom.biz.index.IIndexBiz;
import com.unicom.biz.oplog.IOpLogBiz;
import com.unicom.mapper.IndexMapper;
import com.unicom.mapper.OpLogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author
 * @create 2018-07-03 16:51
 **/

@Service
public class OpLogBizImpl <OpLog> extends AbstractBaseBiz implements IOpLogBiz {


  @Autowired
  public void init(OpLogMapper opLogMapper) {
    super.setBaseMapper(opLogMapper);
  }


}
