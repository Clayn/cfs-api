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
package net.bplaced.clayn.cfs;

import java.io.IOException;

/**
 * An Interface for all classes that may claim some deletable resources. Classes
 * that implement this interface have to ensure, that after deletion the
 * resources are no longer valid or existing. In other words operations that
 * require the resources to exist, must fail after they were deleted.
 *
 * @author Clayn
 * @since 0.2.0
 */
public interface Deletable
{

    /**
     * Attempts to delete whatever resources are wrapped by this instance.
     *
     * @throws IOException if an I/O Exception occures during the deletion
     * @since 0.2.0
     */
    public void delete() throws IOException;
}
