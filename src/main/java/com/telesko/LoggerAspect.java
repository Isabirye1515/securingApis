package com.telesko;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggerAspect {

    @Around("execution(public * com.telesko.MovieRest.getMovies(..))")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("Before execution of: " + joinPoint.getSignature());
        
        Object result = joinPoint.proceed();  // Execute the method
        
        System.out.println("After execution of: " + joinPoint.getSignature());
        return result;
    }
}
