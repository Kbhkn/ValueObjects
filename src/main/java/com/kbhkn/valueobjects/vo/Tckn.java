package com.kbhkn.valueobjects.vo;

import com.kbhkn.valueobjects.base.Maskable;
import com.kbhkn.valueobjects.base.ValueObject;
import lombok.Value;
import lombok.experimental.Accessors;

import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;

/**
 * TCKN is a value object.
 * It should be immutable(@Value) and validated while object creation.
 * It can be sure that this object contains a validated tckn value.
 * Don't need anymore to validate this object at any layer while we're using it.
 *
 * @author Hakan KABASAKAL, 4-July-21
 */
@Value
@Accessors(fluent = true)
public class Tckn implements ValueObject<Tckn>, Maskable {
    private static final Pattern TCKN_PATTERN = Pattern.compile("^[1-9][0-9]{9}[02468]$");
    private static final List<String> TCKN_TEST_VALUES = List.of(
            "11111111110", "22222222220", "33333333330",
            "44444444440", "55555555550", "66666666660",
            "77777777770", "88888888880", "99999999990"
    );

    String value;

    /**
     * Creator for the value object.
     *
     * @param value input value.
     * @return validated TCKN value object.
     */
    public static Tckn of(String value) {
        Objects.requireNonNull(value, "TCKN cannot be null!");

        var tckn = value.trim();

        if (isValid(tckn)) {
            return new Tckn(tckn);
        }

        throw new IllegalArgumentException("An invalid Tckn: " + value);
    }

    /**
     * Checks value with T.R. ID Verification Algorithm.
     *
     * @param value tckn value.
     * @return validation result.
     */
    private static boolean isValid(String value) {
        if (!TCKN_PATTERN.matcher(value).matches() || TCKN_TEST_VALUES.contains(value)) {
            return false;
        }

        var totalOddIndexes = 0;
        var totalEvenIndexes = 0;
        var total = 0;

        for (var i = 0; i < 9; i++) {
            var nthDigit = Character.getNumericValue(value.charAt(i));

            if (i % 2 == 0) {
                totalOddIndexes += nthDigit;
            } else {
                totalEvenIndexes += nthDigit;
            }

            total += nthDigit;
        }

        var digit10 = Character.getNumericValue(value.charAt(9));
        var digit11 = Character.getNumericValue(value.charAt(10));

        var surePositiveDiff = Math.abs((totalOddIndexes * 7) - totalEvenIndexes);
        var newDigit10 = Math.floorMod(surePositiveDiff, 10);
        var newDigit11 = Math.floorMod(total + newDigit10, 10);

        return (digit10 == newDigit10) && (digit11 == newDigit11);
    }

    /**
     * Returns value as a long.
     */
    public Long asLong() {
        return Long.parseLong(value);
    }

    @Override
    public String masked() {
        // Show the first 3 and last 2 character
        // Example output: 12345678901 --> 123******01
        return value.substring(0, 3) + "*".repeat(6) + value.substring(9);
    }
}