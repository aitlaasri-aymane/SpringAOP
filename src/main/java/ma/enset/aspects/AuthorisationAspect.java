package ma.enset.aspects;

import ma.enset.service.SecurityContext;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

@Aspect
@Component
@EnableAspectJAutoProxy
public class AuthorisationAspect { // Make sure file is not .aj and its .java
    @Around(value = "@annotation(securedByAspect)", argNames = "proceedingJoinPoint,securedByAspect")
    public Object secure(ProceedingJoinPoint proceedingJoinPoint, SecuredByAspect securedByAspect) throws Throwable {
        String[] roles=securedByAspect.roles();
        boolean authorized=false;
        for (String r:roles){
            if (SecurityContext.hasRole(r)) {
                authorized = true;
                break;
            }
        }
        if (authorized == true) {
            Object result = proceedingJoinPoint.proceed();
            return result;
        }
        throw new RuntimeException("Unauthorized to access " + proceedingJoinPoint.getSignature());
    }
}
