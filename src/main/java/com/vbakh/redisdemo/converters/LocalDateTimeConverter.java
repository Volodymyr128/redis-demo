package com.vbakh.redisdemo.converters;

import org.apache.commons.beanutils.Converter;
import java.time.LocalDateTime;

/**
 * Created by volodymyr.bakhmatiuk on 5/31/18.
 */
public class LocalDateTimeConverter implements Converter {

    @Override
    public <T> T convert(Class<T> aClass, Object o) {
        if (aClass != LocalDateTime.class) {
            throw new IllegalArgumentException(this.getClass() + " is able to convert only " + LocalDateTime.class);
        }
        return (T) LocalDateTime.parse(o.toString());
    }
}
