package com.vbakh.redisdemo.converters;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.ConvertUtilsBean;
import org.apache.commons.beanutils.PropertyUtilsBean;
import org.springframework.beans.BeanUtils;
import org.springframework.data.redis.hash.HashMapper;
import java.lang.reflect.InvocationTargetException;
import java.time.LocalDateTime;
import java.util.Map;

/**
 * Created by volodymyr.bakhmatiuk on 5/31/18.
 * {@link org.springframework.data.redis.hash.BeanUtilsHashMapper} fails to convert {@link java.time.LocalDateTime} from String.
 */
public class DTOConverter<T> implements HashMapper<T, String, String> {

    private final Class<T> type;

    public DTOConverter(Class<T> type) {
        this.type = type;
    }

    @Override
    public Map<String, String> toHash(T t) {
        throw new UnsupportedOperationException();
    }

    @Override
    public T fromHash(Map<String, String> map) {
        Object message = BeanUtils.instantiateClass(this.type);
        ConvertUtilsBean convertUtilsBean = new ConvertUtilsBean();
        convertUtilsBean.deregister(LocalDateTime.class);
        convertUtilsBean.register(new LocalDateTimeConverter(), LocalDateTime.class);
        BeanUtilsBean beanUtilsBean = new BeanUtilsBean(convertUtilsBean, new PropertyUtilsBean());

        try {
            beanUtilsBean.populate(message, map);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
        return (T) message;
    }
}
