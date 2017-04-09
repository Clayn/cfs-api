/*
 * Copyright (C) 2017 Clayn <clayn_osmato@gmx.de>
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

import java.util.function.Consumer;

/**
 *
 * @author Clayn <clayn_osmato@gmx.de>
 */
public interface TryingConsumer<T> extends Consumer<T>
{
    public void tryAccept(T t) throws Exception;

    @Override
    public default void accept(T t)
    {
        try
        {
            tryAccept(t);
        } catch (Exception ex)
        {
            throw new RuntimeException(ex);
        }
    }
    
    
}
