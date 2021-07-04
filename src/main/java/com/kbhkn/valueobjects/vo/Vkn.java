package com.kbhkn.valueobjects.vo;

import com.kbhkn.valueobjects.base.Maskable;
import com.kbhkn.valueobjects.base.ValueObject;
import lombok.Value;
import lombok.experimental.Accessors;

import java.util.Objects;
import java.util.regex.Pattern;

/**
 * VKN is a value object.
 * It should be immutable(@Value) and validated while object creation.
 * It can be sure that this object contains a validated vkn value.
 * Don't need anymore to validate this object at any layer while we're using it.
 *
 * @author Hakan KABASAKAL, 4-July-21
 */
@Value
@Accessors(fluent = true)
public class Vkn implements ValueObject<Vkn>, Maskable {
    private static final String VALID_BUT_TEST_VKN = "1234567890";
    private static final Pattern VKN_PATTERN = Pattern.compile("^\\d{10}$");

    String value;

    /**
     * Creator for the value object.
     *
     * @param value input value.
     * @return validated VKN value object.
     */
    public static Vkn of(String value) {
        Objects.requireNonNull(value, "VKN cannot be empty!");

        var vkn = value.trim();

        if (isValid(vkn)) {
            return new Vkn(vkn);
        }

        throw new IllegalArgumentException("An invalid VKN: " + value);
    }

    /**
     * Checks value with T.R TAX-ID Verification Algorithm.
     *
     * @param value VKN value.
     * @return validation result.
     */
    private static boolean isValid(String value) {
        if (!VKN_PATTERN.matcher(value).matches() || value.equals(VALID_BUT_TEST_VKN)) {
            return false;
        }

        var tmp = 0;
        var sum = 0;
        var lastDigit = Character.getNumericValue(value.charAt(9));

        for (var i = 0; i < 9; i++) {
            var digit = Character.getNumericValue(value.charAt(i));
            tmp = Math.floorMod(digit + 10 - (i + 1), 10);

            if (tmp == 9) {
                sum += tmp;
            } else {
                sum = (int) (sum + ((tmp * (Math.pow(2, 10 - (i + 1)))) % 9));
            }
        }

        return lastDigit == Math.floorMod(10 - Math.floorMod(sum, 10), 10);
    }

    /**
     * Returns value as a long.
     */
    public Long asLong() {
        return Long.parseLong(value);
    }

    @Override
    public String masked() {
        // Show the first 3 and last 1 character
        // Example output: 1234567890 --> 123******0
        return value.substring(0, 3) + "*".repeat(6) + value.substring(9);
    }
}
