package com.seser.mancalagame.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @author Eser
 * This class contains AOP programming and logger for each method with some details.
 */
@Aspect
@Component
public class LoggingAspect {

	private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

	/**
	 * This method describes which class will be logged by AOP according to package name.
	 */
	@Pointcut("within(com.seser.*.*.*)")
	protected void allMethod() {
	}

	/**
	 * This method calls before function start.
	 * @param joinPoint
	 */
	@Before("allMethod()")
	public void logStartOfMethod(JoinPoint joinPoint) {
		logger.info("Inside method ["+joinPoint.getSignature().getName()+"] "+joinPoint.getTarget().getClass().getName()+ " @ "+LocalDateTime.now().toString());
	}

	/**
	 * This method calls after function end.
	 * @param joinPoint
	 */
	@After("allMethod()")
	public void logEndOfMethod(JoinPoint joinPoint) {
		logger.info("Outside method ["+joinPoint.getSignature().getName()+"]  "+joinPoint.getTarget().getClass().getName()+ " @ "+LocalDateTime.now().toString());
	}
}