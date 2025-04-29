package com.umc.bios.global.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;
import org.slf4j.Logger;

@Component
@Aspect
public class LogAspect {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Pointcut("within(com.umc.bios.domain..*)")
    public void logPointcut() {
        // pintCut signature 메소드에는 내용이 없어야함
    }

    @Around("logPointcut()")
    public Object loggingAspect(ProceedingJoinPoint joinPoint) throws Throwable {

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        Object result = joinPoint.proceed();

        stopWatch.stop();
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        long time = stopWatch.getLastTaskTimeMillis();

        logger.info("실행 메소드 : {}, args : {}, time : {}ms",
                methodName,
                args.length > 0 ? args[0] : "no-args",
                time
        );

        return result;
    }
}
