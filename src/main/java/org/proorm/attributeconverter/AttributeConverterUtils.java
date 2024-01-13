package org.proorm.attributeconverter;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.persistence.AttributeConverter;

// Lay kieu du lieu cua column thanh java
public class AttributeConverterUtils {

    public static Class<?> getConverterDatabaseType(AttributeConverter converter) {
        Class<?> converterClass = converter.getClass();
        try {
            JavaType converterType = new ObjectMapper().getTypeFactory().constructType(converterClass);
            JavaType t = new ObjectMapper().getTypeFactory().findTypeParameters(converterType,
                    AttributeConverter.class)[1];
            return t.getRawClass();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
