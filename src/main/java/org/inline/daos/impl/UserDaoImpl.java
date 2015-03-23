package org.inline.daos.impl;

import org.hibernate.SessionFactory;
import org.inline.daos.UserDao;
import org.inline.entities.InlineUser;
import org.inline.entities.UserData;
import org.inline.entities.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("userDao")
public class UserDaoImpl implements UserDao {

    public static final String GET_USER_BY_NAME_QUERY = "FROM InlineUser WHERE username=?";

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public InlineUser getUserByName(String userName) {
        InlineUser user = (InlineUser) sessionFactory.getCurrentSession()
                .createQuery(GET_USER_BY_NAME_QUERY)
                .setParameter(0, userName).uniqueResult();
        return user;
    }

    @Override
    public void saveNewUser(InlineUser user) {
        sessionFactory.getCurrentSession().persist(user);
    }

    @Override
    public void saveNewUserData(UserData userData) {
        sessionFactory.getCurrentSession().persist(userData);
    }

    @Override
    public void saveNewUserRole(UserRole role) {
        sessionFactory.getCurrentSession().persist(role);
    }
}
