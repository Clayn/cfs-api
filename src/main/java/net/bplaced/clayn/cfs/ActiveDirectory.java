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
import net.bplaced.clayn.cfs.ext.functional.TryingConsumer;
import net.bplaced.clayn.cfs.util.Child;

/**
 * A Directory implementation where you can install actions that will be called
 * when something in the directory changes. Such changes are file create,
 * deletion and modifications. An active directory should be able to register
 * all these events but may not be able to. Since the watching for such events
 * may need ressources you don't want to reserve for each directory the watching
 * process can or must be started manually.
 *
 * @author Clayn
 * @since 0.1
 * @version $Revision: 329 $
 */
public interface ActiveDirectory extends Directory
{

    /**
     * Sets the action which will be called when the directory notices that a
     * file was created within this directory. The {@link FileModification}
     * given to the consumer will contain the file that was created
     *
     * @param newFile the action when a file was created
     * @since 0.1
     */
    public void setOnCreate(TryingConsumer<FileModification> newFile);

    /**
     * Sets the action which will be called when the directory notices that a
     * file was deleted within this directory. The {@link FileModification}
     * given to the consumer will contain the file that was deleted
     *
     * @param deletedFile the action when a file was deleted
     * @since 0.1
     */
    public void setOnDelete(TryingConsumer<FileModification> deletedFile);

    /**
     * Sets the action which will be called when the directory notices that a
     * file was modified within this directory. The {@link FileModification}
     * given to the consumer will contain the file that was modified
     *
     * @param changedFile the action when a file was modified
     * @since 0.1
     */
    public void setOnModify(TryingConsumer<FileModification> changedFile);

    /**
     * Activates the directory. This may be an optional operation since
     * implementations handle the watching process different. However its a good
     * choice use this method to be safe. Using this method may start a Thread
     * that watches the directory or other resource consuming actions that you
     * may not want to be there for every created
     * {@link ActiveDirectory directory}.
     */
    public void activate();

    /**
     * Deactivates the directory. After calling this method no events will be
     * given to any registered consumer. This should also stop and free all
     * resourcs that may be aquired after using {@link #activate()}.
     */
    public void deactivate();

    /**
     * Changes to the active directory relativ to this instance. If the path
     * starts with an {@code /} it will be relative to the root directory from
     * the filesystem where this directory was created. The returned directory
     * has to be an ActiveDirectory.
     *
     * @param path the relativ path for the new directory
     * @return the directory with the path relative to this one
     * @throws IOException if an I/O Exception occures
     * @see Directory#changeDirectory(java.lang.String)
     * @since 0.1
     */
    @Override
    public ActiveDirectory changeDirectory(String path) throws IOException;

    /**
     * Returns the parent {@link ActiveDirectory directory} to this one. If
     * there is no parent (e.g. the current one is the root) {@code null} gets
     * returned. The returned directory has to be an ActiveDirectory.
     *
     * @return the parent directory for the given one or {@code null} if there
     * is none.
     * @see Directory#getParent()
     * @see Child#getParent()
     * @since 0.1
     */
    @Override
    public ActiveDirectory getParent();

}
