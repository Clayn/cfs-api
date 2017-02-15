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
package net.bplaced.clayn.cfs.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;
import net.bplaced.clayn.cfs.CFileSystem;
import net.bplaced.clayn.cfs.Directory;
import net.bplaced.clayn.cfs.SimpleFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.event.Level;

/**
 * Utility class with some handy method that use in and/or output.
 *
 * @author Clayn
 * @since 0.1
 */
public final class IOUtils
{

    private static final Logger LOG = LoggerFactory.getLogger(
            IOUtils.class.getName());

    private static int BUFFER_SIZE = 1024;

    private IOUtils() throws IllegalAccessException
    {
        throw new IllegalAccessException("IOUtils should never be constructed");
    }

    /**
     * Sets the buffer size for reading operations used e.g. in
     * {@link #copy(java.io.InputStream, java.io.OutputStream)}. If the given
     * value is {@code <= 0} the buffer size will be untouched. Changing the
     * buffer size may increase the performance but also can decrease it so use
     * it only if you have to.
     *
     * @param size the new size for the buffer.
     * @since 0.1
     */
    public static void setBufferSize(int size)
    {
        BUFFER_SIZE = size > 0 ? size : BUFFER_SIZE;
    }

    /**
     * Cleans the given path and makes it uniform. Cleaning means that all
     * multiple occurences of {@code /} in a row will be cut to just one. If the
     * path started {@code /} this character will be removed so that the
     * returned path is always relative to the root.
     *
     * @param path the path to clean
     * @return the uniformed path
     * @since 0.1
     */
    public static String cleanPath(String path)
    {
        boolean lastSlash = false;
        boolean root = path.startsWith("/");
        StringBuilder builder = new StringBuilder(path.length());
        for (char ch : path.toCharArray())
        {
            if (ch == '/')
            {
                if (lastSlash)
                {
                } else
                {
                    lastSlash = true;
                    builder.append('/');
                }
            } else
            {
                builder.append(ch);
                lastSlash = false;
            }
        }
        path = builder.toString();
        if (path.endsWith("/"))
        {
            path = path.substring(0, path.length() - 1);
        }
        return root ? path.substring(1) : path;
    }

    /**
     * Copies the content from the {@code from} file to the {@code to} file. If
     * the destination file already exists it's content will be overwritten.
     *
     * @param from the file to copy
     * @param to the destination for the copy
     * @throws IOException if an I/O Exception occures
     * @since 0.1
     * @see #copy(java.io.InputStream, java.io.OutputStream)
     * @log {@link Level#INFO}
     */
    public static void copy(SimpleFile from, SimpleFile to) throws IOException
    {
        if (LOG.isInfoEnabled())
        {
            LOG.info("Copy from file {0} to file {1}", from, to);
        }
        try (InputStream in = from.openRead();
                OutputStream out = to.openWrite())
        {
            copy(in, out);
        }
    }

    /**
     * Copies all data read from the given input to the given output. This
     * method reads as long as there is data available on the input and waits
     * may block the thread if the reading takes some time. The streams remain
     * open after this method.
     *
     * @param in the input to read from
     * @param out the output to write to.
     * @throws IOException if an I/O Exception occures or the thread gets
     * interrupted
     * @see #copy(net.bplaced.clayn.cfs.SimpleFile,
     * net.bplaced.clayn.cfs.SimpleFile)
     * @see #copy(net.bplaced.clayn.cfs.CFileSystem,
     * net.bplaced.clayn.cfs.CFileSystem)
     * @see #copy(net.bplaced.clayn.cfs.Directory,
     * net.bplaced.clayn.cfs.Directory)
     * @since 0.1
     * @log {@link Level#ERROR}
     */
    public static void copy(InputStream in, OutputStream out) throws IOException
    {
        try
        {
            byte[] buffer = new byte[BUFFER_SIZE];
            int len = in.read(buffer);
            while (len != -1)
            {
                out.write(buffer, 0, len);
                len = in.read(buffer);
                if (Thread.interrupted())
                {
                    throw new InterruptedException();
                }
            }
            out.flush();
        } catch (InterruptedException ex)
        {
            if (LOG.isErrorEnabled())
            {
                LOG.error("An error occured during the copy process", ex);
            }
            throw new IOException(ex);
        }
    }

    /**
     * Reads all bytes from the given file and stores them into an array.
     *
     * @param file the file to read from
     * @return the bytes read from the file
     * @throws IOException if an I/O Exception occures
     * @since 0.1
     */
    public static byte[] readAllBytes(SimpleFile file) throws IOException
    {
        return CFiles.readAllBytes(file);
    }

    /**
     * Reads all lines available in the given file. Lines will be separated by
     * the line separator (see {@link BufferedReader#readLine() readLine()}).
     * The stream will be closed afterwards.
     *
     * @param file the file to read from
     * @return all lines available in the file 
     * @throws IOException if an I/O Exception occures
     * @see #readAllLines(java.io.InputStream) 
     * @since 0.3.0
     */
    public static List<String> readAllLines(SimpleFile file) throws IOException
    {
        return readAllLines(file.openRead());
    }

    /**
     * Reads all lines available from the given inputstream. Lines will be
     * separated by the line separator (see
     * {@link BufferedReader#readLine() readLine()}). The stream will be closed
     * afterwards.
     *
     * @param in the inputstream to read from.
     * @return all lines read from the given inputstream
     * @throws IOException if an I/O Exception occures
     * @see #readAllLines(net.bplaced.clayn.cfs.SimpleFile)
     * @since 0.3.0
     */
    public static List<String> readAllLines(InputStream in) throws IOException
    {
        List<String> lines = new ArrayList<>();
        String line;
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(in)))
        {
            while ((line = reader.readLine()) != null)
            {
                lines.add(line);
            }
        }
        return lines;
    }

    /**
     * Creates a Zip File backup of the given filesystems content. Each
     * directory will be added together with their files. Empty directories will
     * also be included.
     *
     * @param cfs the filesystem to backup
     * @param backFile the outputfile for the backup
     * @throws IOException if an I/O Exception occures
     * @since 0.2.0
     * @see #extractFromZip(net.bplaced.clayn.cfs.CFileSystem,
     * java.util.zip.ZipFile)
     * @see #extractFromZip(net.bplaced.clayn.cfs.CFileSystem,
     * java.util.zip.ZipFile, java.lang.String)
     */
    public static void backUpToZip(CFileSystem cfs, File backFile) throws IOException
    {
        try (ZipOutputStream zout = new ZipOutputStream(new FileOutputStream(
                backFile)))
        {
            zipDir(zout, cfs.getRoot());
            zout.finish();
            zout.flush();
        }
    }

    /**
     * Extracts the content from the given backup file into the filesystem. This
     * will overwrite files with the same path that currently exist in the
     * filesystem.
     *
     * @param cfs the filesystem the content gets extracted to
     * @param backUp the backup to extract
     * @throws IOException if an I/O Exception occures
     * @since 0.2.0
     * @see #backUpToZip(net.bplaced.clayn.cfs.CFileSystem, java.io.File)
     * @see #extractFromZip(net.bplaced.clayn.cfs.CFileSystem,
     * java.util.zip.ZipFile, java.lang.String)
     */
    public static void extractFromZip(CFileSystem cfs, ZipFile backUp) throws IOException
    {
        extractFromZip(cfs, backUp, null);
    }

    /**
     * Extracts the content from the given backup file into the filesystem. This
     * will overwrite files with the same path that currently exist in the
     * filesystem. Using the {@code rootReplace} parameter you can remove the
     * prefix of a path that matches the parameter.
     *
     * @param cfs the filesystem the content gets extracted to
     * @param backUp the backup to extract
     * @param rootReplace the pathprefix that should be removed from the files
     * if existent
     * @throws IOException if an I/O Exception occures
     * @since 0.2.0
     * @see #extractFromZip(net.bplaced.clayn.cfs.CFileSystem,
     * java.util.zip.ZipFile)
     * @see #backUpToZip(net.bplaced.clayn.cfs.CFileSystem, java.io.File)
     */
    public static void extractFromZip(CFileSystem cfs, ZipFile backUp,
            String rootReplace) throws IOException
    {
        if (rootReplace != null)
        {
            rootReplace = rootReplace.endsWith("/") ? rootReplace : rootReplace + "/";
        }
        try (ZipFile zip = backUp)
        {
            Enumeration<? extends ZipEntry> entries = zip.entries();
            while (entries.hasMoreElements())
            {
                ZipEntry entry = entries.nextElement();
                String name = entry.getName();
                if (rootReplace != null && name.startsWith(rootReplace))
                {
                    if (name.equals(rootReplace))
                    {
                        continue;
                    }
                    name = name.substring(rootReplace.length());
                }
                if (entry.isDirectory())
                {
                    Directory dir = cfs.getDirectory(name);
                    dir.mkDirs();
                } else
                {
                    SimpleFile file = cfs.getFile(name);
                    file.createSafe();
                    try (InputStream in = zip.getInputStream(entry); OutputStream out = file.openWrite())
                    {
                        IOUtils.copy(in, out);
                    }
                }
            }
        }
    }

    private static void zipDir(ZipOutputStream zout, Directory dir) throws IOException
    {
        String path = dir.toString();
        if (!path.endsWith("/"))
        {
            path = path + "/";
        }
        if (!dir.isRoot())
        {
            zout.putNextEntry(new ZipEntry(path));
            zout.closeEntry();
        }
        for (Directory sub : dir.listDirectories())
        {
            zipDir(zout, sub);
        }
        for (SimpleFile file : dir.listFiles())
        {
            if (!file.exists())
            {
                continue;
            }
            path = file.toString();
            ZipEntry entry = new ZipEntry(path);
            zout.putNextEntry(entry);
            try (InputStream in = file.openRead())
            {
                IOUtils.copy(in, zout);
            }
            zout.closeEntry();
        }
    }

    /**
     * Moves a file from it's currten location to the new one. If the
     * destination file already exists, it will be overwritten. The file that
     * was moved will be deleted afterwards.
     *
     * @param from the file that will be moved
     * @param to the destination for the new file
     * @throws IOException if an I/O Exception occures
     * @see #copy(net.bplaced.clayn.cfs.SimpleFile,
     * net.bplaced.clayn.cfs.SimpleFile)
     * @since 0.3.0
     */
    public static void move(SimpleFile from, SimpleFile to) throws IOException
    {
        to.createSafe();
        copy(from, to);
        from.delete();
    }

    /**
     * Copies the complete source filesystem to the destination. This will
     * overwrite all files in the destination filesystem that are available in
     * the source one. After copying all files from the source will be available
     * in the destination.
     *
     * @param from the filesystem to copy from
     * @param to the filesystem to copy to
     * @throws IOException if an I/O Exception occures
     * @see #copy(net.bplaced.clayn.cfs.Directory,
     * net.bplaced.clayn.cfs.Directory)
     * @see #copy(java.io.InputStream, java.io.OutputStream)
     * @see #copy(net.bplaced.clayn.cfs.SimpleFile,
     * net.bplaced.clayn.cfs.SimpleFile)
     */
    public static void copy(CFileSystem from, CFileSystem to) throws IOException
    {
        Directory fRoot = from.getRoot();
        Directory tRoot = to.getRoot();
        copy(fRoot, tRoot);
    }

    /**
     * Copies all content from the source directory to the destination
     * directory. This copies all directories and files recursive.
     *
     * @param from the directory to copy the content from
     * @param to the directory to copy the content to
     * @throws IOException if an I/O Exception occurs.
     * @since 0.3.0
     * @see #copy(net.bplaced.clayn.cfs.CFileSystem,
     * net.bplaced.clayn.cfs.CFileSystem)
     * @see #copy(java.io.InputStream, java.io.OutputStream)
     * @see #copy(net.bplaced.clayn.cfs.SimpleFile,
     * net.bplaced.clayn.cfs.SimpleFile)
     */
    public static void copy(Directory from, Directory to) throws IOException
    {
        for (Directory dir : from.listDirectories())
        {
            Directory tDir = to.changeDirectory(dir.getName());
            if (!tDir.exists())
            {
                tDir.mkDirs();
            }
            copy(dir, tDir);
        }
        for (SimpleFile file : from.listFiles())
        {
            SimpleFile tFile = to.getFile(file.getName());
            copy(file, tFile);
        }
    }
}
