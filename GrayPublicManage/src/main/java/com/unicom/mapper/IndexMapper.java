package com.unicom.mapper;

import com.unicom.pojo.condition.index.IIndex;
import com.unicom.mapper.base.BaseMapper;
import com.unicom.pojo.rule.IRule;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface IndexMapper<IINdex> extends BaseMapper {


  public List<IIndex> getByConditionId(@Param("conditionId") String conditionId);


}
