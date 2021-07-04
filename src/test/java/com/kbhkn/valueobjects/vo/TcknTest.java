package com.kbhkn.valueobjects.vo;

import com.kbhkn.valueobjects.testvalues.TcknTestValues;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit Tests for Tckn Value Object.
 *
 * @author Hakan KABASAKAL, 4-July-21
 */
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class TcknTest extends TcknTestValues {

    @ParameterizedTest
    @MethodSource(value = "validTcknValues")
    void givenValidTckn_whenUsingTcknOfMethod_thenCreatesValidVO(String tckn) {
        assertNotNull(Tckn.of(tckn));
    }

    @ParameterizedTest
    @MethodSource(value = "validTcknValues")
    void givenValidTckn_whenCallsValueMethod_thenShouldEqualInputtedValue(String tckn) {
        var tcknVO = Tckn.of(tckn);

        assertEquals(tckn, tcknVO.value());
    }

    @ParameterizedTest
    @MethodSource(value = "validTcknValues")
    void givenValidTcknButContainsEmptyCharacter_whenUsingTcknOfMethod_thenCreatesValidVO(String tckn) {
        var tcknContainsGap = addGap(tckn);

        assertNotNull(Tckn.of(tcknContainsGap));
    }

    @ParameterizedTest
    @MethodSource(value = "validTcknValues")
    void givenValidTcknButContainsEmptyChar_whenCallsValueMethod_thenShouldEqualInputtedAndTrimmedValue(String tckn) {
        var tcknContainsGap = addGap(tckn);

        var tcknVO1 = Tckn.of(tcknContainsGap);

        assertEquals(tcknContainsGap.trim(), tcknVO1.value());
    }

    @ParameterizedTest
    @MethodSource(value = "validTcknValues")
    void givenValidTckn_whenUsingAsLongMethod_thenShouldEqualExpectedValue(String tckn) {
        var tcknVO = Tckn.of(tckn);

        assertEquals(Long.parseLong(tckn), tcknVO.asLong());
    }

    @ParameterizedTest
    @MethodSource(value = "tcknWithMasked")
    void givenValidTckn_WhenMasked_ThenShouldEqualExpectedMaskedText(String validTckn, String expectedMasked) {
        var maskedTckn1 = Tckn.of(validTckn).masked();
        assertEquals(expectedMasked, maskedTckn1);
    }

    @ParameterizedTest
    @MethodSource(value = "validTcknValues")
    void givenValidTckn_whenCreates2DifferentTcknVO_thenShouldBeEqual(String tckn) {
        var arcTcknVO1 = Tckn.of(tckn);
        var arcTcknVO2 = Tckn.of(tckn);

        assertEquals(arcTcknVO1, arcTcknVO2);
    }

    @ParameterizedTest
    @MethodSource(value = "validTcknValues")
    void givenValidTckn_whenCreates2DifferentTcknVOAndCallsSameValueAsMethod_thenTrue(String tckn) {
        var arcTcknVO1 = Tckn.of(tckn);
        var arcTcknVO2 = Tckn.of(tckn);

        assertTrue(arcTcknVO1.sameValueAs(arcTcknVO2));
    }

    @Test
    void givenNull_whenUsingTcknOf_thenThrowsNullPointerException() {
        assertThrows(NullPointerException.class, () -> Tckn.of(null));
    }

    @Test
    void givenNull_whenThrowsIllegalArgumentException_thenShouldMessage() {
        NullPointerException nullPointerException =
                assertThrows(NullPointerException.class, () -> Tckn.of(null));

        assertEquals("TCKN cannot be null!", nullPointerException.getMessage());
    }

    @ParameterizedTest
    @MethodSource(value = "validTcknButCreatedForTestValues")
    void givenValidTckn_whenContainsTestDataAndUsingTcknOf_thenThrowsIllegalArgumentException(String tckn) {
        assertThrows(IllegalArgumentException.class, () -> Tckn.of(tckn));
    }

    @ParameterizedTest
    @MethodSource(value = "inValidTcknValues")
    void givenInValid_whenUsingTcknOf_thenThrowsIllegalArgumentException(String invalidTckn) {
        assertThrows(IllegalArgumentException.class, () -> Tckn.of(invalidTckn));
    }

    @ParameterizedTest
    @MethodSource(value = "inValidTcknValues")
    void givenInValid_whenThrowsIllegalArgumentException_thenShouldMessage(String invalidTckn) {
        IllegalArgumentException illegalArgumentException =
                assertThrows(IllegalArgumentException.class, () -> Tckn.of(invalidTckn));

        assertEquals("An invalid Tckn: " + invalidTckn, illegalArgumentException.getMessage());
    }
}