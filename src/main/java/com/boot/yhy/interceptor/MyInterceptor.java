package com.boot.yhy.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class MyInterceptor  implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		System.out.println("嗨！！！1拦截器~~~~");
		if ("get".equals(request.getMethod())) {
				
		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest req,
			HttpServletResponse reps, Object handler,
			ModelAndView modelAndView) throws Exception {
		String sign = "xxxx-xxxxx_xxxxxx_xxxxx";
		reps.setHeader("sign", sign);
		reps.setHeader("uuid", req.getHeader("uuid"));
		int code = reps.getStatus();
		String msg = "success";
		JSONObject meta = new JSONObject();
		if (200 == code) {
			code = 1;
		}else{
			
		}
		meta.append("code", code);
		meta.append("msg", msg);
		reps.addHeader("meta", meta.toString());
	}

	@Override
	public void afterCompletion(HttpServletRequest req,
			HttpServletResponse reps, Object handler, Exception e)
			throws Exception {
		System.out.println("after");
		String sign = "xxxx-xxxxx_xxxxxx_xxxxx";
		reps.setHeader("sign", sign);
		reps.setHeader("uuid", req.getHeader("uuid"));
		int code = reps.getStatus();
		String msg = "success";
		JSONObject meta = new JSONObject();
		if (200 == code) {
			code = 1;
		}else{
			msg=e.getMessage();
		}
		meta.append("code", code);
		meta.append("msg", msg);
		reps.addHeader("meta", meta.toString());
	}

}
