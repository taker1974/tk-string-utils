package ru.spb.tksoft.utils.string;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

/**
 * Tests were developed using the DeepSeek neural network and DeepThink (R1).
 */
class StringExDigitsCheckTest {

    @Test
    void isDigitsOnly_WhenValid_ShouldReturnTrue() {
        assertThat(StringEx.isDigitsOnly("12345", 5)).isTrue();
    }

    @Test
    void isDigitsOnly_WhenNonDigits_ShouldReturnFalse() {
        assertThat(StringEx.isDigitsOnly("12a45", 5)).isFalse();
    }

    @Test
    void isSingleDigit_WhenValid_ShouldReturnTrue() {
        assertThat(StringEx.isSingleDigit("7")).isTrue();
    }

    @ParameterizedTest
    @CsvSource({
        "'', false",
        "'a', false",
        "'12', false",
        "'5', true"
    })
    void isSingleDigit_ParameterizedCases(String input, boolean expected) {
        assertThat(StringEx.isSingleDigit(input)).isEqualTo(expected);
    }
}