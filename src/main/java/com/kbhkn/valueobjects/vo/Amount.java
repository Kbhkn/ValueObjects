package com.kbhkn.valueobjects.vo;

import com.kbhkn.valueobjects.base.ValueObject;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import lombok.experimental.Accessors;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Objects;
import java.util.regex.Pattern;

/**
 * Amount is a value object.
 * It should be immutable(@Value) and validated while object creation.
 * It can be sure that this object contains a validated amount value.
 * Don't need anymore to validate this object at any layer while we're using it.
 *
 * @author Hakan KABASAKAL, 4-July-21
 */
@Value
@Accessors(fluent = true)
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class Amount implements ValueObject<Amount> {
    private static final Pattern MONEY_PATTERN = Pattern.compile("^\\d+([.]\\d{1,2})?$");
    private static final NumberFormat CURRENCY_FOR_USD = NumberFormat.getCurrencyInstance(Locale.US);
    private static final NumberFormat CURRENCY_FOR_EURO = NumberFormat.getCurrencyInstance(Locale.FRANCE);
    private static final NumberFormat CURRENCY_FOR_TURKEY = NumberFormat.getCurrencyInstance(Locale.forLanguageTag("tr-TR"));

    String value;

    /**
     * Creator for the value object.
     *
     * @param value input value as double.
     * @return validated Email value object.
     */
    public static Amount of(Double value) {
        Objects.requireNonNull(value, "value cannot be empty!");

        return of(value.toString());
    }

    /**
     * Creator for the value object.
     *
     * @param value input value as string.
     * @return validated Email value object.
     */
    public static Amount of(String value) {
        Objects.requireNonNull(value, "value cannot be empty!");

        if (isValid(value)) {
            return factory(value);
        }

        throw new IllegalArgumentException("An invalid Amount: " + value);
    }


    /**
     * Checks value with custom business and the money regex.
     *
     * @param value money value.
     * @return validation result.
     */
    public static boolean isValid(String value) {
        String money;

        money = removeCurrencyAndReplaceCommaToDot(value);
        money = removeMinus(money);

        return MONEY_PATTERN.matcher(money).matches();
    }

    /**
     * Creator for the value object.
     *
     * @param value valid money.
     * @return new ArcAmount instance.
     */
    private static Amount factory(String value) {
        String money;

        money = removeCurrencyAndReplaceCommaToDot(value);

        if (money.contains("-")) {
            money = removeMinus(money);
            return new Amount("-" + money);
        }

        return new Amount(money);
    }

    /**
     * Remove currency for the validation.
     * If money contain comma character replace by dot character.
     *
     * @param value money
     * @return cleaned value.
     */
    private static String removeCurrencyAndReplaceCommaToDot(String value) {
        return value
                .replaceAll("[₺$€]|(TL)", "")
                .replaceAll(",", ".")
                .trim();
    }

    private static String removeMinus(String value) {
        if (value.startsWith("-")) {
            value = value.substring(1).trim();
        } else if (value.endsWith("-")) {
            value = value.replaceAll(".$", "").trim();
        }

        return value;
    }

    public Double asDouble() {
        return Double.parseDouble(value);
    }

    public String asTRY() {
        return CURRENCY_FOR_TURKEY.format(asDouble());
    }

    public String asTL() {
        return asTRY().substring(1) + " TL";
    }

    public String asUSD() {
        return CURRENCY_FOR_USD.format(asDouble());
    }

    public String asEuro() {
        return CURRENCY_FOR_EURO.format(asDouble());
    }
}
