package com.unicom.mapper;

import com.unicom.mapper.base.BaseMapper;
import com.unicom.pojo.rule.IRule;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface RuleMapper<IRule> extends BaseMapper {


  public int del(@Param("ids") String [] ids);

}
