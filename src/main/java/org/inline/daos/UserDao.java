package org.inline.daos;

import org.inline.entities.InlineUser;

public interface UserDao {
    InlineUser getUserByName(String userName);
}
