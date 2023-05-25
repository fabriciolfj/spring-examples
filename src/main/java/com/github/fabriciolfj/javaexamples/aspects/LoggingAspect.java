package com.github.fabriciolfj.javaexamples.aspects;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j
public class LoggingAspect {

    @Pointcut("execution(public String com.github.fabriciolfj.javaexamples.entrypoint.TestController.find())")
    public void logPointcut() {

    }

    @Before("logPointcut()")
    public void logAllMethodCallsAdvice() {
        System.out.println("int aspect");
    }

    @Pointcut("@annotation(com.github.fabriciolfj.javaexamples.annotations.LogAop)")
    public void logArgs() {

    }

    @Before("logArgs()")
    public void logMethodArgs(final JoinPoint joinPoint) {
        for (int i = 0; i < joinPoint.getArgs().length; i ++) {
            log.info("method {}, args received {}", joinPoint.getSignature().getName(), joinPoint.getArgs()[i]);
        }

        log.info("target class: {}", joinPoint.getTarget().getClass().getName());
        log.info("this class: {}", joinPoint.getThis().getClass().getName());
    }

    @AfterThrowing("logArgs()")
    public void logRuntimeException() {
        System.out.println("deu ruim");
    }
}
