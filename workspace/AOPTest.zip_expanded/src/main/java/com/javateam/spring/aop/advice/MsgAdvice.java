package com.javateam.spring.aop.advice;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
// import org.slf4j.LoggerFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Service;

// import ch.qos.logback.classic.Logger;

// (functions) service 하는 component
@Service
@Aspect // 관점 서비스 => 몰래 챙겨주는 서비스!
public class MsgAdvice {
	
	private static final Logger log
		= LoggerFactory.getLogger(MsgAdvice.class);

	// 교차점(pointcut)을 별도로 생성 
	// -> 호출명이 간소화
	@Pointcut("execution(* com.javateam.spring.aop.bean.MsgBean.setMsg(..))")
	public void pointcutShow() {
		
	}
	
	// @Before("execution(* com.javateam.spring.aop.bean.MsgBean.setMsg(..))")
	@Before("pointcutShow()")
	public void showBeforeAdvice() { // 충고(advice)
		 log.debug("열심히 공부합시다-2 !");
	}
	
	// @Before("execution(* com.javateam.spring.aop.bean.MsgBean.setMsg(..))")
	@Before("pointcutShow()")
	public void showBeforeAdvice2() { // 충고(advice)
		log.debug("열심히 공부합시다 !");
	}
	
	// After 결합점(jointpoint) 생성
	// @After("execution(* com.javateam.spring.aop.bean.MsgBean.setMsg(..))")
	@After("pointcutShow()")
	public void showAfterAdvice() {
		log.debug("꼭 합격하세요 !");
	}
	
	// After 결합점(jointpoint) 생성
	// @After("execution(* com.javateam.spring.aop.bean.MsgBean.setMsg(..))")
	@After("pointcutShow()")
	public void showAfterAdvice2() {
		log.debug("합격하고 좀 쉬자 !");
	}
	
	// @Around("execution(* com.javateam.spring.aop.bean.MsgBean.setMsg(..))")
//	@Around("pointcutShow()")
//	public Object showAroundAdvice(ProceedingJoinPoint joinPoint) 
//				throws Throwable {
//		
//		log.debug("열공 시작!");
//		
//		Object obj = joinPoint.proceed();
//		
//		log.debug("열공 끝!");
//		
//		return obj;
//	}
	
	// 예외처리(Exception)후에 실행하는 충고(advice)
	@AfterThrowing("pointcutShow()")
	public void showAfterThrowingAdvice() {
		log.debug("예외처리 후의 충고");
	}

}