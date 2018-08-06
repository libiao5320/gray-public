package com.unicom.command;

import com.unicom.command.rx.BaseAbstractCommand;

/**
 * @author
 * @create 2018-07-10 16:52
 **/
public class ServiceCommandInvoker<R> implements ICommand<R, BaseAbstractCommand<R>> {


  @Override
  public R execute(BaseAbstractCommand<R> rBaseAbstractCommand) {
    return rBaseAbstractCommand.execute();
  }
}
