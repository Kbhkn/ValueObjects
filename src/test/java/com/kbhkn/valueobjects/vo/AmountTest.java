package com.kbhkn.valueobjects.vo;

import com.kbhkn.valueobjects.testvalues.AmountTestValues;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit Tests for Amount Value Object.
 * Test Values contains PhoneTestValues.class.
 *
 * @author Hakan KABASAKAL, 4-July-21
 */
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class AmountTest extends AmountTestValues {

    @ParameterizedTest
    @MethodSource(value = "validDoubleMoneyValues")
    void givenValidMoneyAsDouble_whenUsingAmountOfMethod_thenCreatesValidVO(Double money) {
        assertNotNull(Amount.of(money));
    }

    @ParameterizedTest
    @MethodSource(value = "validDoubleMoneyValues")
    void givenValidMoneyAsDouble_whenCallsValueMethod_thenShouldHaveValidValue(Double money) {
        var amountVO = Amount.of(money);

        assertNotNull(amountVO.value());
    }

    @ParameterizedTest
    @MethodSource(value = "validDoubleMoneyValues")
    void givenValidMoneyAsDouble_whenUsingAsDoubleMethod_thenShouldEqualExpectedValue(Double money) {
        var moneyVO = Amount.of(money);

        assertEquals(money, moneyVO.asDouble());
    }

    @ParameterizedTest
    @MethodSource(value = "validDoubleMoneyValues")
    void givenValidMoneyAsDouble_whenCreates2DifferentAmountVO_thenShouldBeEqual(Double money) {
        var AmountVO1 = Amount.of(money);
        var AmountVO2 = Amount.of(money);

        assertEquals(AmountVO1, AmountVO2);
    }

    @ParameterizedTest
    @MethodSource(value = "validDoubleMoneyValues")
    void givenValidMoneyAsDouble_whenCreates2DifferentAmountVOAndCallsSameValueAsMethod_thenTrue(Double money) {
        var AmountVO1 = Amount.of(money);
        var AmountVO2 = Amount.of(money);

        assertTrue(AmountVO1.sameValueAs(AmountVO2));
    }

    @Test
    void givenNullDoubleMoney_whenUsingAmountOf_thenThrowsNullPointerException() {
        Double nullMoney = null;
        assertThrows(NullPointerException.class, () -> Amount.of(nullMoney));
    }

    @Test
    void givenNullDoubleMoney_whenThrowsNullPointerException_thenShouldMessage() {
        Double nullMoney = null;
        NullPointerException nullPointerException = assertThrows(NullPointerException.class, () -> Amount.of(nullMoney));

        assertEquals("value cannot be empty!", nullPointerException.getMessage());
    }

    @ParameterizedTest
    @MethodSource(value = "validMoneyValues")
    void givenValidMoney_whenUsingAmountOfMethod_thenCreatesValidVO(String money) {
        assertNotNull(Amount.of(money));
    }

    @ParameterizedTest
    @MethodSource(value = "validMoneyValues")
    void givenValidMoney_whenCallsValueMethod_thenShouldHaveValidValue(String money) {
        var amountVO = Amount.of(money);

        assertNotNull(amountVO.value());
    }

    @ParameterizedTest
    @MethodSource(value = "validMoneyValues")
    void givenValidMoney_whenUsingAsDoubleMethod_thenShouldNotNull(String money) {
        var moneyVO = Amount.of(money);

        assertNotNull(moneyVO.asDouble());
    }

    @ParameterizedTest
    @MethodSource(value = "validMoneyValues")
    void givenValidMoney_whenCreates2DifferentAmountVO_thenShouldBeEqual(String money) {
        var AmountVO1 = Amount.of(money);
        var AmountVO2 = Amount.of(money);

        assertEquals(AmountVO1, AmountVO2);
    }

    @ParameterizedTest
    @MethodSource(value = "validMoneyValues")
    void givenValidMoney_whenCreates2DifferentAmountVOAndCallsSameValueAsMethod_thenTrue(String money) {
        var AmountVO1 = Amount.of(money);
        var AmountVO2 = Amount.of(money);

        assertTrue(AmountVO1.sameValueAs(AmountVO2));
    }

    @ParameterizedTest
    @MethodSource(value = "validPositiveMoneyValues")
    void givenValidPositiveMoney_whenCallsValueMethod_thenShouldNotHaveMinus(String money) {
        var amountVO = Amount.of(money);
        String validMoney = amountVO.value();

        assertFalse(validMoney.contains("-"));
    }

    @ParameterizedTest
    @MethodSource(value = "validPositiveMoneyValues")
    void givenValidPositiveMoney_whenCallsAsDoubleMethod_thenValueShouldBiggerThenZero(String money) {
        var amountVO = Amount.of(money);
        Double validMoney = amountVO.asDouble();

        assertTrue(validMoney > 0);
    }

    @ParameterizedTest
    @MethodSource(value = "validNegativeMoneyValues")
    void givenValidNegativeMoney_whenCallsValueMethod_thenShouldHaveMinus(String money) {
        var amountVO = Amount.of(money);
        String validMoney = amountVO.value();

        assertTrue(validMoney.contains("-"));
    }

    @ParameterizedTest
    @MethodSource(value = "validNegativeMoneyValues")
    void givenValidNegativeMoney_whenCallsAsDoubleMethod_thenValueShouldLowerThenZero(String money) {
        var amountVO = Amount.of(money);
        Double validMoney = amountVO.asDouble();

        assertTrue(validMoney < 0);
    }

    @ParameterizedTest
    @MethodSource(value = "validMoneyValues")
    void givenValidNegativeMoney_whenCallsAsTRYMethod_thenValueShouldContainTurkishSign(String money) {
        var amountVO = Amount.of(money);
        String validMoney = amountVO.asTRY();

        assertTrue(validMoney.startsWith("₺") || validMoney.startsWith("-₺"));
    }

    @ParameterizedTest
    @MethodSource(value = "validMoneyValues")
    void givenValidNegativeMoney_whenCallsAsTLMethod_thenValueShouldEndsWithTL(String money) {
        var amountVO = Amount.of(money);
        String validMoney = amountVO.asTL();

        assertTrue(validMoney.endsWith("TL"));
    }

    @ParameterizedTest
    @MethodSource(value = "validMoneyValues")
    void givenValidNegativeMoney_whenCallsAsUSDMethod_thenValueShouldContainDollarSign(String money) {
        var amountVO = Amount.of(money);
        String validMoney = amountVO.asUSD();

        assertTrue(validMoney.startsWith("$") || validMoney.startsWith("-$"));
    }

    @ParameterizedTest
    @MethodSource(value = "validMoneyValues")
    void givenValidNegativeMoney_whenCallsAsEuroMethod_thenValueShouldContainEuroSign(String money) {
        var amountVO = Amount.of(money);
        String validMoney = amountVO.asEuro();

        assertTrue(validMoney.endsWith("€"));
    }

    @Test
    void givenNull_whenUsingAmountOf_thenThrowsNullPointerException() {
        String nullMoney = null;
        assertThrows(NullPointerException.class, () -> Amount.of(nullMoney));
    }

    @Test
    void givenNull_whenThrowsNullPointerException_thenShouldMessage() {
        String nullMoney = null;
        NullPointerException nullPointerException = assertThrows(NullPointerException.class, () -> Amount.of(nullMoney));

        assertEquals("value cannot be empty!", nullPointerException.getMessage());
    }

    @ParameterizedTest
    @MethodSource(value = "inValidMoneyValues")
    void givenInValid_whenUsingAmountOf_thenThrowsIllegalArgumentException(String invalidMoney) {
        assertThrows(IllegalArgumentException.class, () -> Amount.of(invalidMoney));
    }

    @ParameterizedTest
    @MethodSource(value = "inValidMoneyValues")
    void givenInValid_whenThrowsIllegalArgumentException_thenShouldMessage(String invalidMoney) {
        IllegalArgumentException illegalArgumentException =
                assertThrows(IllegalArgumentException.class, () -> Amount.of(invalidMoney));

        assertEquals("An invalid Amount: " + invalidMoney, illegalArgumentException.getMessage());
    }
}