package com.ssafy.happyhouse.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.CorsUtils;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.ssafy.happyhouse.handler.AuthenticationEntryPointHandler;
import com.ssafy.happyhouse.handler.JwtAuthenticationFilter;
import com.ssafy.happyhouse.handler.WebAccessDeniedHandler;
import com.ssafy.happyhouse.model.service.CustomUserDetailService;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	// AuthenticationManagerBuilder의 passwordEncoder를 통해서
	// 패스워드 암호화에 사용될 PasswordEncoder 구현체를 지정할 수 있다.

	private final JwtProvider jwtProvider;
	private final WebAccessDeniedHandler webAccessDeniedHandler;
	private final AuthenticationEntryPointHandler authenticationEntryPointHandler;
	private final CustomUserDetailService customUserDetailService;

	@Override
	protected void configure(AuthenticationManagerBuilder builder) throws Exception {
		builder.userDetailsService(customUserDetailService).passwordEncoder(passwordEncoder());
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean(name = BeanIds.AUTHENTICATION_MANAGER)
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Override
	public void configure(WebSecurity web) {
		web.ignoring().antMatchers("/v2/api-docs", "/configuration/ui", "/swagger-resources/**",
				"/configuration/security", "/swagger-ui.html", "/webjars/**", "/swagger-ui/**");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.httpBasic().disable().csrf().disable().sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().authorizeRequests()
				.requestMatchers(CorsUtils::isPreFlightRequest).permitAll()
				.antMatchers("/", "/user/login*", "/user/register", "/user/findpw", "/user/refreshToken",
						"/user/mail", "/user/verifyCode", "/board/total", "/user/info")
				.permitAll().anyRequest().authenticated().and().cors().and().exceptionHandling()
				.authenticationEntryPoint(authenticationEntryPointHandler).accessDeniedHandler(webAccessDeniedHandler)
				.and()
				.addFilterBefore(new JwtAuthenticationFilter(jwtProvider), UsernamePasswordAuthenticationFilter.class);
	}

	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
		// - (3)
		configuration.addAllowedOriginPattern("*");
		configuration.addAllowedMethod("*");
		configuration.addAllowedHeader("*");
		configuration.setAllowCredentials(true);
		configuration.setMaxAge(3600L);
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}

}
