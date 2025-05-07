package ru.spb.tksoft.utils.string;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * Useful string utilities.
 *
 * @author Konstantin Terskikh, kostus.online.1974@yandex.ru, 2025
 */
public final class StringEx {

    /** 'Space' constant. */
    public static final char SPACE = ' ';

    private StringEx() {}

    /**
     * Sequentially replaces occurrences of substrings matching the pattern "{...}" with the
     * provided values. Example: str = "{host}:{port}/{app}", objects = "localhost", 8080, "school"
     * will be transformed into "localhost:8080/school". Useful for creating text templates, such as
     * constructing URLs in tests.
     *
     * @param str The source string containing placeholders (e.g., "http://{host}:{port}/{app}").
     * @param objects An array of objects/values to replace placeholders (e.g., "myhost", 7654,
     *        "mycoolapp").
     * @return The resulting string with placeholders replaced (e.g.,
     *         "http://myhost:7654/mycoolapp").
     */
    @NotNull
    public static String replace(String str, Object... objects) {

        if (null == str || str.isEmpty()) {
            return "";
        }

        for (Object object : objects) {
            if (null != object) {
                str = str.replaceFirst("\\{[^}]*}", object.toString());
            }
        }
        return str;
    }

    /**
     * Remove adjacent spaces.
     * 
     * @param rawSource Source string.
     * @return Trimmed string.
     */
    @NotNull
    public static String removeAdjacentSpaces(@NotBlank final String rawSource) {

        if (null == rawSource) {
            return "";
        }

        final String source = rawSource.trim();
        if (source.isEmpty()) {
            return "";
        }

        final var sb = new StringBuilder();
        final int count = source.length();

        char lastAppended = 0;
        for (int i = 0; i < count; i++) {
            char currentChar = source.charAt(i);

            if (currentChar != SPACE || lastAppended != SPACE) {
                sb.append(currentChar);
            }
            lastAppended = currentChar;
        }

        return sb.toString();
    }

    /**
     * Attempts to normalize date/time-like strings (e.g., "1:3:23", "1/2/25", "2023-9-8") into a
     * format where each numeric field has two digits. Examples: - "1:3:23" → "01:03:23" - "1/2/25"
     * → "01/02/25" - "2023-9-8" → "2023-09-08".
     * 
     * @param input The input string to normalize (date/time-like format).
     * @param delimiter The delimiter used in the input (e.g., ":", "/", "-").
     * @param pattern The target date format pattern (see {@link SimpleDateFormat}).
     * @return Normalized string with two-digit fields for each numeric component.
     */
    @NotNull
    public static String normalizeDateTime(@NotBlank final String input,
            @NotBlank final String delimiter, @NotBlank final String pattern) {

        if (null == input || input.isBlank() || null == pattern || pattern.isBlank()) {
            return "";
        }

        try {
            final List<String> parts = Arrays.asList(input.split("\\" + delimiter));
            final String str = parts.stream()
                    .map(part -> isSingleDigit(part) ? "0" + part : part)
                    .collect(Collectors.joining(delimiter));

            final var dateFormat = new SimpleDateFormat(pattern);
            final Date date = dateFormat.parse(str);

            return dateFormat.format(date);

        } catch (Exception e) {
            return "";
        }
    }

    /**
     * Checks if the given string consists solely of digits and has a specific length.
     * 
     * @param str The string to check.
     * @param length The expected length of the string.
     * @return {@code true} if the string is composed only of digits and has the specified length,
     *         otherwise {@code false}.
     */
    public static boolean isDigitsOnly(@NotBlank final String str, final int length) {
        return str.chars().allMatch(Character::isDigit) && str.length() == length;
    }

    /**
     * Checks if the given string consists solely of digits and has exactly one digit.
     * 
     * @param str The string to check.
     * @return {@code true} if the string is composed only of digits and has exactly one digit,
     *         otherwise {@code false}.
     */
    public static boolean isSingleDigit(@NotBlank final String str) {
        return isDigitsOnly(str, 1);
    }
}
