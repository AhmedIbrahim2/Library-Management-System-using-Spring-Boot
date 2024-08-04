package com.example.library.Aop;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
@Aspect
@Component
public class LoggingAspect {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Before("execution(* com.example.library.service.BookService.addBook(..)) || execution(* com.example.library.service.BookService.updateBook(..)) || execution(* com.example.library.service.PatronService.addPatron(..)) || execution(* com.example.library.service.PatronService.updatePatron(..))")
    public void logBeforeSpecificMethods(JoinPoint joinPoint) {
        logger.info("Entering method: " + joinPoint.getSignature().getName());
    }

    @After("execution(* com.example.library.service.BookService.addBook(..)) || execution(* com.example.library.service.BookService.updateBook(..)) || execution(* com.example.library.service.PatronService.addPatron(..)) || execution(* com.example.library.service.PatronService.updatePatron(..))")
    public void logAfterSpecificMethods(JoinPoint joinPoint) {
        logger.info("Exiting method: " + joinPoint.getSignature().getName());
    }

    @AfterThrowing(pointcut = "execution(* com.example.library.service.BookService.addBook(..)) || execution(* com.example.library.service.BookService.updateBook(..)) || execution(* com.example.library.service.PatronService.addPatron(..)) || execution(* com.example.library.service.PatronService.updatePatron(..))", throwing = "error")
    public void logExceptionsSpecificMethods(JoinPoint joinPoint, Throwable error) {
        logger.error("Exception in method: " + joinPoint.getSignature().getName() + " with cause: " + (error.getCause() != null ? error.getCause() : "NULL"));
    }

    @Around("execution(* com.example.library.service.BookService.addBook(..)) || execution(* com.example.library.service.BookService.updateBook(..)) || execution(* com.example.library.service.PatronService.addPatron(..))|| execution(* com.example.library.service.PatronService.updatePatron(..))")
    public Object logPerformanceSpecificMethods(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        Object returnValue = joinPoint.proceed();
        long timeTaken = System.currentTimeMillis() - start;
        logger.info("Method " + joinPoint.getSignature().getName() + " executed in " + timeTaken + "ms");
        return returnValue;
    }
}
