package com.boot.yhy.util;

import java.util.Date;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class BaseAspect {
	@Before("execution(* com.boot.yhy.service.*.update*(..))")
	public void saveBase() throws Exception {
		System.out.println(new Date().getTime());
		System.out.println("嗨！！！ Aop");
	}
}
