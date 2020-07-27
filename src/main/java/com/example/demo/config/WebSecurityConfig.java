package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.oauth2.client.CommonOAuth2Provider;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.firewall.DefaultHttpFirewall;
import org.springframework.security.web.firewall.HttpFirewall;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	private static final String[] RESOURCE_LOCATIONS = { "classpath:/static/", "/css/**", "/font/**", "/js/**",
			"/image s/**", "/lib/**", "/scss/**", "/resources/**/*", "/jsp/**", "/resources/images/common/*" };

	private static String CLIENT_PROPERTY_KEY = "spring.security.oauth2.client.registration.";
	
	/**
	 * application.properties ( OAuth2 CLIENTS )
	 * */

    @Autowired
    private Environment env;
    //@Autowired
    //private OAuth2SuccessHandler succHandler;
    //@Autowired
    //private LoginSuccessHandler loginSuccHandler;
    //@Autowired
    //private CustomAuthenticationFailHandler failHandler;
    
    @Autowired
    private UserDetailsService userDetailsService;
	@Autowired
    private PasswordEncoder passwordEncoder;
	
	/* v1 */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
	}
	
	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(userDetailsService);
		authenticationProvider.setPasswordEncoder(passwordEncoder);
		return authenticationProvider;
	}
	
	@Bean
    public AuthenticationManager authManager() throws Exception {
        return this.authenticationManager();
    }
	
	/* v1 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//http.csrf().disable() -- Spring Boot Security 적용시 Ajax를통한 POST 방식에서 403 에러 처리방식 ( 이 방식은 비추, workmanList.jsp 에 처리해놨음. ) 
		http.authorizeRequests()
					// 관리자 설정
					.antMatchers("/admin/**").hasRole("ADMIN")
			        // OAuth2 설정
			        .antMatchers("/oauth2/**","/login/**","/auth/**","/registation","/").permitAll()
			        .antMatchers("/console/**").permitAll()
			        .antMatchers("/facebook").hasAuthority("")
			        .antMatchers("/google").hasAuthority("")
			        // API 모든 권한
			        .antMatchers("/api/**").permitAll()
			        // 모든 요청 권한
			        .antMatchers("/**").permitAll()
		        //.anyRequest().authenticated()
		        .anyRequest().permitAll()
            	.and()
					// 403 예외처리 핸들링
					.exceptionHandling().accessDeniedPage("/long-process")
	            .and()
	            	// 기본 폼 로그인 설정
			        .formLogin()
			        //.successHandler(loginSuccHandler)
			        //.failureHandler(failHandler)
			    .and()
//					.loginPage("/login")
//					.permitAll()
	            	// OAuth2 로그인 설정
	            	//.oauth2Login()
	            	//.successHandler(succHandler)
//	            	.defaultSuccessUrl("/login/result")
//	            	.failureUrl("/loginFailure?error=true")
	            	//.failureHandler(failHandler)
				//.and() // 로그아웃 설정
					.logout()
					.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
	                //.logoutSuccessUrl("/user/logout/result")
					.deleteCookies("JESSIONID")
	                .invalidateHttpSession(true)
					.permitAll()
				.and()
					.exceptionHandling()
					// Custom Login View 사용
					.authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint("/login"))
				.and()
					.csrf()
					.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
	            ;
	}

	/**
	 * OAuth2 구현
	 * - Common OAuth(Kakao, Google, etc..)
	 * - KAKAO Custom
	 * */
	/*
	@Bean
	public ClientRegistrationRepository clientRegistrationRepository() {
		
//		# - GOOGLE
		String google_client_id = "";
		String google_client_secret= "";
		
//		# - FACEBOOK
		String facebook_client_id = "";
		String facebook_client_secret = "";
//
//		# - KAKAO
		String kakao_client_id = "";
		String kakao_client_secrect = "";
		
		// Google,facebook, ...
		String socialAuths = "facebook,google";
//		clients = Arrays.asList(socialAuths.split(","));
//		List registrations = (List) clients.stream()
//											.map( c -> getRegistration((String) c) )
//											.filter( registration -> registration != null )
//											.collect(Collectors.toList());

		List registrations = new ArrayList<ClientRegistration>();
		
		registrations.add(CommonOAuth2Provider.GOOGLE.getBuilder("google")
				.clientId(google_client_id)
				.clientSecret(google_client_secret)
				.build());

		registrations.add(CommonOAuth2Provider.FACEBOOK.getBuilder("facebook")
				.clientId(facebook_client_id)
				.clientSecret(facebook_client_secret)
				.build());
		// KAKAO
		//registrations.add(CustomOAuth2Provider.KAKAO.getBuilder("kakao")
		//		.clientId(kakao_client_id)
		//		.clientSecret(kakao_client_secrect)
		//		.jwkSetUri("temp")
		//		.build());


		return new InMemoryClientRegistrationRepository(registrations);
	}*/
	
	/**
	 * Builder 설정
	 * */
	/*
	private ClientRegistration getRegistration(String client) {
		
		String clientId = env.getProperty(CLIENT_PROPERTY_KEY + client + ".client-id");
		if (clientId == null) {
			return null;
		}
		String clientSecret = env.getProperty(CLIENT_PROPERTY_KEY + client + ".client-secret");
		if (client.equals("google")) {
			return CommonOAuth2Provider.GOOGLE.getBuilder(client).clientId(clientId).clientSecret(clientSecret).build();
		}
		if (client.equals("facebook")) {
			return CommonOAuth2Provider.FACEBOOK.getBuilder(client).clientId(clientId).clientSecret(clientSecret)
					.build();
		}
		return null;
	}
	*/

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		// static 디렉터리의 하위 파일 목록은 인증 무시 ( = 항상통과 )
		web.ignoring().antMatchers(RESOURCE_LOCATIONS);
		// "//와같은 형태 url 통과 - facebook oauth2  "
		web.httpFirewall(defaultHttpFirewall());
	}
	
	@Bean
	public HttpFirewall defaultHttpFirewall() {
	    return new DefaultHttpFirewall();
	}
}
