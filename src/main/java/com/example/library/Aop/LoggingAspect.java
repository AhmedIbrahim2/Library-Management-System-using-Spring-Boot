package com.example.library.Aop;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @After("execution(* com.example.library.service.*.*(..))")
    public void logBeforeAllMethods(JoinPoint joinPoint) {
        logger.info("A method in the service package is called : " + joinPoint.getSignature().getName());
    }
}
