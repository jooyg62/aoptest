package com.cafe24.aoptest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyAspect {

	@Before("execution(ProductVo com.cafe24.aoptest.ProductService.find(String))")
	public void beforeAdvice() {
		System.out.println("---before advice---");
	}
	
	@After("execution(* *.cafe24.*.ProductService.*(..))")
	public void afterAdvice() {
		System.out.println("---after advice---");
	}
	
	@AfterReturning("execution(* *.cafe24.*.ProductService.*(..))")
	public void afterReturningAdvice() {
		System.out.println("---afterReturning advice---");
	}
	
	@AfterThrowing(value="execution(* *.cafe24.*.ProductService.*(..))", throwing="ex")
	public void afterThrowingAdvice(Throwable ex) {
		System.out.println("---afterThrowing advice---" + ex);
	}
	
	@Around(value="execution(* *.cafe24.*.ProductService.*(..))")
	public Object adviceRound(ProceedingJoinPoint pjp) throws Throwable {
		/* before advice */
		System.out.println("--around(before) advice---");
		
		// PointCut 되는 메소드 호출
		Object[] parameters = {"Camera"};
		Object result = pjp.proceed(parameters);
//		Object result = pjp.proceed();
		
		/* after advice */
		System.out.println("--around(after) advice---");
		
		return result;
		
	}
}
