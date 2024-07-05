//package com.houstondirectauto.refurb.config;
//
//import com.houstondirectauto.refurb.filter.JwtAuthenticationEntryPoint;
//import com.houstondirectauto.refurb.filter.JwtRequestFilter;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.builders.WebSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//
//@Configuration
//@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
//public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//
//	@Autowired
//	private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
//
//	@Autowired
//	private UserDetailsService jwtUserDetailsService;
//
//	@Autowired
//	private JwtRequestFilter jwtRequestFilter;
//
//	@Autowired
//	private BCryptPasswordEncoder bCryptPasswordEncoder;
//
//	@Autowired
//	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//		// configure AuthenticationManager so that it knows from where to load
//		// user for matching credentials
//		// Use BCryptPasswordEncoder
//		auth.userDetailsService(jwtUserDetailsService).passwordEncoder(bCryptPasswordEncoder);
//	}
//
//	@Bean
//	@Override
//	public AuthenticationManager authenticationManagerBean() throws Exception {
//		return super.authenticationManagerBean();
//	}
//
//	@Override
//	protected void configure(HttpSecurity httpSecurity) throws Exception {
//		// We don't need CSRF for this example
//		httpSecurity.csrf().disable()
////		httpSecurity.cors().and().csrf().disable()
//				// dont authenticate this particular request
//				.authorizeRequests()
//				.antMatchers("/file/**", "/file/*", "/accounts/login", "/welcome", "/user**", "/user/**", "/cron/api/getSmartsheet",
//						"/cron/api/getRefurbData", "/cron/api/getIdmsData", "/cron/api/getHdaData", "/cron/**",
//						"/user/verify/**", "/user/verify/otp/**", "/user/account/password", "/user/*", "/store/*",
//						"/store/**", "/rule/*", "/rule/**", "/manage-cron/*", "/manage-cron/**", "/file/upload",
//						"/file/**", "/inventory**", "/inventory/**", "/cron-audit-log**", "/cron-audit-log/**", "/api/run-rules", "/module**", "/module/**", "/role**", "/role/**")
//				.permitAll().
//				// all other requests need to be authenticated
//				anyRequest().authenticated().and().
//				// make sure we use stateless session; session won't be used to
//				// store user's state.
//				exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint).and().sessionManagement()
//				.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//
//		// Add a filter to validate the tokens with every request
//		httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
//	}
//
//	@Override
//	public void configure(WebSecurity web) throws Exception {
//		web.ignoring().antMatchers("/v3/api-docs/**", "/v3/api-docs/", "/swagger-resources/**", "/swagger-ui.html",
//				"/swagger-ui/#/", "/swagger-ui/**", "/configuration/ui", "/configuration/**", "/webjars/**",
//				"/actuator/**");
//	}
//}
