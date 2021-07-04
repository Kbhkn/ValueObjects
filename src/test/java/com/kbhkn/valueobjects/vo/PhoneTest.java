package com.kbhkn.valueobjects.vo;

import com.kbhkn.valueobjects.testvalues.PhoneTestValues;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit Tests for Phone Value Object.
 * Test Values contains PhoneTestValues.class.
 *
 * @author Hakan KABASAKAL, 4-July-21
 */
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class PhoneTest extends PhoneTestValues {

    @ParameterizedTest
    @MethodSource(value = "validForeignAndTrPhoneNumbers")
    void givenValidPhoneNumber_whenUsingPhoneOfMethod_thenCreatesValidVO(String phoneNumber) {
        assertNotNull(Phone.of(phoneNumber));
    }

    @ParameterizedTest
    @MethodSource(value = "validForeignAndTrPhoneNumbers")
    void givenValidPhoneNumberButContainsEmptyCharacter_whenUsingPhoneOfMethod_thenCreatesValidVO(String phoneNumber) {
        var phoneContainsGap = addGap(phoneNumber);

        assertNotNull(Phone.of(phoneContainsGap));
    }

    @ParameterizedTest
    @MethodSource(value = "validTRPhoneNumbers")
    void givenValidTRPhone_whenUsingPhoneOfMethod_thenCreatesValidVO(String phoneNumber) {
        assertNotNull(Phone.of(phoneNumber));
    }

    @ParameterizedTest
    @MethodSource(value = "validTRPhoneNumbers")
    void givenValidTRPhone_whenCallsValueMethod_thenShouldEqualInputtedValue(String phoneNumber) {
        var phoneNumberVO = Phone.of(phoneNumber);

        var phoneNumberWithoutNumeric = removeNonNumericCharacters(phoneNumber);

        if (phoneNumberWithoutNumeric.startsWith("+90")) {
            assertEquals(phoneNumberWithoutNumeric, phoneNumberVO.value());
        } else if (phoneNumberWithoutNumeric.startsWith("90")) {
            assertEquals("+" + phoneNumberWithoutNumeric, phoneNumberVO.value());
        } else if (phoneNumberWithoutNumeric.startsWith("0")) {
            assertEquals("+9" + phoneNumberWithoutNumeric, phoneNumberVO.value());
        } else {
            assertEquals("+90" + phoneNumberWithoutNumeric, phoneNumberVO.value());
        }

    }

    @ParameterizedTest
    @MethodSource(value = "validForeignPhoneNumbers")
    void givenValidForeignPhone_whenUsingPhoneOfMethod_thenCreatesValidVO(String phoneNumber) {
        assertNotNull(Phone.of(phoneNumber));
    }

    @ParameterizedTest
    @MethodSource(value = "validForeignPhoneNumbers")
    void givenValidForeignPhoneButContainsEmptyChar_whenUsingPhoneOfMethod_thenCreatesValidVO(String phoneNumber) {
        var phoneContainsGap = addGap(phoneNumber);

        assertNotNull(Phone.of(phoneContainsGap));
    }

    @ParameterizedTest
    @MethodSource(value = "validCountryCodeAndNationalNumbers")
    void givenValidCountryCodeAndNationalNumber_whenUsingPhoneOfMethod_thenCreatesValidVO(String countryCode, String nationalNumber) {
        assertNotNull(Phone.of(countryCode, nationalNumber));
    }

    @ParameterizedTest
    @MethodSource(value = "validCountryCodeAndNationalNumbers")
    void givenValidCcAndNnButContainsEmptyChar_whenUsingPhoneOfMethod_thenCreatesValidVO(String countryCode, String nationalNumber) {
        var countryCodeContainsGap = addGap(countryCode);
        var nationalNumberContainsGap = addGap(nationalNumber);

        assertNotNull(Phone.of(countryCodeContainsGap, nationalNumberContainsGap));
    }

    @ParameterizedTest
    @MethodSource(value = "validCountryCodeAndNationalNumbers")
    void givenValidCcAndNnButContainsEmptyCh_whenCallCountryCodeMethod_thenShouldEqualInputtedCountryCode(String countryCode, String nationalNumber) {
        var countryCodeContainsGap = addGap(countryCode);
        var nationalNumberContainsGap = addGap(nationalNumber);

        var phone = Phone.of(countryCodeContainsGap, nationalNumberContainsGap);

        var cc = removeNonNumericCharacters(countryCodeContainsGap);
        if (!cc.startsWith("+")) {
            cc = "+" + cc;
        }

        assertEquals(cc, phone.countryCodeStartsWithPlus());
    }

    @ParameterizedTest
    @MethodSource(value = "validCountryCodeAndNationalNumbers")
    void givenValidCcAndNnButContainsEmptyCh_whenCallNationalNumberMethod_thenShouldEqualInputtedNationalNumber(String code, String nationalNumber) {
        var countryCodeContainsGap = addGap(code);
        var nationalNumberContainsGap = addGap(nationalNumber);

        var phone = Phone.of(countryCodeContainsGap, nationalNumberContainsGap);

        var nn = removeNonNumericCharacters(nationalNumberContainsGap);

        assertEquals(Long.parseLong(nn), phone.nationalNumberAsLong());
    }

    @ParameterizedTest
    @MethodSource(value = "validForeignAndTrPhoneNumbers")
    void givenValidPhoneNumber_whenCallsCountryCodeStartsWithPlusMethod_thenCCShouldStartsWithPlus(String phoneNumber) {
        var phone = Phone.of(phoneNumber);

        var cc = phone.countryCodeStartsWithPlus();

        assertTrue(cc.startsWith("+"));
    }

    @ParameterizedTest
    @MethodSource(value = "validForeignAndTrPhoneNumbers")
    void givenValidPhoneNumber_whenCallsCountryCodeMethod_thenCCShouldNotStartsWithPlus(String phoneNumber) {
        var phone = Phone.of(phoneNumber);
        var cc = phone.countryCode();

        assertFalse(cc.startsWith("+"));
    }

    @ParameterizedTest
    @MethodSource(value = "validForeignAndTrPhoneNumbers")
    void givenValidPhoneNumber_whenCallsCountryCodeAsIntMethod_thenCCShouldNotEqualZero(String phoneNumber) {
        var phone = Phone.of(phoneNumber);
        var cc = phone.countryCodeAsInt();

        assertTrue(cc > 0);
    }

    @ParameterizedTest
    @MethodSource(value = "validForeignAndTrPhoneNumbers")
    void givenValidPhoneNumber_whenCallsCountryCodeAsIntMethod_thenCCShouldEqualCountryCode(String phoneNumber) {
        var phone = Phone.of(phoneNumber);

        var cc = phone.countryCodeAsInt();
        var ccAsString = phone.countryCode();

        assertEquals(Integer.toString(cc), ccAsString);
    }


    @ParameterizedTest
    @MethodSource(value = "validForeignAndTrPhoneNumbers")
    void givenValidPhoneNumber_whenCallsNationalNumberMethod_thenNNShouldNotEqualZero(String phoneNumber) {
        var phone = Phone.of(phoneNumber);

        var nationalNumber = phone.nationalNumber();

        assertNotEquals("0", nationalNumber);
    }


    @ParameterizedTest
    @MethodSource(value = "validForeignAndTrPhoneNumbers")
    void givenValidPhoneNumber_whenCallsNationalNumberMethod_thenNNLengthShouldBiggerThenSix(String phoneNumber) {
        var phone = Phone.of(phoneNumber);

        var nationalNumberLength = phone.nationalNumber().length();

        assertTrue(nationalNumberLength > 6);
    }

    @ParameterizedTest
    @MethodSource(value = "validForeignAndTrPhoneNumbers")
    void givenValidPhoneNumber_whenCallsNationalNumberMethod_thenNNShouldEqualAsLongMethod(String phoneNumber) {
        var phone = Phone.of(phoneNumber);

        var nationalNumber = phone.nationalNumber();
        var nationalNumberAsLong = phone.nationalNumberAsLong();

        assertEquals(nationalNumberAsLong, Long.parseLong(nationalNumber));
    }

    @ParameterizedTest
    @MethodSource(value = "validForeignAndTrPhoneNumbers")
    void givenValidPhoneNumber_whenCallsNationalNumberASLongMethod_thenNNShouldBiggerThenZero(String phoneNumber) {
        var phone = Phone.of(phoneNumber);

        var nationalNumberAsLong = phone.nationalNumberAsLong();

        assertTrue(nationalNumberAsLong > 0);
    }

    @ParameterizedTest
    @MethodSource(value = "validTRPhoneNumbers")
    void givenValidTRPhoneNumber_whenCallsRegionMethod_thenRegionShouldEqualTR(String phoneNumber) {
        var phone = Phone.of(phoneNumber);

        var region = phone.region();

        assertEquals("TR", region);
    }

    @ParameterizedTest
    @MethodSource(value = "validForeignPhoneNumbers")
    void givenValidTRForeignNumber_whenCallsRegionMethod_thenRegionShouldNotEqualTR(String phoneNumber) {
        var phone = Phone.of(phoneNumber);

        var region = phone.region();

        assertNotEquals("TR", region);
    }

    @ParameterizedTest
    @MethodSource(value = "validTrCellPhoneNumbers")
    void givenValidTRCellPhoneNumber_whenCallsLocationMethod_thenLocationShouldEqualTurkey(String phoneNumber) {
        var phone = Phone.of(phoneNumber);

        var location = phone.location();

        assertEquals("Türkiye", location);
    }

    @ParameterizedTest
    @MethodSource(value = "validTrCellPhoneNumbers")
    void givenValidTRCellPhoneNumber_whenCallsTypeMethod_thenTypeShouldEqualMobile(String phoneNumber) {
        var phone = Phone.of(phoneNumber);

        var type = phone.type();

        assertEquals("MOBILE", type);
    }


    @ParameterizedTest
    @MethodSource(value = "validForeignAndTrPhoneNumbers")
    void givenValidPhoneNumber_whenCallsInternationFormatMethod_thenFormatShouldContainsGap(String phoneNumber) {
        var phone = Phone.of(phoneNumber);

        var internationalFormat = phone.internationalFormat();

        assertTrue(internationalFormat.contains(" "));
    }

    @ParameterizedTest
    @MethodSource(value = "validForeignAndTrPhoneNumbers")
    void givenValidPhoneNumber_whenCallsInternationFormatMethod_thenFormatShouldStartsWithPlus(String phoneNumber) {
        var phone = Phone.of(phoneNumber);

        var internationalFormat = phone.internationalFormat();

        assertTrue(internationalFormat.startsWith("+"));
    }


    @ParameterizedTest
    @MethodSource(value = "validForeignAndTrPhoneNumbers")
    void givenValidTRCellPhoneNumber_whenCallsNationalFormatMethod_thenFormatShouldNotStartsWithPlus(String phoneNumber) {
        var phone = Phone.of(phoneNumber);

        var internationalFormat = phone.nationalFormat();

        assertFalse(internationalFormat.startsWith("+"));
    }

    @ParameterizedTest
    @MethodSource(value = "validTrCellPhoneNumbers")
    void givenValidTRCellPhoneNumber_whenCallsNationalFormatMethod_thenFormatShouldStartsWithZero(String phoneNumber) {
        var phone = Phone.of(phoneNumber);

        var internationalFormat = phone.nationalFormat();

        assertTrue(internationalFormat.startsWith("0"));
    }

    @ParameterizedTest
    @MethodSource(value = "validTrCellPhoneNumbers")
    void givenValidTRCellPhoneNumber_whenCallsNationalFormatMethod_thenFormatLengthShouldEqual14(String phoneNumber) {
        var phone = Phone.of(phoneNumber);

        var nationalFormat = phone.nationalFormat();

        assertEquals(14, nationalFormat.length());
    }

    @ParameterizedTest
    @MethodSource(value = "validTrFixedLinePhoneNumbers")
    void givenValidTRFixedLinePhoneNumber_whenCallsTypeMethod_thenShouldEqualsFixedLine(String phoneNumber) {
        var phone = Phone.of(phoneNumber);

        var type = phone.type();

        assertEquals("FIXED_LINE", type);
    }


    @ParameterizedTest
    @MethodSource(value = "validTrFixedLinePhoneNumbers")
    void givenValidTRFixedLinePhoneNumber_whenCallsNationalFormatMethod_thenFormatShouldStartsWithParenthesesAndZero(String phoneNumber) {
        var phone = Phone.of(phoneNumber);

        var nationalFormat = phone.nationalFormat();

        assertTrue(nationalFormat.startsWith("(0"));
    }

    @ParameterizedTest
    @MethodSource(value = "validTrFixedLinePhoneNumbers")
    void givenValidTRFixedLinePhoneNumber_whenCallsNationalFormatMethod_thenFormatLengthShouldEqual16(String phoneNumber) {
        var phone = Phone.of(phoneNumber);

        var nationalFormat = phone.nationalFormat();

        assertEquals(16, nationalFormat.length());
    }

    @ParameterizedTest
    @MethodSource(value = "validTrVOIPPhoneNumbers")
    void givenValidVOIPPhoneNumber_whenCallsTypeMethod_thenTypeShouldEqualVOIP(String phoneNumber) {
        var phone = Phone.of(phoneNumber);

        var type = phone.type();

        assertEquals("VOIP", type);
    }

    @ParameterizedTest
    @MethodSource(value = "validTrVOIPPhoneNumbers")
    void givenValidVOIPPhoneNumber_whenCallsLocationMethod_thenShouldEqualTurkiye(String phoneNumber) {
        var phone = Phone.of(phoneNumber);

        var location = phone.location();

        assertEquals("Türkiye", location);
    }

    @ParameterizedTest
    @MethodSource(value = "validTrVOIPPhoneNumbers")
    void givenValidVOIPPhoneNumber_whenCallsNationalFormatMethod_thenShouldStartsWith08(String phoneNumber) {
        var phone = Phone.of(phoneNumber);

        var nationalFormat = phone.nationalFormat();

        assertTrue(nationalFormat.startsWith("08"));
    }

    @ParameterizedTest
    @MethodSource(value = "validTrVOIPPhoneNumbers")
    void givenValidVOIPPhoneNumber_whenCallsNationalFormatMethod_thenNNLengthShouldEquals14(String phoneNumber) {
        var phone = Phone.of(phoneNumber);

        var nationalFormat = phone.nationalFormat();

        assertEquals(13, nationalFormat.length());
    }

    @ParameterizedTest
    @MethodSource(value = "validForeignAndTrPhoneNumbers")
    void givenValidPhoneNumber_whenUsingMaskedMethod_thenShouldContainsWildcard(String phoneNumber) {
        var phone = Phone.of(phoneNumber);
        var masked = phone.masked();

        assertTrue(masked.contains("***"));
    }

    @ParameterizedTest
    @MethodSource(value = "validForeignAndTrPhoneNumbers")
    void givenValidPhoneNumber_whenUsingMaskedMethod_thenShouldContainsNationalNumberLengthRules(String phoneNumber) {
        var phone = Phone.of(phoneNumber);
        var masked = phone.masked();

        var nationalNumber = phone.nationalNumber();

        var wildcardCount = (nationalNumber.length() - 5);
        var expectedWildCardPart = "*".repeat(wildcardCount);

        assertTrue(masked.contains(expectedWildCardPart));
    }

    @ParameterizedTest
    @MethodSource(value = "validForeignAndTrPhoneNumbers")
    void givenValidPhoneNumber_whenUsingMaskedWithCountryCodeMethod_thenShouldContainsWildcard(String phoneNumber) {
        // given
        var phone = Phone.of(phoneNumber);

        var nationalNumber = phone.nationalNumber();

        var wildcardCount = (nationalNumber.length() - 5);
        var expectedWildCardPart = "*".repeat(wildcardCount);

        // when
        var masked = phone.maskedWithCountryCode();

        // then
        assertTrue(masked.contains(expectedWildCardPart));
    }

    @ParameterizedTest
    @MethodSource(value = "validForeignAndTrPhoneNumbers")
    void givenValidPhoneNumber_whenCreates2DifferentPhoneVOAndCallsSameValueAsMethod_thenTrue(String phoneNumber) {
        var PhoneVO1 = Phone.of(phoneNumber);
        var PhoneVO2 = Phone.of(phoneNumber);

        assertTrue(PhoneVO1.sameValueAs(PhoneVO2));
    }

    @ParameterizedTest
    @MethodSource(value = "validCountryCodeAndNationalNumbers")
    void givenValidCCAndNN_whenCreates2DifferentPhoneVOAndCallsSameValueAsMethod_thenTrue(String countryCode, String nationalNumber) {
        var PhoneVO1 = Phone.of(countryCode, nationalNumber);
        var PhoneVO2 = Phone.of(countryCode, nationalNumber);

        assertTrue(PhoneVO1.sameValueAs(PhoneVO2));
    }

    @Test
    void givenNull_whenUsingPhoneOf_thenThrowsNullPointerException() {
        assertThrows(NullPointerException.class, () -> Phone.of(null));
    }

    @Test
    void givenNullCCAndNullNN_whenUsingPhoneOf_thenThrowsNullPointerException() {
        assertThrows(NullPointerException.class, () -> Phone.of(null, null));
    }

    @Test
    void givenNull_whenThrowsNullPointerException_thenShouldMessage() {
        var nullPointerException = assertThrows(NullPointerException.class, () -> Phone.of(null));
        assertEquals("Phone cannot be empty!", nullPointerException.getMessage());
    }

    @Test
    void givenNullCCAndNN_whenThrowsNullPointerException_thenShouldMessage() {
        var nullPointerException = assertThrows(NullPointerException.class, () -> Phone.of(null, null));
        assertEquals("Country code cannot be empty!", nullPointerException.getMessage());
    }

    @Test
    void givenNullCountryCode_whenThrowsNullPointerException_thenShouldMessage() {
        var nullPointerException = assertThrows(NullPointerException.class, () -> Phone.of(null, "5555555555"));
        assertEquals("Country code cannot be empty!", nullPointerException.getMessage());
    }

    @Test
    void givenNullNationalNumber_whenThrowsNullPointerException_thenShouldMessage() {
        var nullPointerException = assertThrows(NullPointerException.class, () -> Phone.of("+90", null));
        assertEquals("National number cannot be empty!", nullPointerException.getMessage());
    }

    @Test
    void givenBlankCountryCode_whenThrowsIllegalArgumentException_thenShouldMessage() {
        var illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> Phone.of("", ""));
        assertEquals("Country code cannot be blank!", illegalArgumentException.getMessage());
    }

    @Test
    void givenBlankNationalNumber_whenThrowsIllegalArgumentException_thenShouldMessage() {
        var illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> Phone.of("+90", ""));
        assertEquals("National number cannot be blank!", illegalArgumentException.getMessage());
    }

    @ParameterizedTest
    @MethodSource(value = "invalidPhoneNumbers")
    void givenInValid_whenUsingPhoneOf_thenThrowsIllegalArgumentException(String invalidPhone) {
        assertThrows(IllegalArgumentException.class, () -> Phone.of(invalidPhone));
    }

    @ParameterizedTest
    @MethodSource(value = "invalidPhoneNumbers")
    void givenInValid_whenThrowsIllegalArgumentException_thenShouldMessage(String invalidPhone) {
        var illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> Phone.of(invalidPhone));

        assertEquals("An invalid Phone: " + invalidPhone, illegalArgumentException.getMessage());
    }
}