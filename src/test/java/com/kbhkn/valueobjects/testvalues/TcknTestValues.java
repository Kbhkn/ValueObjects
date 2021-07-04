package com.kbhkn.valueobjects.testvalues;

import com.kbhkn.valueobjects.testvalues.base.BaseTestValues;
import org.junit.jupiter.params.provider.Arguments;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Test Values For Tckn Value Object.
 *
 * @author Hakan KABASAKAL, 4-July-21
 */
public class TcknTestValues extends BaseTestValues {
    private static List<Arguments> validTcknValues() {
        return List.of(
                Arguments.of("80382504280"),
                Arguments.of("46583232216"),
                Arguments.of("38548800264"),
                Arguments.of("63193781828"),
                Arguments.of("46111668340"),
                Arguments.of("24263239854"),
                Arguments.of("94089399056"),
                Arguments.of("99146171682")
        );
    }

    // Returns, [11111111110, 22222222220, ..., 99999999990]
    private static List<Arguments> validTcknButCreatedForTestValues() {
        return IntStream.range(1, 10)
                .mapToObj(i -> Arguments.of(Integer.toString(i).repeat(10) + "0"))
                .collect(Collectors.toList());
    }

    private static List<Arguments> tcknWithMasked() {
        return List.of(
                Arguments.of("80382504280", "803******80"),
                Arguments.of("46583232216", "465******16"),
                Arguments.of("38548800264", "385******64")
        );
    }

    private static List<Arguments> inValidTcknValues() {
        return List.of(
                Arguments.of(""),
                Arguments.of("\t\t   "),
                Arguments.of("\r\r\r"),
                Arguments.of("           "),
                Arguments.of("___________"),
                Arguments.of("***********"),
                Arguments.of("AsdQer"),
                Arguments.of("38548800264a"),
                Arguments.of("a38548800264"),
                Arguments.of("_38548800264"),
                Arguments.of("38548_800264"),
                Arguments.of("  1234567890  "),
                Arguments.of("1"),
                Arguments.of("111"),
                Arguments.of("a")
        );
    }
}
