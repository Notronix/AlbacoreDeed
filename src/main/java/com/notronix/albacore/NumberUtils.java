package com.notronix.albacore;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.apache.commons.lang3.StringUtils.isBlank;

public abstract class NumberUtils
{
    private static final Pattern pattern = Pattern.compile("[\\D]*([\\d,]+\\.[\\d]{1,2}).*$");

    public static int intValueOf(Object object) throws NullPointerException, IllegalArgumentException {
        if (object == null) {
            throw new NullPointerException("object is null.");
        }

        if (object instanceof Number) {
            return ((Number) object).intValue();
        }

        throw new IllegalArgumentException(object.getClass().getSimpleName() + " is not a Number instance.");
    }

    public static long longValueOf(Object object) {
        if (object == null) {
            throw new NullPointerException("object is null.");
        }

        if (object instanceof Number) {
            return ((Number) object).longValue();
        }

        throw new IllegalArgumentException(object.getClass().getSimpleName() + " is not a Number instance.");
    }

    public static double doubleValueOf(Object object) throws IllegalArgumentException {
        if (object == null) {
            throw new NullPointerException("object is null.");
        }

        if (object instanceof Number) {
            return ((Number) object).doubleValue();
        }

        throw new IllegalArgumentException(object.getClass().getSimpleName() + " is not a Number instance.");
    }

    /*
     * A utility method to parse a double value from many different types of strings that may contain a number.
     *
     * The following examples...
     *
     * <code>
     *     <pre>
     *     parseDoubleValue("2")
     *     parseDoubleValue("2.0")
     *     parseDoubleValue("$2.0")
     *     parseDoubleValue("Price: $2.00")
     *     parseDoubleValue("Price: $2.0 USD")
     *     </pre>
     * </code>
     *
     * ... will all return a Double value of 2
     *
     * @param value the value that may contain a double value.
     * @return a double value representing a number found in the provided value.
     * @throws ParseException if no double value is found or if there are errors while parsing the provided value.
     */
    public static Double parseDoubleValue(String value) throws ParseException {
        if (isBlank(value)) {
            throw new ParseException("empty value");
        }

        try {
            return Double.parseDouble(value);
        }
        catch (Exception e) {
            Matcher matcher = pattern.matcher(value);
            if (matcher.matches()) {
                try {
                    return Double.parseDouble(matcher.group(1).replaceAll(",", ""));
                }
                catch (Exception ex) {
                    throw new ParseException("Failed to parse double value.", ex);
                }
            }
        }

        throw new ParseException("Unable to parse any double value.");
    }
}
