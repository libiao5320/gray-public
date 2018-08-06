package com.unicom.biz.loginaccount;

import com.unicom.biz.base.BaseBiz;
import com.unicom.pojo.manager.LoginAccount;

/**
 * @author
 * @create 2018-07-16 14:44
 **/
public interface ILoginAccountBiz extends BaseBiz {

  public LoginAccount getByLoginName(String loginName);

}
