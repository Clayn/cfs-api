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

/**
 * The filesettings define several configurations that may be accessed by
 * operations of a filesystem. These may expand during the development. To make
 * sure these changes won't break your code rely on {@link SimpleFileSettings}.
 * However changes may use default implementations an therefor stay compatible.
 *
 * @author Clayn
 * @since 0.1
 * @version $Revision: 318 $
 */
public interface FileSettings
{

    /**
     * Sets if the file should be created when you access (i.e. open) it or not.
     * If set to {@code true} these operations shouldn't fail if the file
     * doesn't exist.
     *
     * @param coa the flag if the file should be created on access
     */
    public void setCreateOnAccess(boolean coa);

    /**
     * Returns if the file will be created when you access it or not.
     *
     * @return {@code true} if the file will be created when you try to access
     * it {@code false} otherwise.
     */
    public boolean getCreateOnAccess();
}
