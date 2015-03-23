package org.inline.services.impl;

import org.apache.log4j.Logger;
import org.inline.daos.UserDao;
import org.inline.entities.InlineUser;
import org.inline.entities.UserData;
import org.inline.entities.UserRole;
import org.inline.exceptions.DuplicateUserException;
import org.inline.exceptions.UserNotFoundException;
import org.inline.forms.RegistrationForm;
import org.inline.services.AbstractInlineService;
import org.inline.services.MailSenderService;
import org.inline.services.RegistrationService;
import org.inline.services.TokenGenerationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service("registrationService")
public class RegistrationServiceImpl extends AbstractInlineService implements RegistrationService {

    private static final Logger LOG = Logger.getLogger(RegistrationServiceImpl.class);

    @Autowired
    private UserDao userDao;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired
    private TokenGenerationService tokenGenerationService;

    @Autowired
    MailSenderService mailSenderService;

    @Override
    @Transactional
    public void createNewUser(RegistrationForm registrationForm, List<String> roles) throws DuplicateUserException {
        LOG.info("Create new user with username: " + registrationForm.getUsername());

        if (checkIfUserAlreadyExist(registrationForm.getUsername())) {
            throw new DuplicateUserException("User with email: " + registrationForm.getEmail() + " already exist");
        }

        try {
            InlineUser user = new InlineUser();
            user.setEnabled(false);
            user.setUsername(registrationForm.getUsername());
            user.setPassword(encoder.encode(registrationForm.getPassword()));
            user.setToken(tokenGenerationService.generateToken(registrationForm.getEmail().toLowerCase(), Long.toString(System.currentTimeMillis())));

            Set<UserRole> userRoles = new HashSet<>();

            for (String role : roles) {
                UserRole userRole = new UserRole();
                userRole.setUser(user);
                userRole.setRole(role);
                userRoles.add(userRole);
            }

            user.setUserRole(userRoles);

            UserData userData = new UserData();
            userData.setUser(user);
            userData.setEmail(registrationForm.getEmail().toLowerCase());
            userData.setPhone(registrationForm.getPhone());
            userData.setName(registrationForm.getName());
            userData.setSurname(registrationForm.getSurname());

            user.setUserData(userData);

            userDao.saveNewUser(user);
            userDao.saveNewUserData(userData);

            for (UserRole role : userRoles) {
                userDao.saveNewUserRole(role);
            }

//            mailSenderService.sendRegistrationEmail(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean checkIfUserAlreadyExist(String userId) {
        InlineUser user = userDao.getUserByName(userId);
        return user != null;
    }

    @Override
    @Transactional
    public void requestPasswordReset(String userId) throws UserNotFoundException {
        InlineUser user = userDao.getUserByName(userId);

        if (user == null) {
            throw new UserNotFoundException("Пользователь с электронной почтой " + userId + " не найден");
        }

        String token = tokenGenerationService.generateToken(userId.toLowerCase(), Long.toString(System.currentTimeMillis()));
//        userDao.requestPasswordReset(userId, token);
        user.setToken(token);

//        mailSenderService.setPasswordReminder(user);
    }

    @Override
    @Transactional
    public void activateUser(String userId) throws UserNotFoundException {
        if (userDao.getUserByName(userId) == null) {
            throw new UserNotFoundException("Пользователь с электронной почтой: " + userId + " не найден!");
        }
//        userDao.activateUser(userId.toLowerCase());
    }

    @Override
    @Transactional
    public void updateUserPassword(String userId, String password) throws UserNotFoundException {
        if (userDao.getUserByName(userId) == null) {
            throw new UserNotFoundException("Пользователь с электронной почтой: " + userId + " не найден!");
        }
//        userDao.updateUserPassword(userId.toLowerCase(), encoder.encode(password));
    }
}
