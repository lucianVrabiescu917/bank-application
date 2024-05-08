package com.example.bankapplication.aspects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogStartEndExecutionAspect {

    @Before("@annotation(com.example.bankapplication.aspects.LogStartEndExecution)")
    public void logBefore(JoinPoint joinPoint) {
        Logger logger = LogManager.getLogger(joinPoint.getTarget().getClass());
        if (joinPoint.getSignature() != null) {
            String methodName = joinPoint.getSignature().toShortString();
            logger.info("START: {}", methodName);
        }
    }

    @After("@annotation(com.example.bankapplication.aspects.LogStartEndExecution)")
    public void logAfter(JoinPoint joinPoint) {
        Logger logger = LogManager.getLogger(joinPoint.getTarget().getClass());
        if (joinPoint.getSignature() != null) {
            String methodName = joinPoint.getSignature().toShortString();
            logger.info("END: {}", methodName);
        }
    }
}
