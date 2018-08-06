package com.unicom.mapper;

import com.unicom.mapper.base.BaseMapper;
import com.unicom.pojo.manager.LoginAccount;
import org.springframework.stereotype.Component;


@Component
public interface LoginAccountMapper extends BaseMapper<LoginAccount> {


  public LoginAccount getByLoginName(String loginName);


}
