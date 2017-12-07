package com.emumu.spice.aspect;

import com.google.gson.Gson;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Aspect
@Component
public class LogAspect {

    private final static Logger logger = LoggerFactory.getLogger(LogAspect.class);//使用spring提供的log框架slf4j
    private Gson gson = new Gson();

    @Pointcut("execution(public * com.emumu.spice.controller..*.*(..))")
    public void log(){

    }

    @Before("log()")
    public void doBefore(JoinPoint joinPoint) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String sessionID = request.getSession().getId();
        String logStr = "\n==================\n"
                + "Session ID:" + sessionID + "\n"
                + "url=" + request.getRequestURL() + "\n"
                + "method=" + request.getMethod() + "\n"
                + "ip=" + request.getRemoteAddr() + "\n"
                + "class_method=" + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName() + "\n"
                + "args=" + Arrays.toString(joinPoint.getArgs()) + "\n==================";
        logger.info(logStr);
    }

    @AfterReturning(returning = "object", pointcut = "log()")
    public void doAfterReturning(JoinPoint joinPoint, Object object){
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String sessionID = request.getSession().getId();
        String logStr = "\n==================\n"
                + "Session ID:" + sessionID + "\n"
                + "response=" + gson.toJson(object) + "\n"
                + "==================";
        logger.info(logStr);
    }
}
