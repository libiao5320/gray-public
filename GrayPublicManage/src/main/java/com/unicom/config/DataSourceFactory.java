package com.unicom.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.session.defaults.DefaultSqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author
 * @create 2018-05-29 15:17
 **/


@Configuration
public class DataSourceFactory {

  @Autowired
  private DataSourceConfig dataSourceConfig;



//
//
//  @Bean
//  public SqlSessionFactory sqlSessionFactory(org.apache.ibatis.session.Configuration configuration) {
//     new org.apache.ibatis.session.Configuration(
//      return new SqlSessionFactory()
//  }
//
//  @Bean
//  public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sessionFactory) {
//    return new SqlSessionTemplate(sessionFactory);
//  }


}
