package com.unicom.command;

import com.alibaba.fastjson.JSONObject;
import com.unicom.command.rx.BaseAbstractCommand;
import java.util.function.Function;

/**
 * @author
 * @create 2018-07-10 16:29
 **/
public interface ICommand<R ,W extends BaseAbstractCommand<R>> {


  public  R execute(W w );

}
