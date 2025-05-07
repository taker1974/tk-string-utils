package ru.spb.tksoft.utils.string;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

/**
 * Tests were developed using the DeepSeek neural network and DeepThink (R1).
 */
class StringExRemoveAdjacentSpacesTest {

    @Test
    void removeAdjacentSpaces_ShouldTrimAndCollapseSpaces() {
        String result = StringEx.removeAdjacentSpaces("  Hello   world  ");
        assertThat(result).isEqualTo("Hello world");
    }

    @Test
    void removeAdjacentSpaces_WhenInputIsNull_ShouldReturnEmpty() {
        String result = StringEx.removeAdjacentSpaces(null);
        assertThat(result).isEmpty();
    }

    @ParameterizedTest
    @CsvSource({
        "'   ', ''",
        "'a   b   c', 'a b c'",
        "'2023-09-08', '2023-09-08'"
    })
    void removeAdjacentSpaces_ParameterizedCases(String input, String expected) {
        assertThat(StringEx.removeAdjacentSpaces(input)).isEqualTo(expected);
    }
}
