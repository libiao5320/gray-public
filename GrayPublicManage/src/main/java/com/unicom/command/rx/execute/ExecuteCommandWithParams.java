package com.unicom.command.rx.execute;

import java.util.function.Function;

/**
 * @author
 * @create 2018-07-12 10:26
 **/
public class ExecuteCommandWithParams<R, W> implements IExecuteCommand<R>{


  private Function<W, R> function;
  private W w;

  public ExecuteCommandWithParams(Function function) {
    this.function = function;
  }


  public ExecuteCommandWithParams buildParams(W w) {
    this.w = w;
    return ExecuteCommandWithParams.this;
  }

  @Override
  public R execute() {
    return function.apply(w);
  }
}
