package com.kbhkn.valueobjects.base;

import java.io.Serializable;

/**
 * A value object is a small and immutable object representing a simple object whose equality is not based on identity.
 * I.E., two value objects are equal when they have the same value, not necessarily the same object.
 * VOs can also include/use their business validation while creating themselves.
 * Therefore, VOs should be created using their "static T of()" factory method.
 *
 * @author Hakan KABASAKAL, 4-July-21
 */
public interface ValueObject<T> extends Serializable {
    String value();

    default boolean sameValueAs(T other) {
        return this.equals(other);
    }

    boolean equals(final Object o);

    int hashCode();
}
