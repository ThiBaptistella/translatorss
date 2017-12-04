package au.com.translatorss.dao;

import au.com.translatorss.bean.User;

import java.util.List;

public interface UserDao {

	public void saveOrUpdate(User user);

	public User getByEmail(String email);

	public User getById(Long id);

    Long getUserIdByEmail(String email);

    boolean isExistEmail(String email);

    boolean isAvailableEmailAndExcludeUser(String email, Long id);

    public List<User> findAll();
}
