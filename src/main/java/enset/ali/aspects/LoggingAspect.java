package enset.ali.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
    @Around("execution(* enset.ali..*.*(..))")
    public Object logMethod(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("📝 Executing: " + joinPoint.getSignature().getDeclaringType().getSimpleName()
            + "." + joinPoint.getSignature().getName());
        Object result = joinPoint.proceed();
        System.out.println("✅ Completed: " + joinPoint.getSignature().getDeclaringType().getSimpleName()
            + "." + joinPoint.getSignature().getName());
        return result;
    }
}