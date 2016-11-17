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

import java.util.function.Function;

/**
 * An interface similar to {@link Function} to allow methods that may throw an
 * exception to be referenced.
 *
 * @author Clayn <clayn_osmato@gmx.de>
 * @param <T> the type of the parameter for the function
 * @param <R> the type of the result of the function
 * @ext
 * @since 0.1.0
 */
@FunctionalInterface
public interface TryingFunction<T, R> extends Function<T, R>
{

    /**
     * Tries to apply this function to the given parameter. This may throw a
     * checked exception.
     *
     * @param t the function argument
     * @return the function result
     * @throws Exception when the function fails.
     * @see #apply(java.lang.Object)
     * @since 0.1.0
     */
    public R tryApply(T t) throws Exception;

    @Override
    public default R apply(T t)
    {
        try
        {
            return tryApply(t);
        } catch (Exception ex)
        {
            throw new RuntimeException(ex);
        }
    }

}
