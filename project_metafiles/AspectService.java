패키지 명세

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Aspect
@Slf4j
public class AspectService {
		       
	@Pointcut("execution(* *..서비스.*(..))")
    public void pointcutAspectService() {
    }
 
    @Before("pointcutAspectService()")
    public void adviceBefore() {
    	
        log.info("\n\n#######################################"
                + "########################################"
                + "########################################"
                + "########################################"
                + "########################################\n");
       
        log.info("OOO 모듈 시작");
    }
    
    @After("pointcutAspectService()")
    public void logAfter(JoinPoint jp) {
       
        log.info(jp.getSignature().getName()
                + " 메소드를 끝냈습니다.");
       
        log.info("\n\n#######################################"
                + "########################################"
                + "########################################"
                + "########################################"
                + "########################################\n");
    }
   
    @Around("pointcutAspectService()")
    public Object logAround(ProceedingJoinPoint pjp) {
       
        Object obj = null;
       
        try {
                log.info("AOP Around begin : "
                        + pjp.getSignature().getName()
                        + " 메소드를 시작합니다-1.");
               
                 obj = pjp.proceed();
                 
                log.info("AOP Around end : "
                        + pjp.getSignature().getName()
                        + " 메소드를 끝냈습니다-1.");
                   
        } catch (Throwable e) {
            log.error("logAround Exception : " + e.getMessage());
        }      
       
        return obj;
    }
   
    @AfterThrowing("pointcutAspectService()")
    public void adviceAfterThrowing() {
        log.info("예외처리되었습니다.");
    }

}