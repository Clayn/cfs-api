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
 * Implementations of this class can have a parent that manages this class. A
 * child allows to get it's parent. It's not necessary that a parent exists
 *
 * @author Clayn
 * @param <P> the type for the parent of this class
 * @since 0.1
 * @version $Revision: 318 $
 */
public interface Child<P>
{

    /**
     * Returns the parent file from this instance. The type depends on the
     * implementation. Implementing classes should assure that you can't access
     * an invalid value using this method e.g. get the parent of the root from a
     * filesystem. The parent may be {@code null}
     *
     * @return the parent of this instance
     * @since 0.1
     */
    public P getParent();
}
