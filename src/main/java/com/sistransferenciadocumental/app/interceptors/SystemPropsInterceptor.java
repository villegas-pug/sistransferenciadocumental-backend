package com.sistransferenciadocumental.app.interceptors;

import java.net.InetAddress;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sistransferenciadocumental.app.constants.LevelLog;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class SystemPropsInterceptor implements HandlerInterceptor {

   @Override
   public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object handler) throws Exception {
      String uri = req.getRequestURI();
      String[] partUrl = uri.split("/");
      String machineName = InetAddress.getLocalHost().getHostName();
      String entity = partUrl[1].toUpperCase();
      String action = partUrl[2];

      System.setProperty("uri", uri);
      System.setProperty("package", "");
      System.setProperty("entity", entity);
      System.setProperty("method", action);
      System.setProperty("machineName", machineName);
      /*-> Default... */
      System.setProperty("levelLog", LevelLog.ERROR);
      return true;
   }

}