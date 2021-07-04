package com.kbhkn.valueobjects.testvalues;

import com.kbhkn.valueobjects.testvalues.base.BaseTestValues;
import org.junit.jupiter.params.provider.Arguments;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Test Values For Amount Value Object.
 *
 * @author Hakan KABASAKAL, 4-July-21
 */
public class AmountTestValues extends BaseTestValues {
    private static Set<Arguments> validDoubleMoneyValues() {
        return validMoneyValues().stream()
                .map(t -> t.get()[0].toString())
                .map(x -> x.replaceAll("[₺$€\\s]|(TL)", "").replaceAll(",", "."))
                .filter(x -> !x.endsWith("-"))
                .map(Double::parseDouble)
                .map(Arguments::of)
                .collect(Collectors.toSet());
    }

    private static Set<Arguments> validMoneyValues() {
        return Stream.concat(validNegativeMoneyValues().stream(), validNegativeMoneyValues().stream())
                .collect(Collectors.toSet());
    }

    private static Set<Arguments> validNegativeMoneyValues() {
        Set<Arguments> minusPrefix = validPositiveMoneyValues().stream()
                .map(t -> t.get()[0].toString())
                .map(t -> "-" + t)
                .map(Arguments::of)
                .collect(Collectors.toSet());

        Set<Arguments> minusSuffix = validPositiveMoneyValues().stream()
                .map(t -> t.get()[0].toString())
                .map(t -> t + "-")
                .map(Arguments::of)
                .collect(Collectors.toSet());

        minusPrefix.addAll(minusSuffix);

        return minusPrefix;
    }

    private static List<Arguments> validPositiveMoneyValues() {
        return List.of(
                Arguments.of("125"),
                Arguments.of("125,00"),
                Arguments.of("125.00"),
                Arguments.of("0,98"),
                Arguments.of("98"),
                Arguments.of("99.99"),
                Arguments.of("99,99"),
                Arguments.of("199.00"),
                Arguments.of("1000000.94"),
                Arguments.of("98 TL"),
                Arguments.of("98TL"),
                Arguments.of("98,00TL"),
                Arguments.of("98,1TL"),
                Arguments.of("98,99TL"),
                Arguments.of("₺98"),
                Arguments.of("₺1,98"),
                Arguments.of("₺10,98"),
                Arguments.of("₺10,00"),
                Arguments.of("₺ 10,00"),
                Arguments.of("₺ 10"),
                Arguments.of("₺ 10,1"),
                Arguments.of("98$"),
                Arguments.of("98.1$"),
                Arguments.of("98.1 $"),
                Arguments.of("0.10 $"),
                Arguments.of("0.1 $"),
                Arguments.of("0.1$"),
                Arguments.of("$98"),
                Arguments.of("$ 98"),
                Arguments.of("$98.1"),
                Arguments.of("$ 98.1"),
                Arguments.of("$ 0.10"),
                Arguments.of("$0.10"),
                Arguments.of("$0.1"),
                Arguments.of("$ 0.1"),
                Arguments.of("98€"),
                Arguments.of("98.1€"),
                Arguments.of("98.1 €"),
                Arguments.of("0.10 €"),
                Arguments.of("0.1 €"),
                Arguments.of("0.1€"),
                Arguments.of("€98"),
                Arguments.of("€ 98"),
                Arguments.of("€98.1"),
                Arguments.of("€ 98.1"),
                Arguments.of("€ 0.10"),
                Arguments.of("€0.10"),
                Arguments.of("€0.1"),
                Arguments.of("€ 0.1")
        );
    }

    private static List<Arguments> inValidMoneyValues() {
        return List.of(
                Arguments.of(""),
                Arguments.of("\t\t   "),
                Arguments.of("\r\r\r"),
                Arguments.of("           "),
                Arguments.of("___________"),
                Arguments.of("***********"),
                Arguments.of("94.091"),
                Arguments.of("94,091"),
                Arguments.of("0,091"),
                Arguments.of("0000,091"),
                Arguments.of("000$0,09$1"),
                Arguments.of("000$0,09$-1"),
                Arguments.of("38548800264a"),
                Arguments.of("38548 800264a"),
                Arguments.of("a38548800264"),
                Arguments.of("a385  48800264   "),
                Arguments.of("_38548800264"),
                Arguments.of("_3854880_0264"),
                Arguments.of("1_000_000_000"),
                Arguments.of("1_000_000_000₺"),
                Arguments.of("$1_000_000_000"),
                Arguments.of("$1_###"),
                Arguments.of("$1_###_###_###"),
                Arguments.of("38548_800264"),
                Arguments.of("a"),
                Arguments.of("48 USD"),
                Arguments.of("48 EURO"),
                Arguments.of("48 $_"),
                Arguments.of("48 $_$"),
                Arguments.of("$_$"),
                Arguments.of("€$_$"),
                Arguments.of("€$_€"),
                Arguments.of("€$_€₺")
        );
    }
}
