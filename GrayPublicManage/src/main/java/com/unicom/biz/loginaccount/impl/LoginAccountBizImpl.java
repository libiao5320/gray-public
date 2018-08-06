package com.unicom.biz.loginaccount.impl;

import com.google.common.base.Optional;
import com.unicom.biz.base.AbstractBaseBiz;
import com.unicom.biz.base.BaseBiz;
import com.unicom.biz.loginaccount.ILoginAccountBiz;
import com.unicom.mapper.IndexMapper;
import com.unicom.mapper.LoginAccountMapper;
import com.unicom.pojo.manager.LoginAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author
 * @create 2018-07-16 14:44
 **/
@Service
public class LoginAccountBizImpl<LoginAccout> extends AbstractBaseBiz implements ILoginAccountBiz {

  @Autowired
  public void init(LoginAccountMapper loginAccountMapper) {
    super.setBaseMapper(loginAccountMapper);
  }

  public LoginAccount getByLoginName(String loginName) {

    return (LoginAccount) Optional
        .fromNullable(((LoginAccountMapper) this.getBaseMapper()).getByLoginName(loginName))
        .orNull();

  }


}
