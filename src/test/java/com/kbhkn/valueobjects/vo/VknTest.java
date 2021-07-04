package com.kbhkn.valueobjects.vo;

import com.kbhkn.valueobjects.testvalues.VknTestValues;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit Tests for VKN Value Object.
 *
 * @author Hakan KABASAKAL, 4-July-21
 */
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class VknTest extends VknTestValues {

    @ParameterizedTest
    @MethodSource(value = "validVknValues")
    void givenValidVkn_whenUsingVknOfMethod_thenCreatesValidVO(String vkn) {
        assertNotNull(Vkn.of(vkn));
    }

    @ParameterizedTest
    @MethodSource(value = "validVknValues")
    void givenValidVknButContainsEmptyCharacter_whenUsingVknOfMethod_thenCreatesValidVO(String vkn) {
        String vknContainsGap = addGap(vkn);

        assertNotNull(Vkn.of(vknContainsGap));
    }

    @ParameterizedTest
    @MethodSource(value = "validVknValues")
    void givenValidVkn_whenCallsValueMethod_thenShouldEqualInputtedValue(String vkn) {
        var vknVO = Vkn.of(vkn);

        assertEquals(vkn, vknVO.value());
    }

    @ParameterizedTest
    @MethodSource(value = "validVknValues")
    void givenValidVkn_whenUsingAsLongMethod_thenShouldEqualExpectedValue(String vkn) {
        var vknVO = Vkn.of(vkn);

        assertEquals(Long.parseLong(vkn), vknVO.asLong());
    }

    @ParameterizedTest
    @MethodSource(value = "validVknValues")
    void givenValidVknButContainsEmptyChar_whenCallsValueMethod_thenShouldEqualInputtedAndTrimmedValue(String vkn) {
        String vknContainsGap = addGap(vkn);

        var vknVO1 = Vkn.of(vknContainsGap);

        assertEquals(vknContainsGap.trim(), vknVO1.value());
    }

    @ParameterizedTest
    @MethodSource(value = "vknWithMasked")
    void givenValidVkn_WhenMasked_ThenShouldEqualExpectedMaskedText(String vkn, String expectedMaskedValue) {
        var maskedVkn1 = Vkn.of(vkn).masked();
        assertEquals(expectedMaskedValue, maskedVkn1);
    }

    @ParameterizedTest
    @MethodSource(value = "validVknValues")
    void givenValidVkn_whenCreates2DifferentVknVO_thenShouldBeEqual(String vkn) {
        var arcVknVO1 = Vkn.of(vkn);
        var arcVknVO2 = Vkn.of(vkn);

        assertEquals(arcVknVO1, arcVknVO2);
    }

    @ParameterizedTest
    @MethodSource(value = "validVknValues")
    void givenValidVkn_whenCreates2DifferentVknVOAndCallsSameValueAsMethod_thenTrue(String vkn) {
        var arcVknVO1 = Vkn.of(vkn);
        var arcVknVO2 = Vkn.of(vkn);

        assertTrue(arcVknVO1.sameValueAs(arcVknVO2));
    }

    @Test
    void givenNull_whenUsingVknOf_thenThrowsNullPointerException() {
        assertThrows(NullPointerException.class, () -> Vkn.of(null));
    }

    @Test
    void givenValidVkn_whenItsTestVknAndUsingVknOf_thenThrowsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> Vkn.of("1234567890"));
    }

    @ParameterizedTest
    @MethodSource(value = "inValidVknValues")
    void givenInValid_whenUsingVknOf_thenThrowsIllegalArgumentException(String invalidVkn) {
        assertThrows(IllegalArgumentException.class, () -> Vkn.of(invalidVkn));
    }

    @ParameterizedTest
    @MethodSource(value = "inValidVknValues")
    void givenInValid_whenThrowsIllegalArgumentException_thenShouldMessage(String invalidVkn) {
        IllegalArgumentException illegalArgumentException =
                assertThrows(IllegalArgumentException.class, () -> Vkn.of(invalidVkn));

        assertEquals("An invalid VKN: " + invalidVkn, illegalArgumentException.getMessage());
    }
}