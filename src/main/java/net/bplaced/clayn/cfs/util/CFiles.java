package net.bplaced.clayn.cfs.util;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Stream;
import net.bplaced.clayn.cfs.Directory;
import net.bplaced.clayn.cfs.SimpleFile;

/**
 * Utility class similar to {@link java.nio.file.Files Files} to provide similar
 * methods for {@link SimpleFile SimpleFiles}. Though not all methods will be
 * adapted the most common can be found here.
 *
 * @author Clayn
 * @since 0.2
 */
public final class CFiles
{

    /**
     * Reads all lines from the given file. The bytes are interpreted using the
     * Charset from the file returned by {@link SimpleFile#getCharset()}.
     *
     * @param sf the file to read from
     * @return a list with all lines read from the given file. Never
     * {@code null}.
     * @throws IOException if an I/O Exception occures
     * @see java.nio.file.Files#readAllLines(java.nio.file.Path)
     */
    public static List<String> readAllLines(SimpleFile sf) throws IOException
    {
        List<String> lines = new ArrayList<>();
        if (sf.exists())
        {
            try (BufferedReader reader = newBufferedReader(sf))
            {
                StreamUtils.generateWhile(reader::readLine,
                        Objects::nonNull).forEach(lines::add);
            }
        }
        return lines;
    }

    /**
     * Calculates the size of the directory by calculating the size of it's files and 
     * subdirectories.
     * @param dir the directory which size will be calculated
     * @return the size of the directory
     * @throws RuntimeException if an exception occures while listing the files 
     * and directories.
     * @since 0.2
     */
    public static long getSize(Directory dir)
    {
        Function<SimpleFile,Long> getSize=(SimpleFile t) ->
        {
            try
            {
                return t.getSize();
            } catch (IOException ex)
            {
                return 0l;
            }
        };
        try
        {
            return Stream.concat(dir.listDirectories().stream().map(CFiles::getSize),dir.listFiles().stream().map(getSize))
                    .mapToLong(Long::longValue).sum();
        } catch (IOException ex)
        {
            throw new RuntimeException(ex);
        }
    }
    
    /**
     * Creates a new {@link BufferedReader} that reads from the given file. The 
     * reader uses the files charset.
     * @param sf the file to read from
     * @return a new reader to read from the file
     * @throws IOException if an I/O Exception occures during the creation of the reader.
     * @see java.nio.file.Files#newBufferedReader(java.nio.file.Path) 
     */
    public static BufferedReader newBufferedReader(SimpleFile sf) throws IOException
    {
        return sf.exists() ? new BufferedReader(new InputStreamReader(
                sf.openRead(), sf.getCharset())) : null;
    }

    /**
     * Attempts to read all byte from the given file. This may fail if the files
     * size is {@code >2Gb}. In that case consider to stream from that file. If
     * the size is good, all bytes from that file will be in the returned array.
     * Though this method may throw an {@link OutOfMemoryError}, the memory will
     * be fine since the size will be checked before any memory gets allocated.
     *
     * @param sf the file to read from
     * @return the bytes from the file
     * @throws IOException if an I/O Exception occures
     * @throws OutOfMemoryError if the files size is {@code >2Gb} and can't be read 
     * into an array.
     * @see java.nio.file.Files#readAllBytes(java.nio.file.Path) 
     */
    public static byte[] readAllBytes(SimpleFile sf) throws IOException, OutOfMemoryError
    {
        if (!sf.exists())
        {
            return new byte[]
            {
            };
        }
        if (sf.getSize() >= (long) Integer.MAX_VALUE)
        {
            throw new OutOfMemoryError(
                    "Filesize " + sf.getSize() + " exceeds the limit of 2Gb");
        }
        byte[] data;
        try (ByteArrayOutputStream bout = new ByteArrayOutputStream(
                (int) sf.getSize()); InputStream in = sf.openRead())
        {
            IOUtils.copy(in, bout);
            data = bout.toByteArray();
        }
        return data;
    }
}
