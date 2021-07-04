package com.kbhkn.valueobjects.testvalues;

import com.kbhkn.valueobjects.testvalues.base.BaseTestValues;
import org.junit.jupiter.params.provider.Arguments;

import java.util.List;

/**
 * Test Values For Email Value Object.
 *
 * @author Hakan KABASAKAL, 4-July-21
 */
public class EmailTestValues extends BaseTestValues {
    private static List<Arguments> validEmailAddress() {
        return List.of(
                Arguments.of("kbhknn@gmail.com"),
                Arguments.of("test@hotmail.com"),
                Arguments.of("test@hotmail.com.tr"),
                Arguments.of("test@outlook.com"),
                Arguments.of("test@outlook.com.tr")
        );
    }

    private static List<Arguments> validLocalPartAndDomains() {
        return List.of(
                Arguments.of("kbhknn", "gmail.com"),
                Arguments.of("test", "hotmail.com"),
                Arguments.of("test", "hotmail.com.tr"),
                Arguments.of("test", "outlook.com"),
                Arguments.of("test", "outlook.com.tr")
        );
    }

    private static List<Arguments> emailsWithMasked() {
        return List.of(
                Arguments.of("h@test.com", "h@test.com"),
                Arguments.of("h@test.com", "H@TEST.COM"),
                Arguments.of("ha@gmail.com", "ha@gmail.com"),
                Arguments.of("hak@gmail.com", "ha****@gmail.com"),
                Arguments.of("haka@gmail.com", "ha****@gmail.com"),
                Arguments.of("hakan@gmail.com", "HA****@GMAIL.COM"),
                Arguments.of("hakan@gmail.com", "ha****@gmail.com"),
                Arguments.of("hakankabasakal@test.com.tr", "ha****@test.com.tr")
        );
    }

    private static List<Arguments> invalidEmails() {
        return List.of(
                Arguments.of(""),
                Arguments.of("      "),
                Arguments.of("hakan"),
                Arguments.of("           @gmail.com"),
                Arguments.of("hakan      @gmail.com"),
                Arguments.of("hakan@     gmail.com"),
                Arguments.of("hakan@gmail"),
                Arguments.of("hakangmail.com"),
                Arguments.of("000@000")
        );
    }
}
