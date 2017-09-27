package au.com.translatorss.bean.dto;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

import au.com.translatorss.bean.User;

public class CurrentUser extends org.springframework.security.core.userdetails.User {

	private static final long serialVersionUID = 1L;
	private User user;

	public CurrentUser(User user, Collection<? extends GrantedAuthority> collection) {
		super(user.getEmail(), user.getPassword(), collection);
		this.user = user;
	}

	public User getUser() {
		return user;
	}

	public Long getId() {
		return user.getId();
	}

}