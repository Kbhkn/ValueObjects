package com.kbhkn.valueobjects.vo;

import com.kbhkn.valueobjects.base.Maskable;
import com.kbhkn.valueobjects.base.ValueObject;
import lombok.Value;
import lombok.experimental.Accessors;
import org.apache.commons.validator.routines.EmailValidator;

import java.util.Objects;

/**
 * Email is a value object.
 * It should be immutable(@Value) and validated while object creation.
 * It can be sure that this object contains a validated email value.
 * Don't need anymore to validate this object at any layer while we're using it.
 *
 * @author Hakan KABASAKAL, 4-July-21
 */
@Value
@Accessors(fluent = true)
public class Email implements ValueObject<Email>, Maskable {
    private static final EmailValidator EMAIL_VALIDATOR = EmailValidator.getInstance();

    String value;

    /**
     * Creator for the value object.
     *
     * @param localpart email's localpart.
     * @param domain    email's domain.
     * @return validated Email value object.
     */
    public static Email of(String localpart, String domain) {
        Objects.requireNonNull(localpart, "Local part of email cannot be empty!");
        Objects.requireNonNull(domain, "Domain of email cannot be empty!");

        return of(localpart.trim() + "@" + domain.trim());
    }

    /**
     * Creator for the value object.
     *
     * @param value input value.
     * @return validated Email value object.
     */
    public static Email of(String value) {
        Objects.requireNonNull(value, "Email cannot be empty!");

        var email = value.trim().toLowerCase();

        if (EMAIL_VALIDATOR.isValid(email)) {
            return new Email(email);
        }

        throw new IllegalArgumentException("An invalid Email: " + value);
    }

    public String localPart() {
        return value.substring(0, value.lastIndexOf("@"));
    }

    public String domain() {
        return value.substring(value.lastIndexOf("@") + 1);
    }

    public String upperCaseLocalPart() {
        return localPart().toUpperCase();
    }

    public String upperCaseDomain() {
        return domain().toUpperCase();
    }

    public String upperCaseValue() {
        return value().toUpperCase();
    }

    @Override
    public String masked() {
        var localPart = localPart();

        if (localPart.length() > 2) {
            localPart = localPart.substring(0, 2) + "*".repeat(4);
        }

        return localPart + "@" + domain();
    }
}
