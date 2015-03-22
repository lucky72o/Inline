package org.inline.services;


import org.inline.exceptions.UserNotFoundException;

public interface TokenGenerationService {

    String generateToken(String userId, String timestamp);
    boolean tokenIsValid(String userId, String token) throws UserNotFoundException;
    boolean tokenNotExpired(String token);
    boolean checkTokenAgainstDB(String userId, String token) throws UserNotFoundException;
}
