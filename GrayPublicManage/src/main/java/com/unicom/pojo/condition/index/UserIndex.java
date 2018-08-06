package com.unicom.pojo.condition.index;

import com.unicom.model.UserModel;

/**
 * @author
 * @create 2018-05-08 10:43
 **/
public class UserIndex extends AbstractIndex<UserModel> {


  @Override
  public boolean expression(UserModel userModel)
      throws NoSuchFieldException, IllegalAccessException {

    return compareObjToIndex(userModel);

  }
}
