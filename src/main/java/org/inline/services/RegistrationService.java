package org.inline.services;


import org.inline.exceptions.UserNotFoundException;
import org.inline.forms.RegistrationForm;

import java.util.DuplicateFormatFlagsException;
import java.util.List;

public interface RegistrationService {
    void createNewUser(RegistrationForm registrationForm, List<String> roles) throws DuplicateFormatFlagsException;

    void activateUser(String userId) throws UserNotFoundException;

    boolean checkIfUserAlreadyExist(String userId);

    void requestPasswordReset(String userId) throws UserNotFoundException;

    public void updateUserPassword(String userId, String password) throws UserNotFoundException;

}
