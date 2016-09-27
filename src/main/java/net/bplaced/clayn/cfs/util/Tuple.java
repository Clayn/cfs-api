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
 *
 * @author Clayn <clayn_osmato@gmx.de>
 * @param <T>
 * @param <U>
 */
public final class Tuple<T,U>
{
    private T first;
    private U second;

    public Tuple()
    {
    }

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

    public T getFirst()
    {
        return first;
    }

    public U getSecond()
    {
        return second;
    }
}
