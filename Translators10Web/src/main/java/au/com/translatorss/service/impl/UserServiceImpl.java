package au.com.translatorss.service.impl;

import au.com.translatorss.bean.Customer;
import au.com.translatorss.bean.Translator;
import au.com.translatorss.bean.User;
import au.com.translatorss.bean.dto.CurrentUser;
import au.com.translatorss.dao.CustomerDao;
import au.com.translatorss.dao.UserDao;
import au.com.translatorss.enums.Role;
import au.com.translatorss.service.TranslatorSettingsService;
import au.com.translatorss.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserDetailsService, UserService {

	@Autowired
	private UserDao userDao;


    @Autowired
    private TranslatorSettingsService translatorService;

    @Autowired
    private CustomerDao customerDao;

    @Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = userDao.getByEmail(email);
		return user == null ? null : new CurrentUser(user, getAuthorities(user));
	}

	public Collection<? extends GrantedAuthority> getAuthorities(User user) {
		return user.getGrantedAuthorities();
	}

	public User getById(Long id) {
		return userDao.getById(id);
	}

	@Override
	public void saveOrUpdate(User user) {
		userDao.saveOrUpdate(user);
	}

    @Override
    public User getByEmail(String email) {
        return userDao.getByEmail(email);
    }

    @Override
    public Long getUserIdByEmail(String email) {
        return userDao.getUserIdByEmail(email);
    }

    @Override
    public Long getUserIdByRole(User user) {
        Long id;
        String email = user.getEmail().trim();
        if (user.getRole() == Role.TRANSLATOR) {
            Translator translator = translatorService.getTranslatorByEmail(email);
            id = translator.getUser().getId();
        } else if (user.getRole() == Role.CLIENT) {
            Customer customer = customerDao.getByEmail(email);
            id = customer.getUser().getId();
        } else {
            id = user.getId();
        }
        return id;
    }

    @Override
    public User getCurrentUser() {
        User currentUserOrNull = getCurrentUserOrNull();
        if (currentUserOrNull == null) {
            throw new IllegalAccessError("User is not authorized");
        }
        return currentUserOrNull;
    }

    @Override
    public User getCurrentUserOrNull() {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        if (authentication != null) {
            Object principal = authentication.getPrincipal();
            if ((principal instanceof CurrentUser)) {
                CurrentUser details = (CurrentUser) principal;

                if (details.getUser() != null) {
                    return getById(details.getUser().getId());
                }

                return details.getUser();
            }
        }

        return null;
    }

    @Override
    public boolean isExistEmail(String email) {
        return userDao.isExistEmail(email);
    }

    @Override
    public boolean isAvailableEmailAndExcludeUser(String email, Long id) {
        return userDao.isAvailableEmailAndExcludeUser(email, id);
    }

    public List<User> findAll(){
        return userDao.findAll();
    }

}