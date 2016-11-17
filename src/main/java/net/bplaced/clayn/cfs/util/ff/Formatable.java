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
package net.bplaced.clayn.cfs.util.ff;

import net.bplaced.clayn.cfs.ext.functional.TryingFunction;

/**
 * Classes that implement this interface can be formated using every function
 * that can format this class. Therefore a implementing class can be formated to
 * every type as long as a formater exists.
 *
 * @author Clayn
 * @param <T> the type that should be formated. Normally the class itself
 * @since 0.2.0
 */
public interface Formatable<T>
{

    /**
     * Formats the class using the given function.
     *
     * @param <R> the type the class gets formatted to
     * @param formater the formater to use
     * @return the result of the given formater
     * @since 0.2.0
     */
    public <R> R as(TryingFunction<T, R> formater);
}
