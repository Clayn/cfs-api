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
 * Simple implementation for the filesettings. If you don't need something
 * special this class will be just fine.
 *
 * @author Clayn
 * @since 0.1
 * @version $Revision: 329 $
 */
public class SimpleFileSettings implements FileSettings
{

    private boolean createOnAccess;

    /**
     * {@inheritDoc }
     *
     * @return {@inheritDoc }
     * @since 0.1
     */
    @Override
    public boolean getCreateOnAccess()
    {
        return createOnAccess;
    }

    /**
     * {@inheritDoc }
     *
     * @param createOnAccess {@inheritDoc }
     * @since 0.1
     */
    @Override
    public void setCreateOnAccess(boolean createOnAccess)
    {
        this.createOnAccess = createOnAccess;
    }
}
