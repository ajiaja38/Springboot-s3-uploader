package com.uploader.spring.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.uploader.spring.filter.HttpReqResLoggingFilter;

@Configuration
public class WebConfig implements WebMvcConfigurer {

  @Autowired
  private HttpReqResLoggingFilter httpReqResLoggingFilter;

  @Bean
  FilterRegistrationBean<HttpReqResLoggingFilter> loggingFilterRegistration() {
    FilterRegistrationBean<HttpReqResLoggingFilter> registrationBean = new FilterRegistrationBean<>();
    registrationBean.setFilter(httpReqResLoggingFilter);
    registrationBean.addUrlPatterns("/*");
    registrationBean.setOrder(1);
    return registrationBean;
  }

}
