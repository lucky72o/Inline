package org.inline.exceptions;

public class ConversionException extends RuntimeException {
    private static final long serialVersionUID = -37653689639576411L;

    public ConversionException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public ConversionException(String msg) {
        super(msg);
    }
}

