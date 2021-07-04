package com.kbhkn.valueobjects.testvalues.base;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Base class for test classes.
 *
 * @author Hakan KABASAKAL, 4-July-21
 */
public class BaseTestValues {
    protected String addGap(String value) {
        int number = ThreadLocalRandom.current().nextInt(0, 12);
        String gap = " ".repeat(number + 1);

        if (number < 4) {
            value += gap;
        } else if (number < 9) {
            value = gap + value;
        } else {
            value = gap + value + gap;
        }

        return value;
    }
}
