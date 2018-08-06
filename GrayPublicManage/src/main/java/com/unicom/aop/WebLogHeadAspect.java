package com.unicom.aop;

import java.util.Arrays;
import javax.servlet.http.HttpServletRequest;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * @author
 * @create 2018-03-28 9:47
 **/


@Component
@Aspect
public class WebLogHeadAspect {

  private Logger logger = LoggerFactory.getLogger(this.getClass());
  private ThreadLocal<Long> startTime = new ThreadLocal<>();
  private final String PRE_TAG = "==============******* Aspect Log  ******================";


  @Pointcut("execution(public * com.unicom.ctrl.*.*(..))")
  public void webLog() {
  }


  @Before("webLog()")
  public void process(JoinPoint pjp) {
    startTime.set(System.currentTimeMillis());
    ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder
        .getRequestAttributes();
    HttpServletRequest request = attributes.getRequest();

    logger.info(PRE_TAG + " URL : " + request.getRequestURL().toString());
    logger.info(PRE_TAG + " HTTP_METHOD : " + request.getMethod());
    logger.info(PRE_TAG + " IP : " + request.getRemoteAddr());
    logger.info(PRE_TAG + " CLASS_METHOD : " + pjp.getSignature().getDeclaringTypeName() + "." + pjp
        .getSignature().getName());
    logger.info(PRE_TAG + " ARGS : " + Arrays.toString(pjp.getArgs()));


  }


  @AfterReturning(returning = "ret", pointcut = "webLog()")
  public void doAfterReturning(Object ret) throws Throwable {
    // 处理完请求，返回内容
    logger.info(PRE_TAG + "(doAfterReturning) RESPONSE : " + ret);
    logger.info(
        PRE_TAG + "(doAfterReturning) SPEND TIME : " + (System.currentTimeMillis() - startTime
            .get()));
  }

}
