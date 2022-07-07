//package com.ssafy.happyhouse.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//import com.ssafy.happyhouse.interceptor.BearerAuthInterceptor;
//
//@Configuration
//public class WebMvcConfig implements WebMvcConfigurer{
//	private static final String[] EXCLUDE_PATHS = { "/jwt/**", "/error/**" };
//	
//	@Autowired
//	private final BearerAuthInterceptor bearerAuthInterceptor;
//
//	
//	public WebMvcConfig(BearerAuthInterceptor bearerAuthInterceptor) {
//		this.bearerAuthInterceptor = bearerAuthInterceptor;
//	}
//	
//	
//	@Override
//	public void addInterceptors(InterceptorRegistry registry) {
//		System.out.println("===========regist interceptor============");
//		registry.addInterceptor(bearerAuthInterceptor).addPathPatterns("")// 기본 적용 경로
//		.excludePathPatterns(EXCLUDE_PATHS);// 적용 제외 경로
//	}
//	
//	
//	
//}
