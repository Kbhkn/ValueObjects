package com.kbhkn.valueobjects.vo;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.PhoneNumberUtil.PhoneNumberFormat;
import com.google.i18n.phonenumbers.Phonenumber;
import com.google.i18n.phonenumbers.Phonenumber.PhoneNumber.CountryCodeSource;
import com.google.i18n.phonenumbers.geocoding.PhoneNumberOfflineGeocoder;
import com.kbhkn.valueobjects.base.Maskable;
import com.kbhkn.valueobjects.base.ValueObject;
import lombok.Value;
import lombok.experimental.Accessors;

import java.util.Locale;
import java.util.Objects;

/**
 * ArcPhone is a value object.
 * It should be immutable(@Value) and validated while object creation.
 * It can be sure that this object contains a validated phone value.
 * Don't need anymore to validate this object at any layer while we're using it.
 *
 * @author Hakan KABASAKAL, 4-July-21
 */
@Value
@Accessors(fluent = true)
public class Phone implements ValueObject<Phone>, Maskable {
    private static final PhoneNumberUtil PHONE_NUMBER_UTIL = PhoneNumberUtil.getInstance();
    private static final PhoneNumberOfflineGeocoder PHONE_NUMBER_OFFLINE_GEOCODER = PhoneNumberOfflineGeocoder.getInstance();

    private static Phonenumber.PhoneNumber PHONE_NUMBER;

    String value;

    private Phone(String value) {
        this.value = value;
        PHONE_NUMBER = getCommonPhoneNumberObject(value);
    }

    /**
     * Creator for the value object with country code and national number.
     *
     * @param countryCode    country code of phone number.
     * @param nationalNumber national number of phone number.
     * @return validated ArcPhone value object.
     */
    public static Phone of(final String countryCode, final String nationalNumber) {
        Objects.requireNonNull(countryCode, "Country code cannot be empty!");
        Objects.requireNonNull(nationalNumber, "National number cannot be empty!");

        if (countryCode.trim().isBlank()) {
            throw new IllegalArgumentException("Country code cannot be blank!");
        }

        if (nationalNumber.trim().isBlank()) {
            throw new IllegalArgumentException("National number cannot be blank!");
        }

        if (countryCode.startsWith("+")) {
            return of(countryCode.trim() + nationalNumber.trim());
        } else {
            return of("+" + countryCode.trim(), nationalNumber.trim());
        }
    }

    /**
     * Creator for the value object.
     *
     * @param value input value. If value doesn't starts with "+" use TR country code.
     * @return validated ArcPhone value object.
     */
    public static Phone of(final String value) {
        Objects.requireNonNull(value, "Phone cannot be empty!");

        var phone = checkCountryCode(value);

        if (isValid(phone)) {
            return factory(phone);
        }

        throw new IllegalArgumentException("An invalid Phone: " + value);
    }

    private static String checkCountryCode(final String value) {
        var phone = value.replaceAll("[^\\d+]", "");

        if (phone.startsWith("+")) {
            return phone;
        } else if (phone.startsWith("90")) {
            return "+" + phone;
        } else {
            return "+90" + phone;
        }

    }

    private static Phonenumber.PhoneNumber getCommonPhoneNumberObject(String phone) {
        try {
            return PHONE_NUMBER_UTIL.parse(phone, CountryCodeSource.UNSPECIFIED.name());
        } catch (NumberParseException e) {

            return null;
        }
    }

    private static boolean isValid(String phone) {
        var phoneNumber = getCommonPhoneNumberObject(phone);

        return Objects.nonNull(phoneNumber) && PHONE_NUMBER_UTIL.isValidNumber(phoneNumber);
    }

    private static Phone factory(String phone) {
        var phoneNumber = getCommonPhoneNumberObject(phone);
        var phoneValue = PHONE_NUMBER_UTIL.format(phoneNumber, PhoneNumberFormat.E164);

        return new Phone(phoneValue);
    }

    /**
     * Returns the country code of the phone number with a "+" prefix.
     */
    public String countryCodeStartsWithPlus() {
        return "+" + PHONE_NUMBER.getCountryCode();
    }

    /**
     * Returns the country code of the phone number.
     */
    public String countryCode() {
        return "" + PHONE_NUMBER.getCountryCode();
    }

    /**
     * Returns the country code of the phone number as an int.
     */
    public int countryCodeAsInt() {
        return PHONE_NUMBER.getCountryCode();
    }

    /**
     * Returns the national number of the phone number as a string.
     */
    public String nationalNumber() {
        return "" + PHONE_NUMBER.getNationalNumber();
    }

    /**
     * Returns the national number of the phone number as a long.
     */
    public Long nationalNumberAsLong() {
        return PHONE_NUMBER.getNationalNumber();
    }

    /**
     * Returns the region code for the phone number.
     */
    public String region() {
        return PHONE_NUMBER_UTIL.getRegionCodeForNumber(PHONE_NUMBER);
    }

    /**
     * Returns a text description for the phone number. The description might consist of the name of the country
     * where the phone number is from, or the name of the geographical area the phone number is from
     * if more detailed information is available.
     */
    public String location() {
        return PHONE_NUMBER_OFFLINE_GEOCODER.getDescriptionForNumber(PHONE_NUMBER, Locale.forLanguageTag("tr-TR"));
    }

    /**
     * Returns the type of phone number.
     */
    public String type() {
        return PHONE_NUMBER_UTIL.getNumberType(PHONE_NUMBER).name();
    }

    /**
     * Returns the phone number close to the E-164 standard.(Separated parts)
     * See // https://www.wikiwand.com/en/E.164 for more information about E164.
     */
    public String internationalFormat() {
        return PHONE_NUMBER_UTIL.format(PHONE_NUMBER, PhoneNumberFormat.INTERNATIONAL);
    }

    /**
     * Returns the phone number by the national standard.
     * See // https://www.wikiwand.com/en/National_conventions_for_writing_telephone_numbers
     */
    public String nationalFormat() {
        return PHONE_NUMBER_UTIL.format(PHONE_NUMBER, PhoneNumberFormat.NATIONAL);
    }

    @Override
    public String masked() {
        var nationalNumber = nationalNumber();
        var lengthOfNationalNumber = nationalNumber.length();

        return nationalNumber.substring(0, 3)
                + "*".repeat(lengthOfNationalNumber - 5)
                + nationalNumber.substring(lengthOfNationalNumber - 2);
    }

    /**
     * Returns the masked national number with the country code.
     */
    public String maskedWithCountryCode() {
        return countryCodeStartsWithPlus() + masked();
    }
}