package com.trimind.restdemo1.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Calendar;

@Component
public class EmployeeServiceInterceptor implements HandlerInterceptor {

    public static long startTime;
    public static long endTime;
    public static long totalTime;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        startTime=System.currentTimeMillis();
        System.out.println("Request sent from client @time in millies :  "+startTime);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

        endTime=System.currentTimeMillis();
        System.out.println("Response recieved from server @time in millies :  "+endTime);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

        totalTime=endTime-startTime;
        System.out.println("total time for the transaction in millies = "+totalTime);
    }
}
