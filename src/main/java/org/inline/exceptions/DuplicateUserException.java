package org.inline.exceptions;

public class DuplicateUserException extends AbstractException {

    public DuplicateUserException(String description) {
        super(description);
    }

    public DuplicateUserException() {
    }
}
