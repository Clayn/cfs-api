package net.bplaced.clayn.cfs;

import java.io.IOException;
import java.util.List;
import net.bplaced.clayn.cfs.util.Child;

/**
 * A Directory contains files and other directories. It provides method to
 * switch to other directories and get files inside of it.
 *
 * @author Clayn
 * @since 0.1
 * @version $Revision: 318 $
 */
public interface Directory extends Child<Directory>, Deletable
{

    /**
     * Tests wether or not the directory already exists or not. Existing means
     * it was created and no other directory can be created with that parent and
     * name.
     *
     * @return {@code true} if and only if the directory exists {@code false}
     * otherwise.
     * @since 0.1
     */
    public boolean exists();

    /**
     * Changes the directory with the path relative to this one and returns it.
     * The path may contain unix like parts such as {@code .} and {@code ..}. If
     * the path starts with a {@code /} the path should be relative to the root.
     * The unix parts should work like the user is used to it i.e. {@code ..}
     * changes to the parent and {@code .} is the current directory.<br><br>
     * <b>Note: </b>Implementations may work different. In such cases the
     * documentation should specifie how it works.
     *
     * @param path the path for the directory to switch to.
     * @return the directory with the given path relative to this one
     * @throws IOException if an I/O Exception occures
     * @since 0.1
     */
    public Directory changeDirectory(String path) throws IOException;

    /**
     * Returns the file with the given name inside of this directory. The file
     * returned may not exist and has to be created. This method may not resolve
     * relatives paths but takes the name as it is.
     *
     * @param name the name for the file
     * @return the file with the given name and this directory as parent
     * @throws IOException if an I/O Exception occures
     * @since 0.1
     */
    public SimpleFile getFile(String name) throws IOException;

    /**
     * Lists all files that pass through the given filter. Only files are
     * returned so sub directories won't be in the list. All returned files 
     * exist.
     *
     * @param sff the filter to check the files
     * @return a list of all files in the directory that match the given filter
     * @throws IOException if an I/O Exception occures
     * @since 0.1
     * @see #listFiles()
     * @see #listDirectories()
     */
    public List<SimpleFile> listFiles(SimpleFileFilter sff) throws IOException;

    /**
     * Returns a list of all files in this directory. All files in the returned
     * list have this directory as parent. This method does no filtering.
     *
     * @return a list of all files from this directory
     * @throws IOException if an I/O Exception occures
     * @see #listDirectories()
     * @see #listFiles(net.bplaced.clayn.cfs.SimpleFileFilter)
     * @since 0.1
     * @default {@code return listFiles(SimpleFileFilter.all());}
     */
    public default List<SimpleFile> listFiles() throws IOException
    {
        return listFiles(SimpleFileFilter.all());
    }

    /**
     * Lists all directories that are within this directory. This means all
     * directories in the returned list have this one as parent.
     *
     * @return a list with all directories with this directory as parent
     * @throws IOException if an I/O Exception occures
     * @see #listFiles()
     * @see #listFiles(net.bplaced.clayn.cfs.SimpleFileFilter)
     * @since 0.1
     */
    public List<Directory> listDirectories() throws IOException;

    /**
     * Deletes this directory if it exists. After calling this method
     * {@link #exists()} has to return {@code false}.
     *
     * @throws IOException if an I/O Exception occures
     * @since 0.1
     */
    @Override
    public void delete() throws IOException;

    /**
     * Creates the directory if it doesn't exist. This fails if one or more
     * parent directories don't exist.
     *
     * @throws IOException if an I/O Exception occures
     * @see #mkDirs()
     * @since 0.1
     */
    public void mkDir() throws IOException;

    /**
     * Creates the directory together with all not existing directories.
     *
     * @throws IOException if an I/O Exception occures.
     * @see #mkDir()
     * @since 0.1
     * @default checks for existence and then uses
     * {@link #mkDirs() getParent().mkDirs()} before creating it using
     * {@link #mkDir()}
     */
    public default void mkDirs() throws IOException
    {
        if (exists())
        {
            return;
        }
        Directory dir = this;
        if (dir.getParent() != null)
        {
            dir.getParent().mkDirs();
        }
        mkDir();
    }

    /**
     * Returns the name for this directory. Different directories may have the
     * same name as long as they have different parents.
     *
     * @return the name of the directory
     * @since 0.2.0
     */
    public String getName();

    /**
     * Returns wether or not this directory is the root directory or not.
     *
     * @return {@code true} if and only if {@code getParent() == null} is
     * {@code true}, {@code false} otherwise.
     * @since 0.2.0
     */
    public default boolean isRoot()
    {
        return getParent() == null;
    }

    /**
     * Checks if the given directories are the same or not. The directories are
     * the same when they have the same parent and their name is equals (case
     * sensitive).
     *
     * @param dir1 the first directory to check
     * @param dir2 the second directory to check
     * @return {@code ture} if and only if the directories have the same parent
     * and the same name, {@code false} otherwise.
     * @since 0.2.0
     */
    public static boolean equals(Directory dir1, Directory dir2)
    {
        if (dir1 == dir2)
        {
            return true;
        }
        if (dir1 == null)
        {
            return false;
        }
        if (dir1.isRoot() && dir2.isRoot())
        {
            return true;
        }
        if (Directory.equals(dir1.getParent(), dir2.getParent()))
        {
            return dir1.getName().equals(dir2.getName());
        }
        return false;
    }
    
    public default SimpleFile resolveFile(String path) throws IOException
    {
        if(!path.contains("/"))
        {
            return getFile(path);
        }
        else
        {
            String dir=path.substring(0, path.lastIndexOf("/"));
            String file=path.substring(path.lastIndexOf("/")+1);
            Directory parent=changeDirectory(dir);
            return parent.getFile(file);
        }
    }
}
