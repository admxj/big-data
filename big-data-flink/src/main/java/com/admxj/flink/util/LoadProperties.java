package com.admxj.flink.util;

import com.google.common.collect.Sets;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Properties;

/**
 * @author admxj
 * @version Id: LoadProperties, v 0.1 2020/9/26 1:34 上午 admxj Exp $
 */
public class LoadProperties {

    private static final HashSet<String> INT_TYPE = Sets.newHashSet("int", "java.lang.Integer");
    private static final HashSet<String> STRING_TYPE = Sets.newHashSet("java.lang.String");
    private static final HashSet<String> LONG_TYPE = Sets.newHashSet("long", "java.lang.Long");

    public static <T> T loadProperties(Class<T> tClass, Properties properties) {
        T instance = null;
        try {
            instance = tClass.newInstance();
            Field[] fields = tClass.getDeclaredFields();
            for (Field field : fields) {
                PropertyKey propertyKey = field.getAnnotation(PropertyKey.class);
                if (null == propertyKey) {
                    continue;
                }
                Method method = tClass.getMethod(buildSetMethod(field.getName()), field.getType());
                String value = properties.getProperty(propertyKey.value());
                method.invoke(instance, typeTransfer(field.getType(), value));
            }
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return instance;
    }

    public static String buildSetMethod(String fieldName) {
        return "set" + (fieldName.charAt(0) + "").toUpperCase() + fieldName.substring(1);
    }

    public static Object typeTransfer(Class<?> fieldType, String strVal) {

        if (LONG_TYPE.contains(fieldType.getName())) {
            return Long.parseLong(strVal);
        } else if (INT_TYPE.contains(fieldType.getName())) {
            return Integer.parseInt(strVal);
        } else if (STRING_TYPE.contains(fieldType.getName())) {
            return strVal;
        }
        return null;
    }

}
