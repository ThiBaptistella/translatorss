package au.com.translatorss.service;

import au.com.translatorss.bean.User;
import au.com.translatorss.bean.dto.CurrentUser;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
public class AuthorityHelper {

    private static final String ROLE_AUTHORITY_TPL = "ROLE_%s";

    private Collection<? extends GrantedAuthority> getGrantedAuthorities(User user) {

        List<GrantedAuthority> userAuthorities = new ArrayList<GrantedAuthority>();
        userAuthorities.add(createAuthority(ROLE_AUTHORITY_TPL, user.getRole()));
        return userAuthorities;
    }

    public void updateGrantedAuthoritiesForCurrentUser(User user) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        Collection<? extends GrantedAuthority> grantedAuthorities = getGrantedAuthorities(user);

        Authentication authentication = new UsernamePasswordAuthenticationToken(
                new CurrentUser(user, grantedAuthorities),
                user.getPassword());

        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    private SimpleGrantedAuthority createAuthority(String tpl, Object value) {
        return new SimpleGrantedAuthority(
                String.format(tpl, value.toString())
        );
    }
}