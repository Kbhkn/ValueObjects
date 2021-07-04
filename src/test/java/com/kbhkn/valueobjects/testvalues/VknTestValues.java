package com.kbhkn.valueobjects.testvalues;

import com.kbhkn.valueobjects.testvalues.base.BaseTestValues;
import org.junit.jupiter.params.provider.Arguments;

import java.util.List;

/**
 * VknTest Values.
 *
 * @author Hakan KABASAKAL, 4-July-21
 */
public class VknTestValues extends BaseTestValues {
    private static List<Arguments> validVknValues() {
        return List.of(
                Arguments.of("4024775444"),
                Arguments.of("2756384931"),
                Arguments.of("9924562681"),
                Arguments.of("6924775984"),
                Arguments.of("3703751069"),
                Arguments.of("2717971770"),
                Arguments.of("5931426980"),
                Arguments.of("0394942820")
        );
    }

    private static List<Arguments> vknWithMasked() {
        return List.of(
                Arguments.of("4024775444", "402******4"),
                Arguments.of("2756384931", "275******1"),
                Arguments.of("9924562681", "992******1")
        );
    }

    private static List<Arguments> inValidVknValues() {
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
