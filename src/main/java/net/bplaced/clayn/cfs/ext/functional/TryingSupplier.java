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

import java.util.function.Supplier;

/**
 * An interface similar to {@link Supplier} to allow methods that may throw an
 * exception to be referenced.
 *
 * @author Clayn <clayn_osmato@gmx.de>
 * @param <T> the type of supplied object
 * @ext
 * @since 0.1.0
 */
@FunctionalInterface
public interface TryingSupplier<T> extends Supplier<T>
{

    /**
     * Tries to get the result. This may throw an exception.
     *
     * @return a result if it doesn't fail
     * @throws Exception if the supplier fails
     * @see #get()
     * @since 0.1.0
     */
    public T tryGet() throws Exception;

    @Override
    public default T get()
    {
        try
        {
            return tryGet();
        } catch (Exception ex)
        {
            throw new RuntimeException(ex);
        }
    }

}
