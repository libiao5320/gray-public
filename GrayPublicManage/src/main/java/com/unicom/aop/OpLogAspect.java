package com.unicom.aop;

import com.alibaba.fastjson.JSON;
import com.unicom.biz.oplog.IOpLogBiz;
import com.unicom.pojo.other.OpLog;

import java.util.stream.Stream;
import javax.servlet.http.HttpServletRequest;
import org.aspectj.lang.JoinPoint;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


/**
 * @createUser Lee
 * @createTime 2018/7/10 11:03
 * @Package OpLogAspect.java
 * @ClassName
 * @ClassDesc
 **/
@Component
//@Aspect
public class OpLogAspect {


  @Autowired
  private IOpLogBiz opLogBiz;


  @Pointcut("execution(public * com.unicom.ctrl.*.*(..))")
  public void webLog() {
  }


  /**
   * @createUser Lee
   * @createTime 2018/7/10 10:56
   * @methodParams * @Param: null
   * @methodName
   * @methodDesc
   **/
  @Before("webLog()")
  public void process(JoinPoint pjp) throws Exception {

    ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder
        .getRequestAttributes();
    HttpServletRequest request = attributes.getRequest();
    OpLog opLog = new OpLog();
    opLog.setOpClass(pjp.getSignature().getDeclaringTypeName());
    opLog.setOpMethod(pjp
        .getSignature().getName());
    opLog.setRemoteIp(request.getRemoteAddr());

    if (pjp.getArgs().length > 0) {
      StringBuffer params = new StringBuffer();
      Stream.of(pjp.getArgs()).forEach((d) -> {
        params.append(d + ",");
      });
      opLog.setOpParam(params.substring(0, params.lastIndexOf(",")));
    } else {
      opLog.setOpParam(JSON.toJSONString(pjp.getArgs()));
    }
    opLog.setOpUser("test");
    opLogBiz.add(opLog);

  }


}
