package com.unicom.biz.index;

import com.unicom.biz.base.BaseBiz;
import java.util.List;

public interface IIndexBiz extends BaseBiz {


    public List fetchIndexByConditionId(String conditionId);


}
