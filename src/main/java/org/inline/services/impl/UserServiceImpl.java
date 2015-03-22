package org.inline.services.impl;

import org.inline.daos.UserDao;
import org.inline.entities.InlineUser;
import org.inline.services.AbstractInlineService;
import org.inline.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("userService")
public class UserServiceImpl extends AbstractInlineService implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    @Transactional
    public InlineUser getUserByName(String userName) {
        InlineUser user = userDao.getUserByName(userName.toLowerCase());
        return user;
    }
}
