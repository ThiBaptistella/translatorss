package au.com.translatorss.service;

import au.com.translatorss.bean.User;

public interface UserService {

	public User getById(Long id);
	
	public User getByEmail(String email);
	
	public void saveOrUpdate(User use);

	Long getUserIdByEmail(String email);

	Long getUserIdByRole(User user);

    User getCurrentUser();

	User getCurrentUserOrNull();

	boolean isExistEmail(String email);

	boolean isAvailableEmailAndExcludeUser(String email, Long id);
}
