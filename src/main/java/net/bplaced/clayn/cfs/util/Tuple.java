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

/**
 * Utility class that holds to values of the same or different types.
 *
 * @author Clayn <clayn_osmato@gmx.de>
 * @param <T> the type for the first value
 * @param <U> the type for the second value
 */
public final class Tuple<T, U>
{

    private T first;
    private U second;

    /**
     * Creates a new empty tuple
     */
    public Tuple()
    {
    }

    /**
     * Creates a new tuple with the given values.
     *
     * @param first the first value to store
     * @param second the second value to store
     */
    public Tuple(T first, U second)
    {
        this.first = first;
        this.second = second;
    }

    public void setFirst(T first)
    {
        this.first = first;
    }

    public void setSecond(U second)
    {
        this.second = second;
    }

    /**
     * Returns the first value. May be {@code null}
     *
     * @return the first value
     */
    public T getFirst()
    {
        return first;
    }

    /**
     * Returns the second value. May be {@code null}
     *
     * @return the second value
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
    
   
}
