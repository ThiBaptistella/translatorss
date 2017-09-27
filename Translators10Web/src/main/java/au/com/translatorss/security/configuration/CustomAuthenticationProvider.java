package au.com.translatorss.security.configuration;

import au.com.translatorss.bean.dto.CurrentUser;
import au.com.translatorss.service.LogintryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Collection;

public class CustomAuthenticationProvider implements AuthenticationProvider {
	private static int MAX_LOGIN_TRIES = 10;
	@Autowired
	UserDetailsService userDetailservice;

	@Autowired
	LogintryService logintryService;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String username = authentication.getName();
		String password = (String) authentication.getCredentials();
		CurrentUser user = (CurrentUser) userDetailservice.loadUserByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("User Name Not Valid");
		}
		if (logintryService.getTriesByUser(user.getId()) >= MAX_LOGIN_TRIES) {
			throw new InsufficientAuthenticationException("You have reached "+MAX_LOGIN_TRIES+" wrong password limit");
		}

		// if (!user.getPassword().equals(new
		// BCryptPasswordEncoder().encode(password))) {
		if (!user.getPassword().trim().equals(password.trim())) {
			logintryService.IncreaseTries(user.getUser());
			throw new AuthenticationCredentialsNotFoundException("Password Not Valid");
		}

		Collection<? extends GrantedAuthority> authorities = user.getAuthorities();
		return new UsernamePasswordAuthenticationToken(user, password, authorities);
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
	}
}