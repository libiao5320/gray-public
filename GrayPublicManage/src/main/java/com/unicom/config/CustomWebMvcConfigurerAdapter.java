package com.unicom.config;

import com.unicom.exception.CustomExceptionResolver;
import com.unicom.interrupter.AuthInterrupter;
import java.util.List;
import java.util.Properties;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.util.ResourceUtils;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author
 * @create 2018-06-26 10:28
 **/
@Configuration
public class CustomWebMvcConfigurerAdapter extends WebMvcConfigurerAdapter implements
    ApplicationContextAware {

  private ApplicationContext applicationContext;


  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(new AuthInterrupter()).addPathPatterns("/**")
        .excludePathPatterns("/login").excludePathPatterns("/nosession")
        .excludePathPatterns("/validateLogin").excludePathPatterns("/static/**");
    super.addInterceptors(registry);
  }





  @Override
  public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    this.applicationContext = applicationContext;
  }

  @Bean
  public FormHttpMessageConverter formHttpMessageConverter() {

    return new FormHttpMessageConverter();
  }

  @Bean
  public CustomExceptionResolver customExceptionResolver(){
    CustomExceptionResolver customExceptionResolver = new CustomExceptionResolver();
    customExceptionResolver.setDefaultErrorView("error/error");
    return customExceptionResolver;
  }




}
