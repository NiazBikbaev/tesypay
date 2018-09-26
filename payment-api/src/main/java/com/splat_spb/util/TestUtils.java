package com.splat_spb.util;


import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringUtils;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.time.Instant;
import java.util.Arrays;
import java.util.Collection;
import java.util.Currency;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.UUID;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.lang.Math.abs;

/**
 * Test utils.
 *
 * @author Niyaz Bikbaev
 */
@SuppressWarnings("OptionalGetWithoutIsPresent")
public class TestUtils {

    private static final Random RANDOM = new Random();

    /**
     * Populate object in non-recursive manner.
     *
     * @param source source object
     * @param <T>    source type
     * @return populated object
     */
    public static <T> T populate(T source) {
        return populate(source, false);
    }

    /**
     * Populate object in non-recursive manner.
     *
     * @param source source object supplier
     * @param <T>    source type
     * @return populated object
     */
    public static <T> T populate(Supplier<T> source) {
        return populate(source.get(), false);
    }

    /**
     * Populate object.
     *
     * @param source      source object supplier
     * @param recursively if true, populates custom non-primitive types objects
     * @param <T>         source type
     * @return populated object
     */
    public static <T> T populate(Supplier<T> source, boolean recursively) {
        return populate(source.get(), recursively);
    }

    /**
     * Populate object.
     *
     * @param source      source object
     * @param recursively if true, populates custom non-primitive types objects
     * @param <T>         source type
     * @return populated object
     */
    public static <T> T populate(T source, boolean recursively) {
        Arrays.stream(source.getClass().getDeclaredFields())
              .map(TestUtils::makeFieldAccessible)
              .filter(field -> isEmpty(ReflectionUtils.getField(field, source)))
              .forEach(field ->
                      ReflectionUtils.setField(field, source, generateValue(field, recursively)));
        return source;
    }

    private static boolean isEmpty(final Object value) {
        if (value == null) {
            return true;
        }
        if (value instanceof Collection<?>) {
            final Collection<?> collection = (Collection<?>) value;
            return collection.isEmpty();
        } else if (value instanceof Map<?, ?>) {
            final Map<?, ?> map = (Map<?, ?>) value;
            return map.isEmpty();
        } else if (value instanceof Integer) {
            final int intValue = (int) value;
            return intValue == 0;
        } else if (value instanceof Double) {
            final double doubleValue = (double) value;
            return doubleValue == 0;
        } else if (value instanceof Long) {
            final long longValue = (long) value;
            return longValue == 0;
        } else if (value instanceof String) {
            final String stringValue = (String) value;
            return StringUtils.isEmpty(stringValue);
        } else if (value.getClass().isArray()) {
            return Array.getLength(value) == 0;
        } else if (value instanceof Boolean) {
            final boolean booleanValue = (boolean) value;
            return !booleanValue;
        } else {
            return Arrays
                    .stream(value.getClass().getDeclaredFields())
                    .map(TestUtils::makeFieldAccessible)
                    .map(field -> isEmpty(ReflectionUtils.getField(field, value)))
                    .reduce((a, b) -> a || b)
                    .orElse(false);
        }
    }

    private static Field makeFieldAccessible(Field field) {
        field.setAccessible(true);
        return field;
    }

    private static Object generateValue(Field field, boolean recursively) {
        Class<?> type = field.getType();
        return instantiate(type, field, recursively);
    }

    private static Object instantiate(Class<?> type, Field field, boolean recursively) {
        if (type.isEnum()) {
            Object[] enumValues = type.getEnumConstants();
            return enumValues[RANDOM.nextInt(enumValues.length)];
        } else if (type.equals(Integer.class) || type.equals(Integer.TYPE)) {
            return abs(RANDOM.nextInt());
        } else if (type.equals(Long.class) || type.equals(Long.TYPE)) {
            return abs(RANDOM.nextLong());
        } else if (type.equals(Double.class) || type.equals(Double.TYPE)) {
            return abs(RANDOM.nextDouble());
        } else if (type.equals(Float.class) || type.equals(Float.TYPE)) {
            return abs(RANDOM.nextFloat());
        } else if (type.equals(String.class)) {
            String uuid = UUID.randomUUID().toString();
            if (field.getName().toLowerCase().contains("email")) {
                return uuid.replace("-", "") + "@test.com";
            }
            if (field.getName().toLowerCase().contains("currency")) {
                return Currency.getAvailableCurrencies().stream()
                               .findAny()
                               .map(Currency::toString)
                               .get();
            }
            return UUID.randomUUID().toString();
        } else if (type.equals(Boolean.TYPE) || type.equals(Boolean.class)) {
            return RANDOM.nextBoolean();
        } else if (type.equals(Instant.class)) {
            return Instant.now();
        } else if (Set.class.equals(type)) {
            Class<?> generic = getParameterizedType(field);
            return Stream.generate(() -> instantiate(generic, field, recursively)).limit(5)
                         .collect(Collectors.toSet());
        } else {
            try {
                if (recursively) {
                    return populate(type.newInstance(), true);
                }
                return null;
            } catch (InstantiationException | IllegalAccessException e) {
                throw new RuntimeException(String.format(
                        "There is no public constructor for type %s",
                        type
                ));
            }
        }
    }

    /**
     * Returns parameterized type of field.
     *
     * <p>In case of collections returns only first parameterized type.
     *
     * @param field object field
     * @return field generic type
     */
    private static Class<?> getParameterizedType(Field field) {
        ParameterizedType genericType = (ParameterizedType) field.getGenericType();
        return (Class<?>) (genericType.getActualTypeArguments()[0]);
    }

}
