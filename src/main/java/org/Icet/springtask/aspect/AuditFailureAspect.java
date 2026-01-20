package org.Icet.springtask.aspect;

import lombok.extern.slf4j.Slf4j;
import org.Icet.springtask.service.AuditFailure;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class AuditFailureAspect {

    @AfterThrowing(pointcut = "@annotation(auditFailure)", throwing = "ex")
    public void auditFailedBooking(JoinPoint joinPoint, AuditFailure auditFailure, Throwable ex) {
        Object[] args = joinPoint.getArgs();


        Long userId = args.length > 0 ? (Long) args[0] : null;
        Long seatId = args.length > 1 ? (Long) args[1] : null;

        log.warn("Booking failed! User: {}, Seat: {}, Reason: {}", userId, seatId, ex.getMessage());

    }
}
