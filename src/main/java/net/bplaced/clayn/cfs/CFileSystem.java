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
 * This interface represents a simple file system, which is the main access
 * point to {@link Directory Directories} and {@link SimpleFile Files}.
 *
 * @author Clayn
 * @since 0.1
 * @version $Revision: 318 $
 */
public interface CFileSystem
{

    /**
     * Returns the character that separates directories and files. The default
     * separator char is {@code '/'}.
     *
     * @return the separator char for this filesystem
     * @see #getSeparator()
     * @since 0.1
     */
    public default char getSeparatorChar()
    {
        return '/';
    }

    /**
     * Returns the separator as string. Simply creates a string using
     * {@link #getSeparatorChar()} for an easy usage e.g. splitting paths.
     *
     * @return the separation character as String value
     * @see #getSeparatorChar()
     * @since 0.1
     * @default {@code return String.valueOf(getSeparatorChar());}
     */
    public default String getSeparator()
    {
        return String.valueOf(getSeparatorChar());
    }

    /**
     * Returns the root directory for this filesystem. The returned directoy
     * must not be the root of the real file system.
     *
     * @return the root directory for the file system
     * @throws IOException if an I/O Exception occures
     * @since 0.1
     * @see #getActiveRoot()
     */
    public Directory getRoot() throws IOException;

    /**
     * Returns the directory with the path relative to the filesystems root.
     *
     * @param path the path for the directory relative to the root
     * @return the directory with the given path relative to the root
     * @throws IOException if an I/O Exception occures
     * @since 0.1
     * @see #getActiveDirectory(java.lang.String)
     */
    public default Directory getDirectory(String path) throws IOException
    {
        return getRoot().changeDirectory(path);
    }

    /**
     * Returns wether or not active directories are supported by the file system
     * or not.
     *
     * @return {@code true} if, and only if, active directories are supported,
     * {@code false} otherwise.
     * @default Returns {@code false}
     */
    public default boolean isActiveDirectorySupported()
    {
        return false;
    }

    /**
     * Returns an {@link ActiveDirectory active directory} with the given path
     * relative to the root. Use this method only when active directories are
     * supported by the file system.
     *
     * @param path the path relative to the root
     * @return the active directory with the given path
     * @throws IOException if an I/O Exception occures
     * @since 0.1
     * @see #getDirectory(java.lang.String)
     * @default {@code return null;}
     */
    public default ActiveDirectory getActiveDirectory(String path) throws IOException
    {
        return null;
    }

    /**
     * Returns the root {@link ActiveDirectory active directory} for this
     * filesystem. It is recommended that the directory returned by this method
     * has the same path as the one returned by {@link #getRoot()} though its
     * not required.
     *
     * @return the root active directory
     * @throws IOException if an I/O Exception occures
     * @see #getRoot()
     * @since 0.1
     * @default {@code return null;}
     */
    public default ActiveDirectory getActiveRoot() throws IOException
    {
        return null;
    }

    /**
     * Returns the filesystems filesettings. These settings can be used to
     * configure several operations for this filesystem or its files and
     * directories.
     *
     * @return the filesystems filesettings
     * @since 0.1
     */
    public FileSettings getFileSettings();

    /**
     * Returns the file with the given path relative to the root directory. It
     * the path contains any other directories the file will be in the last
     * given directory.
     *
     * @param path the path for the file
     * @return the file with the given path
     * @throws IOException if an I/O Exception occures
     * @since 0.1
     * @see Directory#getFile(java.lang.String)
     * @default Switches to the directory from the path (or root if nothing
     * given) and creates the file using
     * {@link Directory#getFile(java.lang.String)}s
     */
    public default SimpleFile getFile(String path) throws IOException
    {
        if (path.contains(getSeparator()))
        {
            int last = path.lastIndexOf(getSeparator());
            String dirs = path.substring(0, last);
            String fileName = path.substring(last + 1);
            return getDirectory(dirs).getFile(fileName);
        } else
        {
            return getRoot().getFile(path);
        }
    }

    /**
     * Creates a subfilesystem from the current one with the given path as root.
     * The created filesystem uses the same data as the current one. This means
     * the 'parent' filesystem can access all files from the sub filesystem.
     *
     * @param dir the new root directory
     * @return a new filesystem with the given directory as root
     * @throws IOException if an I/O Exception occures e.g. the given directory
     * can't be found
     * @since 0.3.0
     */
    public CFileSystem subFileSystem(String dir) throws IOException;
}
