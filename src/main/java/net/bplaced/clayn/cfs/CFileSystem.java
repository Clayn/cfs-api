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
    
    public CFileSystem subFileSystem(String dir) throws IOException;
}
