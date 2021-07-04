package com.kbhkn.valueobjects.testvalues;

import com.kbhkn.valueobjects.testvalues.base.BaseTestValues;
import org.junit.jupiter.params.provider.Arguments;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Test Values For Phone Value Object.
 *
 * @author Hakan KABASAKAL, 4-July-21
 */
public class PhoneTestValues extends BaseTestValues {

    private static List<Arguments> validForeignAndTrPhoneNumbers() {
        List<Arguments> validTrPhoneNumbers = validTRPhoneNumbers();
        List<Arguments> validForeignPhoneNumbers = validForeignPhoneNumbers();

        return Stream.concat(validTrPhoneNumbers.stream(), validForeignPhoneNumbers.stream())
                .collect(Collectors.toList());
    }

    private static List<Arguments> validTrCellPhoneNumbers() {
        return List.of(
                Arguments.of("5058124579"),
                Arguments.of("(532) 266 16-60"),
                Arguments.of("0 (532) 266 16-60"),
                Arguments.of("90 (532) 266 16-60"),
                Arguments.of("+90 532 325 54 04"),
                Arguments.of("+90 (532) 266 16-60"),
                Arguments.of("+90-(532)-266-16-60"),
                Arguments.of("0507 945 56 19"),
                Arguments.of("0555 808 20 60"),
                Arguments.of("905322447438"),
                Arguments.of("90 535 566 48 55")
        );
    }

    private static List<Arguments> validTrVOIPPhoneNumbers() {
        return List.of(
                Arguments.of("8507240850"),
                Arguments.of("850 7240850"),
                Arguments.of("(850) 7240850"),
                Arguments.of("850 724 0850"),
                Arguments.of("(850) 724 0850"),
                Arguments.of("850 724 08 50"),
                Arguments.of("(850) 724 08 50"),
                Arguments.of("+908507240850"),
                Arguments.of("+90850 7240850"),
                Arguments.of("+90(850) 7240850"),
                Arguments.of("+90850 724 0850"),
                Arguments.of("+90(850) 724 0850"),
                Arguments.of("+90850 724 08 50"),
                Arguments.of("+90(850) 724 08 50"),
                Arguments.of("+908507240850"),
                Arguments.of("+90 8507240850"),
                Arguments.of("+90 850 7240850"),
                Arguments.of("+90 (850) 7240850"),
                Arguments.of("+90 850 724 0850"),
                Arguments.of("+90 (850) 724 0850"),
                Arguments.of("+90 850 724 08 50"),
                Arguments.of("+90 (850) 724 08 50"),
                Arguments.of("+90 8507240850"),
                Arguments.of("+90 850-7240850"),
                Arguments.of("+90-(850) 7240850"),
                Arguments.of("+90 850 724-0850"),
                Arguments.of("+90 (850) 724-0850"),
                Arguments.of("+90 850 724-08-50"),
                Arguments.of("+90 (850)-724-08 50")
        );
    }

    private static List<Arguments> validTrFixedLinePhoneNumbers() {
        return List.of(
                Arguments.of("2124011010"),
                Arguments.of("(212) 401 10-10"),
                Arguments.of("(212) 401 10-11"),
                Arguments.of("(212) 401 10-12"),
                Arguments.of("0 (212) 401 10-10"),
                Arguments.of("0 (212) 401 10-11"),
                Arguments.of("90 (212) 401 10-12"),
                Arguments.of("+90 212 401 10 10"),
                Arguments.of("+90 212 401 10 11"),
                Arguments.of("+90 212 401 10 12"),
                Arguments.of("+90 (212) 401 10-10"),
                Arguments.of("+90 (212) 401 10-11"),
                Arguments.of("+90 (212) 401 10-12"),
                Arguments.of("+90-(212)-401-10-10"),
                Arguments.of("+90-(212)-401-10-11"),
                Arguments.of("+90-(212)-401-10-12")
        );
    }

    private static List<Arguments> validTRPhoneNumbers() {
        return List.of(
                Arguments.of("5058124579"),
                Arguments.of("(532) 266 16-60"),
                Arguments.of("0 (532) 266 16-60"),
                Arguments.of("90 (532) 266 16-60"),
                Arguments.of("+90 532 325 54 04"),
                Arguments.of("+90 (532) 266 16-60"),
                Arguments.of("+90-(532)-266-16-60"),

                Arguments.of("2124011010"),
                Arguments.of("(212) 401 10-10"),
                Arguments.of("0 (212) 401 10-10"),
                Arguments.of("90 (212) 401 10-10"),
                Arguments.of("+90 212 401 10 10"),
                Arguments.of("+90 (212) 401 10-10"),
                Arguments.of("+90-(212)-401-10-10"),

                Arguments.of("+90 8507240850"),
                Arguments.of("+90 (850) 7240850"),
                Arguments.of("+90 (850) 724-08-50"),
                Arguments.of("+90 (850) 724 08 50"),

                Arguments.of("0507 945 56 19"),
                Arguments.of("0555 808 20 60"),
                Arguments.of("905322447438"),
                Arguments.of("90 535 566 48 55"),
                Arguments.of("0850 724 0850"),
                Arguments.of("08507240850"),
                Arguments.of("8507240850")
        );
    }

    private static List<Arguments> validForeignPhoneNumbers() {
        return List.of(
                Arguments.of("+4930591440110"),
                Arguments.of("+49 30591440110"),
                Arguments.of("+49 30 591440110"),
                Arguments.of("+49 30 (591)440110"),
                Arguments.of("+49 30-(591)-440110"),
                Arguments.of("+930773185408"),
                Arguments.of("+93 0773185408"),
                Arguments.of("+93 077 318 54 08"),
                Arguments.of("+93 077 318 5408"),
                Arguments.of("+93 077 3185408"),
                Arguments.of("+357 9 964 9762"),
                Arguments.of("+35799649762"),
                Arguments.of("+357 99649762"),
                Arguments.of("+357 996 49762"),
                Arguments.of("+357 996 497 62"),
                Arguments.of("+201174186095"),
                Arguments.of("+20 117 4186095"),
                Arguments.of("+20 117 418-6095"),
                Arguments.of("+33936938395"),
                Arguments.of("+33 936938395"),
                Arguments.of("+33 936 938395"),
                Arguments.of("+33 936 938 395"),
                Arguments.of("+33 93-693-8395"),
                Arguments.of("+39 06 930 3409"),
                Arguments.of("+50688522066"),
                Arguments.of("+506 88522066"),
                Arguments.of("+506 8852 2066"),
                Arguments.of("+506 8852-2066")
        );
    }

    private static List<Arguments> validCountryCodeAndNationalNumbers() {
        return List.of(
                Arguments.of("90", "5058124579"),
                Arguments.of("+90", "5058124579"),
                Arguments.of("+90", "(532) 266 16-60"),
                Arguments.of("     +90", "8507240850"),
                Arguments.of("+90", "(850) 724-08-50"),
                Arguments.of("+90     ", "2124011010"),
                Arguments.of("+90", "(212) 401 1010"),
                Arguments.of("+90", "(212) 401 10-10"),
                Arguments.of("+506", "8852-2066"),
                Arguments.of(" +20", "    117 4186095"),
                Arguments.of(" 20", "    117 4186095"),
                Arguments.of("+357", "9 964 9762   "),
                Arguments.of("49", "30-(591)-440110"),
                Arguments.of("+49", "30-(591)-440110"),
                Arguments.of("+93 ", "077 318 54 08"),
                Arguments.of(" +39", "06 930 3409"),
                Arguments.of(" +33", "936 938 395")
        );
    }

    private static List<Arguments> invalidPhoneNumbers() {
        return List.of(
                Arguments.of(""),
                Arguments.of("       "),
                Arguments.of("              "),
                Arguments.of("+90              "),
                Arguments.of("+90 544       "),
                Arguments.of("+90 544_______"),
                Arguments.of("+90 544 ___ __ __"),
                Arguments.of("+90 544 *** ** **"),
                Arguments.of("+905441234567895321"),
                Arguments.of("       000005441234567895321"),
                Arguments.of("000005441234567895321"),
                Arguments.of("000005441234567895321       "),
                Arguments.of("       000005441234567895321       "),
                Arguments.of("5001234567"),
                Arguments.of("       5001234567"),
                Arguments.of("5001234567       "),
                Arguments.of("       5001234567       "),
                Arguments.of("6001234567"),
                Arguments.of("hakan?"),
                Arguments.of("       hakan?"),
                Arguments.of("hakan?       "),
                Arguments.of("444")
        );
    }

    protected String removeNonNumericCharacters(String value) {
        return value.replaceAll("[^\\d+]", "");
    }
}
