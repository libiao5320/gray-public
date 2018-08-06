package com.unicom.pojo.condition.index;

import com.unicom.model.BaseModel;

public interface IIndex<T extends BaseModel> {


    public boolean expression( T t ) throws NoSuchFieldException, IllegalAccessException;

}
