package org.inline.init;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.inline.entities.InlineUser;
import org.inline.entities.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;


/**
 *
 * This is a initializing bean that inserts some test data in the database. It is only active in
 * the development profile, to see the data login with user123 / PAssword2
 */
@Component
public class TestDataInitializer {

    @Autowired
    private SessionFactory sessionFactory;


    public void init() throws Exception {

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        InlineUser user = new InlineUser();
        user.setEnabled(true);
        user.setUsername("user123");
        user.setPassword("$2a$10$x9vXeDsSC2109FZfIJz.pOZ4dJ056xBpbesuMJg3jZ.ThQkV119tS");

        UserRole userRole = new UserRole();
        userRole.setRole("ROLE_USER");
        userRole.setUser(user);
        Set<UserRole> userRoles = new HashSet<>();
        userRoles.add(userRole);

        user.setUserRole(userRoles);

        session.persist(user);

        transaction.commit();
    }
}
