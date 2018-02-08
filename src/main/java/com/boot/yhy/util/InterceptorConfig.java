package com.boot.yhy.util;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.boot.yhy.interceptor.MyInterceptor;

@Configuration
public class InterceptorConfig extends WebMvcConfigurerAdapter{
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		//这里可以配多个
		/*registry.addInterceptor(new MyInterceptor()).addPathPatterns("/**");
		super.addInterceptors(registry);*/
	}
	

}
