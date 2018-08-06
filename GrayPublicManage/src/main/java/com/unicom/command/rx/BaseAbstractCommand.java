package com.unicom.command.rx;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.unicom.biz.base.BaseBiz;
import com.unicom.command.rx.execute.IExecuteCommand;
import com.unicom.command.rx.fallback.BaseFallbackCommand;
import com.unicom.command.rx.fallback.IFallBack;

import java.util.function.Function;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author
 * @create 2018-07-10 16:37
 **/

public  class BaseAbstractCommand<R> extends HystrixCommand<R> {

  Logger logger = LoggerFactory.getLogger(this.getClass());
  private static final String TAG = "BaseAbstractCommand";
  private IFallBack<R> fallbackCommand;
  private IExecuteCommand<R> executeCommand;

  public BaseAbstractCommand(IExecuteCommand<R> executeCommand, IFallBack<R> fallbackCommand) {
    super(HystrixCommandGroupKey.Factory.asKey(TAG),1000);
    this.executeCommand = executeCommand;
    this.fallbackCommand = fallbackCommand;
  }

  public BaseAbstractCommand() {
    super(HystrixCommandGroupKey.Factory.asKey(TAG));
  }

  @Override
  protected R run() throws Exception {
    return executeCommand.execute();
  }

  @Override
  protected R getFallback() {
    logger .error( "Error Fall Back  cause :" + getFailedExecutionException().getClass().getName() + " , detail : " + getFailedExecutionException().getMessage() ) ;
    if (com.google.common.base.Optional.fromNullable(fallbackCommand).isPresent()) {
      return fallbackCommand.fallback();
    } else {
      return null;
    }
  }


}
