package com.candlez.budget_guy.aop;

import com.candlez.budget_guy.annotation.SupportsHTML;
import com.candlez.budget_guy.util.RequestUtils;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ExceptionForwardingAspect {

    private final RequestUtils requestUtils;

    @Autowired
    public ExceptionForwardingAspect(RequestUtils requestUtils) {
        this.requestUtils = requestUtils;
    }

    @Around("@annotation(supportsHTML)")
    public Object exceptionHandler(ProceedingJoinPoint pjp, SupportsHTML supportsHTML) throws Throwable {
        // HttpServletRequest req, HttpServletResponse res, NoResourceFoundException e
        Object[] args = pjp.getArgs();
        HttpServletRequest req = (HttpServletRequest) args[0];

        if (requestUtils.isHtmlRequest(req)) {

            HttpStatus status = supportsHTML.value();

            if (supportsHTML.destination().isEmpty()) {

                req.setAttribute(RequestDispatcher.ERROR_STATUS_CODE, status);
                return "forward:/error";
            }

            HttpServletResponse res = (HttpServletResponse) args[1];
            res.setStatus(status.value());

            return "forward:/errors/" + supportsHTML.destination();
        }

        return pjp.proceed();
    }
}
