package org.inline.exceptions;

public class UserNotFoundException extends AbstractException {

    public UserNotFoundException(String description) {
        super(description);
    }

    public UserNotFoundException() {
    }
}
