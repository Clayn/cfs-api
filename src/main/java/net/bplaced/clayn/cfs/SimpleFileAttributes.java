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
 * Simple implementations for the {@link FileAttributes} that holds the 
 * necessary values as fields. Subclasses may change these values and have no need 
 * to implement the accessing methods. The provided default attributes are always 
 * {@code -1}
 * @author Clayn <clayn_osmato@gmx.de>
 * @since 0.3.0
 */
public class SimpleFileAttributes implements FileAttributes
{
    protected long modified=-1l;
    protected long created=-1l;
    protected long used=-1l;
    
    public static SimpleFileAttributes defaultAttributes()
    {
        return new SimpleFileAttributes();
    }

    @Override
    public long lastModified()
    {
        return modified;
    }

    @Override
    public long creationTime()
    {
        return created;
    }

    @Override
    public long lastUsed()
    {
        return used;
    }
}
