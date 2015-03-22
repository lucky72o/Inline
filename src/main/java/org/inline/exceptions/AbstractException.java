package org.inline.exceptions;

public class AbstractException extends RuntimeException {
    protected String description;

    public AbstractException(String description) {
        this.description = description;
    }

    public AbstractException() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
