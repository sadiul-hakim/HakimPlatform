package xyz.sadiulhakim.util;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeUtil {

    private static final DateTimeFormatter DEFAULT_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss a z");

    private DateTimeUtil() {
    }

    public static String format(ZonedDateTime dateTime) {
        return DEFAULT_FORMATTER.format(dateTime);
    }

    public static String now() {
        ZonedDateTime now = ZonedDateTime.now();
        return format(now);
    }
}
