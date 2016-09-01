package net.bplaced.clayn.cfs.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import net.bplaced.clayn.cfs.CFileSystem;

/**
 * A filetree represents a hierachy of files and diretories for a filesystem.
 * This may be used to create a structure for your application that will be
 * passed to an filesystem without thinking about the implementation. A file
 * system may be created from a local directory and create the same tree with a
 * filesystem.
 *
 * @author Clayn
 * @since 0.1
 * @version $Revision: 329 $
 */
public class FileTree
{

    private final List<String> directories = new ArrayList<>();
    private final List<String> files = new ArrayList<>();

    /**
     * Adds a file to the filetree. The file will be added to the root of the
     * tree and therefore need separation characters to be in a subdirectory
     *
     * @param fileName the name of the file íncluding parent directories
     * @return the same filetree
     * @see #addDirectory(java.lang.String)
     * @since 0.1
     */
    public FileTree addFile(String fileName)
    {
        files.add(fileName);
        return this;
    }

    /**
     * Adds a directory to the filetree. The directory will be created in the
     * root of the tree and subdirectories must be specified in the given name.
     *
     * @param dir the directory to add including parent directories
     * @return the same filetree
     * @see #addFile(java.lang.String)
     * @since 0.1
     */
    public FileTree addDirectory(String dir)
    {
        directories.add(dir);
        return this;
    }

    /**
     * Creates the structure represented by this filetree using the given file
     * system. The {@code platformSeparator} are used to convert OS depending
     * separators with the ones from the given filesystem. One way to obtain the
     * separator is {@link File#separator}.
     *
     * @param cfs the filesystem where the filetree should be created
     * @param platformSeparator the separator from the filetree files that will
     * be converted to the filesystems one-
     * @throws IOException if an I/O Exception occures
     * @since 0.1
     */
    public void create(CFileSystem cfs, String platformSeparator) throws IOException
    {
        String sep = cfs.getSeparator();
        for (String dir : directories)
        {
            dir = dir.replace(platformSeparator, sep);
            cfs.getDirectory(dir).mkDirs();
        }
        for (String file : files)
        {
            file = file.replace(platformSeparator, sep);
            cfs.getFile(file).createSafe();
        }
    }

    /**
     * Analyzes the given file and returns a new filetree. If the {@code root}
     * is a file, the filetree only contains that file. If it was a directory
     * the filetree will contain all files directories from the root. The files
     * will be added recursive.
     *
     * @param root the root file for the file tree
     * @return a filetree with all files and directories from the root
     * @since 0.1
     */
    public static FileTree analyze(File root)
    {
        if (!root.exists())
        {
            throw new RuntimeException();
        }
        FileTree tree = new FileTree();
        if (root.isFile())
        {
            tree.addFile(root.getName());
        } else
        {
            fillTree(tree, root, root);
        }
        return tree;
    }

    private static void fillTree(FileTree tree, File dir, File root)
    {
        Path rPath = root.toPath();
        File[] files = dir.listFiles();
        if (files == null)
        {
            return;
        }
        for (File f : files)
        {
            if (f == null)
            {
                continue;
            }
            if (f.isFile())
            {
                Path fPath = f.toPath();
                tree.addFile(rPath.relativize(fPath).toString());
            } else
            {
                Path dPath = f.toPath();
                tree.addDirectory(rPath.relativize(dPath).toString());
                fillTree(tree, f, root);
            }
        }
    }
}
