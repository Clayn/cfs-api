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
package net.bplaced.clayn.cfs.ext.functional;

import java.util.function.Predicate;

/**
 * An interface similar to {@link Predicate} to allow methods that may throw an
 * exception to be referenced.
 *
 * @author Clayn
 * @param <T> the type of the objects to check
 * @ext
 * @since 0.1.0
 */
@FunctionalInterface
public interface TryingPredicate<T> extends Predicate<T>
{

    /**
     * Tries to evaluate this predicate on the given argument. This may throw an
     * exception.
     *
     * @param val the input argument
     * @return {@code true} if the input argument matches the predicate,
     * otherwise {@code false}
     * @throws Exception if the evaluation fails.
     * @see #test(java.lang.Object)
     * @since 0.1.0
     */
    public boolean tryTest(T val) throws Exception;

    @Override
    public default boolean test(T t)
    {
        try
        {
            return tryTest(t);
        } catch (Exception ex)
        {
            throw new RuntimeException(ex);
        }
    }

}
