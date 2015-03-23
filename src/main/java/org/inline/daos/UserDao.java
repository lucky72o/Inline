package org.inline.daos;

import org.inline.entities.InlineUser;
import org.inline.entities.UserData;
import org.inline.entities.UserRole;

public interface UserDao {
    InlineUser getUserByName(String userName);

    void saveNewUser(InlineUser user);

    void saveNewUserData(UserData userData);

    void saveNewUserRole(UserRole role);
}
