package com.unicom.command.rx.execute;


@FunctionalInterface
public interface IExecuteCommand<R> {

  public abstract R execute() throws Exception;
}
