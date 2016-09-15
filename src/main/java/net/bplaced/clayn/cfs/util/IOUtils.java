package net.bplaced.clayn.cfs.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import net.bplaced.clayn.cfs.CFileSystem;
import net.bplaced.clayn.cfs.Directory;
import net.bplaced.clayn.cfs.SimpleFile;

/**
 * Utility class with some handy method that use in and/or output.
 *
 * @author Clayn
 * @since 0.1
 */
public final class IOUtils
{

    private static int BUFFER_SIZE = 1024;

    private IOUtils() throws IllegalAccessException
    {
        throw new IllegalAccessException("IOUtils should never be constructed");
    }

    /**
     * Sets the buffer size for reading operations used e.g. in
     * {@link #copy(java.io.InputStream, java.io.OutputStream)}. If the given
     * value is {@code <= 0} the buffer size will be untoched. Changing the
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
     */
    public static void copy(SimpleFile from, SimpleFile to) throws IOException
    {

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
     * @since 0.1
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
            Logger.getLogger(IOUtils.class.getName()).log(Level.SEVERE, null, ex);
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
     * Creates a Zip File backup of the given filesystems content. Each
     * directory will be added together with their files. Empty directories will
     * also be included.
     *
     * @param cfs the filesystem to backup
     * @param backFile the outputfile for the backup
     * @throws IOException if an I/O Exception occures
     * @since 0.2.0
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

    private static void zipDir(ZipOutputStream zout, Directory dir) throws IOException
    {
        String path = dir.toString();
        if (!path.endsWith("/"))
        {
            path = path + "/";
        }
        if(!dir.isRoot())
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
}
