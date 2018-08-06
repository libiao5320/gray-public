package com.unicom.exception;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.unicom.util.ResultInfo;
import java.io.IOException;
import java.util.Properties;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.StringUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

/**
 * @author
 * @create 2018-07-06 10:20
 **/

public class CustomExceptionResolver extends SimpleMappingExceptionResolver {

  private String defaultErrorView;

//  private Properties exceptionMappings;

  public CustomExceptionResolver() {
  }

  @Override
  public void setDefaultErrorView(String defaultErrorView) {
    this.defaultErrorView = defaultErrorView;
    super.setDefaultErrorView(defaultErrorView);
  }

//  @Override
//  public void setExceptionMappings(Properties exceptionMappings) {
//    this.exceptionMappings = exceptionMappings;
//    super.setExceptionMappings(exceptionMappings);
//  }

  @Override
  protected ModelAndView doResolveException(HttpServletRequest request,
      HttpServletResponse response, Object handler, Exception ex) {
    boolean isAjax = this.isJsonRequest(handler);
    return isAjax ? this.jsonExceptionHandler(response, ex)
        : this.generalExceptionHandler(request, response, ex);
  }


  private boolean isJsonRequest(Object handler) {
    HandlerMethod method = (HandlerMethod) handler;
    ResponseBody body = (ResponseBody) method.getMethodAnnotation(ResponseBody.class);
    return body != null;
  }

  //ajax异步请求
  private ModelAndView jsonExceptionHandler(HttpServletResponse response, Exception e) {

    ServletOutputStream stream = null;

    try {
      response.setContentType("application/json");
      response.setCharacterEncoding("UTF-8");
      response.setHeader("Cache-Control", "no-cache, must-revalidate");
      stream = response.getOutputStream();
      String errorMsg = "系统出错了";
      if (e instanceof LogicBizException) {
        errorMsg = e.getMessage();
      }
      stream.write(JSON.toJSONBytes(new ResultInfo(((LogicBizException)e).getErrorCode(),"faild", errorMsg),
          new SerializerFeature[]{SerializerFeature.QuoteFieldNames}));
    } catch (Exception var16) {

    } finally {
      if (stream != null) {
        try {
          stream.close();
        } catch (IOException var15) {
          var15.printStackTrace();
        }
      }
    }
    return new ModelAndView();
  }

  //普通请求
  private ModelAndView generalExceptionHandler(HttpServletRequest request,
      HttpServletResponse response, Exception ex) {
    String errorLog = String
        .format("web请求[%s]发生异常:%s", new Object[]{request.getServletPath(), ex.getMessage()});
    this.logger.error(errorLog, ex);
    ModelAndView mav = new ModelAndView();
    String errorMsg = "系统出错了";
    if (ex instanceof LogicBizException) {
      errorMsg = ex.getMessage();
      mav.addObject("code", ((LogicBizException)ex).getErrorCode());
      mav.addObject("errormsg", errorMsg);
    }
    else
    {
      mav.addObject("code", 9999);
      mav.addObject("errormsg",   errorMsg + " : " + ex.getMessage());
    }



//    String name = ex.getClass().getName();
//    String path = this.exceptionMappings.getProperty(name);
    String wrapperPath = defaultErrorView;
    mav.setViewName(wrapperPath);
    return mav;
  }


}
