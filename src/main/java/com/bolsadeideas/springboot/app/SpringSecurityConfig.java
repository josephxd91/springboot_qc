package com.bolsadeideas.springboot.app;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

//import com.bolsadeideas.springboot.app.auth.handler.LoginSuccessHandler;
//import com.bolsadeideas.springboot.app.models.service.JpaUserDetailsService;

@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
//
//	@Autowired
//	private LoginSuccessHandler successHandler;

//	@Autowired
//	private JpaUserDetailsService userDetailsService;

//	@Autowired
//	private BCryptPasswordEncoder passwordEncoder;

//	@Value("${security.enable-csrf}")
//	private boolean csrfEnabled;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.csrf().disable();
//		http.authorizeRequests().antMatchers("/", "/css/**", "/js/**", "/images/**", "/listar", "/locale").permitAll()
//		.anyRequest().authenticated()
//		.and()
//		    .formLogin()
//		        .successHandler(successHandler)
//		        .loginPage("/login")
//		    .permitAll()
//		.and()
//		.logout().permitAll()
//		.and()
//		.exceptionHandling().accessDeniedPage("/error_403");

//		super.configure(http);
//
//		if (!csrfEnabled) {
//			http.csrf().disable();
//		}

	}

	@Autowired
	public void configurerGlobal(AuthenticationManagerBuilder build) throws Exception {
//		build.inMemoryAuthentication().withUser("jose").password("jose").roles("USER");
//		build.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
	}
}
