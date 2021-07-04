package com.kbhkn.valueobjects.base;

/**
 * Confidential or privileged information should be disguised for the client-side, logging, or transaction history.
 * For example, TR-ID, TAX-ID, SSN, IP Address, Phone Number, Credit-card, etc.
 *
 * @author Hakan KABASAKAL, 4-July-21
 */
public interface Maskable {

    /**
     * Returns upperCased.
     */
    default String upperCaseMasked() {
        return masked().toUpperCase();
    }

    /**
     * Returns lowerCased.
     */
    default String lowerCaseMasked() {
        return masked().toLowerCase();
    }

    /**
     * Returns value as a masked.
     */
    String masked();
}
