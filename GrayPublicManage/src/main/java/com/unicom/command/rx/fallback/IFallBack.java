package com.unicom.command.rx.fallback;



@FunctionalInterface
public interface IFallBack<R> {
  public R fallback();
}
