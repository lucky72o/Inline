package org.inline.converters;

import org.inline.exceptions.ConversionException;

public interface Converter<SOURCE, TARGET> {

    void convert(SOURCE source, TARGET target) throws ConversionException;
}
