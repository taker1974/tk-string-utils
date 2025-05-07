package ru.spb.tksoft.utils.string;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

/**
 * Tests were developed using the DeepSeek neural network and DeepThink (R1).
 */
class StringExReplaceTest {

    @Test
    void replace_ShouldReplacePlaceholdersInOrder() {
        String result = StringEx.replace("{host}:{port}/{app}", "localhost", 8080, "school");
        assertThat(result).isEqualTo("localhost:8080/school");
    }

    @Test
    void replace_WhenInputIsNull_ShouldReturnEmpty() {
        String result = StringEx.replace(null, "value");
        assertThat(result).isEmpty();
    }

    @Test
    void replace_WhenObjectsAreNull_ShouldSkipNulls() {
        String result = StringEx.replace("{a}-{b}-{c}", null, "B", null);
        // null objects are just skipped so the resulting string will be "B-{b}-{c}"
        assertThat(result).isEqualTo("B-{b}-{c}");
    }

    @Test
    void replace_WithSpecialCharactersInPlaceholders() {
        String result = StringEx.replace("{user name}: {role}", "admin", "root");
        assertThat(result).isEqualTo("admin: root");
    }

    @ParameterizedTest
    @CsvSource({
            "'{a}{b}{c}', '1,2,3', '123'",
            "'{a}-{b}', 'X', 'X-{b}'",
            "'No placeholders', '', 'No placeholders'"
    })
    void replace_ParameterizedCases(String input, String objectsStr, String expected) {
        Object[] objects = objectsStr.isEmpty() ? new Object[0] : objectsStr.split(",");
        assertThat(StringEx.replace(input, objects)).isEqualTo(expected);
    }
}
