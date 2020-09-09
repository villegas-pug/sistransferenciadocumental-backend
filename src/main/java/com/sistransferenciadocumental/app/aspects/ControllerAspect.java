package com.sistransferenciadocumental.app.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ControllerAspect {

   @Pointcut("execution( * com.sistransferenciadocumental.app.controllers.*.*(..))")
   public void pointCutMethod() {
   }

   @AfterThrowing("pointCutMethod()")
   public void handleAfterThrowing(JoinPoint point) {
      System.setProperty("package", point.getTarget().getClass().getName());
      System.setProperty("method", point.getSignature().getName());
   }
}