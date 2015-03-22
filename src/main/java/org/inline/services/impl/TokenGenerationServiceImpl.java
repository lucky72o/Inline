package org.inline.services.impl;

import org.inline.daos.UserDao;
import org.inline.entities.InlineUser;
import org.inline.exceptions.UserNotFoundException;
import org.inline.services.AbstractInlineService;
import org.inline.services.TokenGenerationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("tokenGenerationService")
public class TokenGenerationServiceImpl extends AbstractInlineService implements TokenGenerationService {

    @Autowired
    private UserDao userDao;

    private String salt = "Partner Consulting Site";
    private String separator = "z";

    private Long millisecondsBeforeTokenExpired = 86400000l;

    @Override
    public String generateToken(String userId, String timestamp) {
        Md5PasswordEncoder encoder = new Md5PasswordEncoder();
        String token = encoder.encodePassword(userId.toLowerCase() + timestamp, salt);
        token = token + separator + timestamp;
        System.out.println(token);
        return token;
    }

    @Override
    @Transactional
    public boolean tokenIsValid(String userId, String token) throws UserNotFoundException {
        Md5PasswordEncoder encoder = new Md5PasswordEncoder();
        String timestamp = token.substring(token.lastIndexOf(separator) + 1);
        String newToken = encoder.encodePassword(userId.toLowerCase() + timestamp, salt);
        newToken = newToken + separator + timestamp;

        if (!token.equals(newToken)) {
            return false;
        }
        return checkTokenAgainstDB(userId, token);
    }

    @Override
    public boolean tokenNotExpired(String token) {
        String timestamp = token.substring(token.lastIndexOf(separator) + 1);
        if ((System.currentTimeMillis() - new Long(timestamp)) < millisecondsBeforeTokenExpired) {
            return true;
        }
        return false;
    }

    @Override
    @Transactional
    public boolean checkTokenAgainstDB(String userId, String token) throws UserNotFoundException {
        InlineUser user = userDao.getUserByName(userId);

        if (userDao.getUserByName(userId) == null) {
            throw new UserNotFoundException("Пользователь с электронной почтой: " + userId + " не найден!");
        }

        return user.getToken().equals(token);
    }
}

