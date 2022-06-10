package tn.esprit.spring.config;

import org.apache.logging.log4j.LogManager;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import org.apache.logging.log4j.Logger;

@Component
@Aspect
public class LoggingAspect {
    private static final Logger l = LogManager.getLogger(LoggingAspect.class);
    @Before("execution(* tn.esprit.spring.services.EmployeServiceImpl.*(..))")
    public void logMethodEntry(JoinPoint joinPoint) {
        String name = joinPoint.getSignature().getName();
        l.info("In method " + name + " : ");
    }

    @AfterReturning("execution(* tn.esprit.spring.services.*.*(..))")
    public void logMethodSuccessExit(JoinPoint joinPoint) {
        String name = joinPoint.getSignature().getName();
        l.info("After returning method " + name + " : without error");
    }

    @AfterThrowing("execution(* tn.esprit.spring.services.*.*(..))")
    public void logMethodSWithErrorExit(JoinPoint joinPoint) {
        String name = joinPoint.getSignature().getName();
        l.error("After throwing method " + name + " : with error");
    }

    @After("execution(* tn.esprit.spring.services.EmployeServiceImpl.*(..))")
    public void logMethodExit(JoinPoint joinPoint) {
        String name = joinPoint.getSignature().getName();
        l.info("Out method " + name + " : ");
    }


    @Around("execution(* tn.esprit.spring.services.*.*(..))")
    public Object profile(ProceedingJoinPoint pjp) throws Throwable {
        long start = System.currentTimeMillis();
        Object obj = pjp.proceed();
        long elapsedTime = System.currentTimeMillis() - start;
        if(elapsedTime<3000)
            l.info("Method execution time: " + elapsedTime + " milliseconds.");
        return obj;
    }
}