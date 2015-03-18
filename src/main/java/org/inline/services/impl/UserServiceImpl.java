package org.inline.services.impl;

import org.inline.entities.InlineUser;
import org.inline.services.UserService;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Override
    public InlineUser getUserByName(String userName) {
        return null;
    }
}
