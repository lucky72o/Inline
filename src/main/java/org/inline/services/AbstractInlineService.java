package org.inline.services;

import org.inline.daos.UserDao;
import org.inline.entities.InlineUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Properties;

public abstract class AbstractInlineService {

    @Autowired
    protected UserDao userDao;

    @Autowired
    private Properties myProperties;

    public String getCurrentUserName() {
        User userEmail = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String userId = userEmail.getUsername();
        return userId;
    }

    @Transactional
    public InlineUser getCurrentUser() {
        String userId = getCurrentUserName();
        if (getCurrentUserName() != null) {
            return userDao.getUserByName(userId);
        }
        return null;
    }

    public boolean isCurrentUserAdmin() {
        User currentSpringUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Collection<GrantedAuthority> grantedAuthorities = currentSpringUser.getAuthorities();

        String roleAdmin = myProperties.getProperty("inline.admin.role");

        for (GrantedAuthority authority : grantedAuthorities) {
            if (roleAdmin.equals(authority.getAuthority())) {
                return true;
            }
        }

        return false;
    }
}
