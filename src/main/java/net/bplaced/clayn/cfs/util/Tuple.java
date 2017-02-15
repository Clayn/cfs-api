/*
 * Copyright (C) 2016 Clayn <clayn_osmato@gmx.de>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package net.bplaced.clayn.cfs.util;

import java.util.function.Supplier;

/**
 * Utility class that holds to values of the same or different types. This
 * implementation is used to not include any dependencie for a tuple.
 *
 * @author Clayn <clayn_osmato@gmx.de>
 * @param <T> the type for the first value
 * @param <U> the type for the second value
 * @since 0.1.0
 */
public final class Tuple<T, U>
{

    private T first;
    private U second;

    /**
     * Creates a new empty tuple
     *
     * @since 0.1.0
     */
    public Tuple()
    {
    }

    /**
     * Creates a new tuple with the given values.
     *
     * @param first the first value to store
     * @param second the second value to store
     * @since 0.1.0
     */
    public Tuple(T first, U second)
    {
        this.first = first;
        this.second = second;
    }

    /**
     * Sets the first value of this tuple.
     *
     * @param first the new first value
     * @since 0.1.0
     */
    public void setFirst(T first)
    {
        this.first = first;
    }

    /**
     * Sets the second value of this tuple.
     *
     * @param second the new second value
     * @since 0.1.0
     */
    public void setSecond(U second)
    {
        this.second = second;
    }

    /**
     * Returns the first value. May be {@code null}
     *
     * @return the first value
     * @since 0.1.0
     */
    public T getFirst()
    {
        return first;
    }

    /**
     * Returns the second value. May be {@code null}
     *
     * @return the second value
     * @since 0.1.0
     */
    public U getSecond()
    {
        return second;
    }

    @Override
    public String toString()
    {
        return "Tuple{" + "first=" + first + ", second=" + second + '}';
    }

    /**
     * Creates a new tuple using the values provided by the given suppliers.
     * This is to convert other tuple or tuple like implementations into a tuple
     * expected by the cfs.
     *
     * @param <T> the type of the first value
     * @param <U> the type of the second value
     * @param firstSup the supplier that provides the first value
     * @param secondSub the supplier that provides the second value
     * @return a new tuple that with the values provided by the suppliers
     * @since 0.3.0
     */
    public static <T, U> Tuple<T, U> create(Supplier<? extends T> firstSup,
            Supplier<? extends U> secondSub)
    {
        return new Tuple<>(firstSup.get(), secondSub.get());
    }
}
