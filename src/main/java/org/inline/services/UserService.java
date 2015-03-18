package org.inline.services;

import org.inline.entities.InlineUser;

public interface UserService {
    InlineUser getUserByName(String userName);
}
