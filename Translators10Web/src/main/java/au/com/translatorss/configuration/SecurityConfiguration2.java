package au.com.translatorss.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices;

import au.com.translatorss.security.configuration.CustomAuthenticationProvider;
import au.com.translatorss.security.configuration.CustomAuthenticationSuccessHandler;
import au.com.translatorss.security.configuration.CustomSimpleUrlAuthenticationFailureHandler;

public class SecurityConfiguration2 extends WebSecurityConfigurerAdapter {

	@Autowired
	UserDetailsService userDetailsService;
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		// authenticationProviderList.add(rememberMeAuthenticationProvider());
		auth.authenticationProvider(customAuthenticationProvider());
		// auth.authenticationProvider(rememberMeAuthenticationProvider());
		super.configure(auth);
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Bean
	public SessionRegistry sessionRegistry() {
		return new SessionRegistryImpl();
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/resources/**", "/static/**");
	}

	@Bean
	public CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler() {
		CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler = new CustomAuthenticationSuccessHandler();
		return customAuthenticationSuccessHandler;
	}

	@Bean
	public CustomSimpleUrlAuthenticationFailureHandler customSimpleUrlAuthenticationFailureHandler2() {
		CustomSimpleUrlAuthenticationFailureHandler customSimpleUrlAuthenticationFailureHandler = new CustomSimpleUrlAuthenticationFailureHandler("/businessUserForm");
		return customSimpleUrlAuthenticationFailureHandler;
	}

	@Bean
	public CustomAuthenticationProvider customAuthenticationProvider() {
		return new CustomAuthenticationProvider();
	}

	@Bean
	public TokenBasedRememberMeServices tokenBasedRememberMeServices() {
		TokenBasedRememberMeServices tokenBasedRememberMeServices = new TokenBasedRememberMeServices("rememberme-key",userDetailsService);
		tokenBasedRememberMeServices.setAlwaysRemember(false);
		tokenBasedRememberMeServices.setTokenValiditySeconds(30000);
		tokenBasedRememberMeServices.setCookieName("remembermecookie");
		tokenBasedRememberMeServices.setParameter("_spring_security_remember_me");
		return tokenBasedRememberMeServices;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// Change to true, it will block other user to login with same
		// id/password
		http.sessionManagement().maximumSessions(1).expiredUrl("/").maxSessionsPreventsLogin(false)
				.sessionRegistry(sessionRegistry());

		http.authorizeRequests()

				.and().authorizeRequests()
				.antMatchers("/").permitAll()
			    .antMatchers("/admin/**").access("hasRole('ADMIN')")
			    .antMatchers("/customer/**").access("hasRole('EMPLOYER')")
			    .antMatchers("/translator/**").access("hasRole('TRANSLATOR')")
				.and().formLogin().loginPage("/businessUserForm").usernameParameter("email").passwordParameter("password")
				.loginProcessingUrl("/j_spring_security_check2").successHandler(customAuthenticationSuccessHandler())
				.failureHandler(customSimpleUrlAuthenticationFailureHandler2()).and().rememberMe().key("rememberme-key")
				.rememberMeServices(tokenBasedRememberMeServices()).and().csrf().disable();

		http.exceptionHandling().accessDeniedPage("/403");

	}
	
}
