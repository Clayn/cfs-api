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
 * This class represents an event from an {@link ActiveDirectory}. It collects
 * the informations from the event as a wrapper.
 *
 * @author Clayn
 * @since 0.1
 * @version $Revision: 318 $
 */
public class FileModification
{

    /**
     * The modification types available for filemodifications
     */
    public static enum Modification
    {
        CREATE, DELETE, MODIFY;
    }

    private final SimpleFile file;
    private final Modification modification;
    private final long timeStamp;

    /**
     * Creates a new {@link FileModification} with the given file, modification
     * and timestamp.
     *
     * @param file the file that caused the event
     * @param modification the modification that was done
     * @param timeStamp the time when the event occured
     * @since 0.1
     */
    public FileModification(SimpleFile file, Modification modification,
            long timeStamp)
    {
        this.file = file;
        this.modification = modification;
        this.timeStamp = timeStamp;
    }

    /**
     * Returns the timestamp from the modification in {@code ms}
     *
     * @return the time when the modification happend
     * @since 0.1
     */
    public long getTimeStamp()
    {
        return timeStamp;
    }

    /**
     * Returns the file that was modfied (i.e. created, deleted or modified).
     *
     * @return the file that caused the event
     * @since 0.1
     */
    public SimpleFile getFile()
    {
        return file;
    }

    /**
     * Returns the modification that was done to the file from the event.
     *
     * @return the modification done to the file
     * @since 0.1
     */
    public Modification getModification()
    {
        return modification;
    }

}
