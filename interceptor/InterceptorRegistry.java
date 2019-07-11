package com.trimind.restdemo1.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
@Component
public class InterceptorRegistry extends WebMvcConfigurationSupport {
    @Autowired
    EmployeeServiceInterceptor employeeServiceInterceptor;

    @Override
    protected void addInterceptors(org.springframework.web.servlet.config.annotation.InterceptorRegistry registry) {
         registry.addInterceptor(employeeServiceInterceptor);
    }
}
