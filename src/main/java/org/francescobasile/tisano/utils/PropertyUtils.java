package org.francescobasile.tisano.utils;

import java.lang.reflect.Field;
import java.util.Objects;

public class PropertyUtils {

    public static String verifyFieldName(Object o, String campo) {
        String attributo = null;
        final Field[] declaredFields = o.getClass().getDeclaredFields();
        for (Field field : declaredFields) {
            if (field.getName().equals(campo)) {
                attributo = field.getName();
            }
        }
        if (Objects.isNull(attributo)) {
            throw new IllegalArgumentException();
        }
        return attributo;
    }

    public static String extractFieldName() {
        StackTraceElement[] traceElements = Thread.currentThread().getStackTrace();
        return "?";
    }
}
