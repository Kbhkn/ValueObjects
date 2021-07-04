package com.kbhkn.valueobjects.vo;

import com.kbhkn.valueobjects.testvalues.EmailTestValues;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit Tests for Email Value Object.
 * Test Values contains EmailTestValues.class.
 *
 * @author Hakan KABASAKAL, 4-July-21
 */
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class EmailTest extends EmailTestValues {

    @ParameterizedTest
    @MethodSource(value = "validEmailAddress")
    void givenValidEmail_whenUsingEmailOfMethod_thenCreatesValidVO(String email) {
        assertNotNull(Email.of(email));
    }

    @ParameterizedTest
    @MethodSource(value = "validEmailAddress")
    void givenValidEmail_whenCallsValueMethod_thenShouldEqualInputtedValue(String email) {
        var emailVO = Email.of(email);

        assertEquals(email, emailVO.value());
    }

    @ParameterizedTest
    @MethodSource(value = "validEmailAddress")
    void givenValidEmailButContainsEmptyCharacter_whenUsingEmailOfMethod_thenCreatesValidVO(String email) {
        var emailContainsGap = addGap(email);

        assertNotNull(Email.of(emailContainsGap));
    }

    @ParameterizedTest
    @MethodSource(value = "validEmailAddress")
    void givenValidEmailButContainsEmptyCharacter_whenCallsValueMethod_thenShouldEqualInputtedAndTrimmedValue(String email) {
        var emailContainsGap = addGap(email);

        var emailVO = Email.of(emailContainsGap);
        assertEquals(emailContainsGap.trim(), emailVO.value());
    }

    @ParameterizedTest
    @MethodSource(value = "validLocalPartAndDomains")
    void givenValidLocalPartAndDomain_whenUsingEmailOfMethod_thenCreatesValidVO(String localPart, String domain) {
        assertNotNull(Email.of(localPart, domain));
    }

    @ParameterizedTest
    @MethodSource(value = "validLocalPartAndDomains")
    void givenValidLocalPartAndDomain_whenCallsValueMethod_thenShouldEqualInputtedValue(String localPart, String domain) {
        var emailVO = Email.of(localPart, domain);
        var expected = String.format("%s@%s", localPart.trim(), domain.trim());

        assertEquals(expected, emailVO.value());
    }

    @ParameterizedTest
    @MethodSource(value = "validLocalPartAndDomains")
    void givenValidLocalPartAndDomainButContainsEmptyCharacters_whenUsingEmailOfMethod_thenCreatesValidVO(String localPart, String domain) {
        var localPartContainsGap = addGap(localPart);
        var domainContainsGap = addGap(domain);

        assertNotNull(Email.of(localPartContainsGap, domainContainsGap));
    }

    @ParameterizedTest
    @MethodSource(value = "validLocalPartAndDomains")
    void givenValidLocalPartAndDomainButContainEmptyCh_whenCallsValueMethod_thenShouldEqualInputtedAndTrimmedValue(String localPart, String domain) {
        var localPartContainsGap = addGap(localPart);
        var domainContainsGap = addGap(domain);

        var emailVO = Email.of(localPartContainsGap, domainContainsGap);
        var expected = String.format("%s@%s", localPartContainsGap.trim(), domainContainsGap.trim());

        assertEquals(expected, emailVO.value());
    }

    @ParameterizedTest
    @MethodSource(value = "validLocalPartAndDomains")
    void givenValidEmail_whenUsingLocalPartMethod_thenShouldEqualExpectedValue(String localPart, String domain) {
        var emailVO = Email.of(localPart, domain);
        assertEquals(localPart, emailVO.localPart());
    }

    @ParameterizedTest
    @MethodSource(value = "validLocalPartAndDomains")
    void givenValidEmailButContainsEmptyCharacter_whenUsingLocalPartMethod_thenShouldEqualExpectedValue(String localPart, String domain) {
        var localPartContainsGap = addGap(localPart);
        var domainContainsGap = addGap(domain);

        var emailVO = Email.of(localPartContainsGap, domainContainsGap);
        assertEquals(localPartContainsGap.trim(), emailVO.localPart());
    }

    @ParameterizedTest
    @MethodSource(value = "validLocalPartAndDomains")
    void givenValidEmail_whenUsingDomainMethod_thenShouldEqualExpectedValue(String localPart, String domain) {
        var emailVO = Email.of(localPart, domain);
        assertEquals(domain, emailVO.domain());
    }

    @ParameterizedTest
    @MethodSource(value = "validLocalPartAndDomains")
    void givenValidEmailButContainsEmptyCharacters_whenUsingDomainMethod_thenShouldEqualExpectedValue(String localPart, String domain) {
        var localPartContainsGap = addGap(localPart);
        var domainContainsGap = addGap(domain);

        var emailVO = Email.of(localPartContainsGap, domainContainsGap);
        assertEquals(domainContainsGap.trim(), emailVO.domain());
    }

    @ParameterizedTest
    @MethodSource(value = "validEmailAddress")
    void givenValidEmail_whenUsingUpperValueMethod_thenShouldEqualExpectedValue(String email) {
        var emailVO = Email.of(email);
        assertEquals(email.toUpperCase(), emailVO.upperCaseValue());
    }

    @ParameterizedTest
    @MethodSource(value = "validLocalPartAndDomains")
    void givenValidEmail_whenUsingUpperLocalPartMethod_thenShouldEqualExpectedValue(String localPart, String domain) {
        var emailVO = Email.of(localPart, domain);
        assertEquals(localPart.toUpperCase(), emailVO.upperCaseLocalPart());
    }

    @ParameterizedTest
    @MethodSource(value = "validLocalPartAndDomains")
    void givenValidEmail_whenUsingUpperDomainMethod_thenShouldEqualExpectedValue(String localPart, String domain) {
        var emailVO = Email.of(localPart, domain);
        assertEquals(domain.toUpperCase(), emailVO.upperCaseDomain());
    }

    @ParameterizedTest
    @MethodSource(value = "emailsWithMasked")
    void givenValidEmail_WhenUsingMaskedMethod_ThenShouldEqualExpectedMaskedText(String email, String expectedMasked) {
        var masked = Email.of(email).masked();
        assertEquals(expectedMasked.toLowerCase(), masked);
    }

    @ParameterizedTest
    @MethodSource(value = "emailsWithMasked")
    void givenValidEmail_WhenUsingUpperMaskedMethod_ThenShouldEqualExpectedMaskedText(String email, String expectedMasked) {
        var masked = Email.of(email).upperCaseMasked();
        assertEquals(expectedMasked.toUpperCase(), masked);
    }

    @ParameterizedTest
    @MethodSource(value = "emailsWithMasked")
    void givenValidEmail_WhenUsingLowerMaskedMethod_ThenShouldEqualExpectedMaskedText(String email, String expectedMasked) {
        var masked = Email.of(email).lowerCaseMasked();
        assertEquals(expectedMasked.toLowerCase(), masked);
    }

    @ParameterizedTest
    @MethodSource(value = "validEmailAddress")
    void givenValidEmail_whenCreates2DifferentEmailVO_thenShouldBeEqual(String email) {
        assertEquals(Email.of(email), Email.of(email));
    }

    @ParameterizedTest
    @MethodSource(value = "validLocalPartAndDomains")
    void givenValidLocalPartAndDomain_whenCreates2DifferentEmailVO_thenShouldBeEqual(String localPart, String domain) {
        assertEquals(Email.of(localPart, domain), Email.of(localPart, domain));
    }

    @ParameterizedTest
    @MethodSource(value = "validEmailAddress")
    void givenValidEmail_whenCreates2DifferentEmailVOAndCallsSameValueAsMethod_thenTrue(String email) {
        var emailVO1 = Email.of(email);
        var emailVO2 = Email.of(email);

        assertTrue(emailVO1.sameValueAs(emailVO2));
    }

    @ParameterizedTest
    @MethodSource(value = "validLocalPartAndDomains")
    void givenValidLocalPartAndDomain_whenCreates2DifferentEmailVOAndCallsSameValueAsMethod_thenTrue(String localPart, String domain) {
        var emailVO1 = Email.of(localPart, domain);
        var emailVO2 = Email.of(localPart, domain);

        assertTrue(emailVO1.sameValueAs(emailVO2));
    }

    @Test
    void givenNull_whenUsingEmailOf_thenThrowsNullPointerException() {
        assertThrows(NullPointerException.class, () -> Email.of(null));
    }

    @Test
    void givenNull_whenThrowsNullPointerException_thenShouldMessage() {
        var nullPointerException = assertThrows(NullPointerException.class, () -> Email.of(null));
        assertEquals("Email cannot be empty!", nullPointerException.getMessage());
    }

    @Test
    void givenNullLocalPartAndDomain_whenUsingEmailOf_thenThrowsNullPointerException() {
        assertThrows(NullPointerException.class, () -> Email.of(null, null));
    }

    @Test
    void givenNullLocalPart_whenThrowsNullPointerException_thenShouldMessage() {
        var nullPointerException = assertThrows(NullPointerException.class, () -> Email.of(null, "gmail.com"));
        assertEquals("Local part of email cannot be empty!", nullPointerException.getMessage());
    }

    @Test
    void givenNullDomain_whenThrowsNullPointerException_thenShouldMessage() {
        var nullPointerException = assertThrows(NullPointerException.class, () -> Email.of("hakan", null));
        assertEquals("Domain of email cannot be empty!", nullPointerException.getMessage());
    }

    @ParameterizedTest
    @MethodSource(value = "invalidEmails")
    void givenInValid_whenUsingEmailOf_thenThrowsIllegalArgumentException(String invalidEmail) {
        assertThrows(IllegalArgumentException.class, () -> Email.of(invalidEmail));
    }

    @ParameterizedTest
    @MethodSource(value = "invalidEmails")
    void givenInValid_whenThrowsIllegalArgumentException_thenShouldMessage(String invalidEmail) {
        var illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> Email.of(invalidEmail));

        assertEquals("An invalid Email: " + invalidEmail, illegalArgumentException.getMessage());
    }
}