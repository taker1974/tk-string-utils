package ru.spb.tksoft.utils.string;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

/**
 * Tests were developed using the DeepSeek neural network and DeepThink (R1).
 */
class StringExNormalizeDateTimeTest {

    @Test
    void normalizeDateTime_WithValidDate_ShouldFormatCorrectly() {
        String result = StringEx.normalizeDateTime("2023-9-8", "-", "yyyy-MM-dd");
        assertThat(result).isEqualTo("2023-09-08");
    }

    @Test
    void normalizeDateTime_WithInvalidDate_ShouldReturnEmpty() {
        String result = StringEx.normalizeDateTime("99:99:99", ":", "");
        assertThat(result).isEmpty();
    }

    @Test
    void normalizeDateTime_WithNonNumericParts_ShouldReturnEmpty() {
        String result = StringEx.normalizeDateTime("abc:def:ghi", ":", "HH:mm:ss");
        assertThat(result).isEmpty();
    }

    @ParameterizedTest
    @CsvSource({
            "'1:3:23', ':', 'HH:mm:ss', '01:03:23'",
            "'1/2/25', '/', 'MM/dd/yy', '01/02/25'",
            "'5.7.2023', '.', 'dd.MM.yyyy', '05.07.2023'"
    })
    void normalizeDateTime_ParameterizedValidCases(
            String input, String delimiter, String pattern, String expected) {
        assertThat(StringEx.normalizeDateTime(input, delimiter, pattern)).isEqualTo(expected);
    }
}
