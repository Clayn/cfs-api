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
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.Objects;
import net.bplaced.clayn.cfs.ext.functional.TryingFunction;
import net.bplaced.clayn.cfs.util.Child;
import net.bplaced.clayn.cfs.util.IOUtils;
import net.bplaced.clayn.cfs.util.SizeUnit;
import net.bplaced.clayn.cfs.util.Tuple;
import net.bplaced.clayn.cfs.util.ff.Formatable;

/**
 * This class represents a file with basic operations available to provide a
 * simple access to common file operations such as creating and opening.
 *
 * @author Clayn
 * @since 0.1
 * @version $Revision: 329 $
 */
public interface SimpleFile extends Child<Directory>, Formatable<SimpleFile>, Deletable
{

    /**
     * Checks wether the file exists or not.
     *
     * @return {@code true} if and only if the file exists, {@code false}
     * otherwise
     * @since 0.1
     */
    public boolean exists();

    /**
     * Creates the file in the case it doesn't exists. If the file already
     * exists this method may throw an Excepiot according to the implementation
     *
     * @throws IOException if an I/O Exception occures
     * @since 0.1
     */
    public void create() throws IOException;

    /**
     * Creates the file if it doesn't exists yet. If The file already exists
     * this method simply returns.
     *
     * @throws IOException if an I/O Exception occures
     * @since 0.1
     */
    public default void createSafe() throws IOException
    {
        if (exists())
        {
            return;
        }
        create();
    }

    /**
     * Deletes this file if it exists. This instance is still valid and may be
     * used to recreate the file.
     *
     * @throws IOException if an I/O Exception occures.
     * @since 0.1
     */
    @Override
    public void delete() throws IOException;

    /**
     * Opens the file for either reading or writing or both. The returned
     * IOStream contains the input or output streams needed for accessing the
     * file.
     *
     * @param type the opening type such as reading or writing
     * @return the IOStreams that wrappes the opend input or output streams
     * @throws IOException if an I/O Exception occures
     * @since 0.1
     */
    public default IOStream open(OpeningType type) throws IOException
    {
        Tuple<InputStream, OutputStream> streams = new Tuple<>();
        switch (type)
        {
            case READ:
                streams.setFirst(openRead());
                break;
            case WRITE:
                streams.setSecond(openWrite());
                break;
            case READ_WRITE:
                streams.setFirst(openRead());
                streams.setSecond(openWrite());
                break;
        }
        return new IOStream()
        {
            @Override
            public InputStream getInput() throws IOException
            {
                return streams.getFirst();
            }

            @Override
            public OutputStream getOutput() throws IOException
            {
                return streams.getSecond();
            }
        };
    }

    /**
     * Opens the file only for reading purposes. This method is for cases where
     * you don't want to deal with the OpeningType and simply want to read that
     * file
     *
     * @return the InputStream for reading from the file.
     * @throws IOException if an I/O Exception occures
     * @since 0.1
     */
    public InputStream openRead() throws IOException;

    /**
     * Opens the file only for writing purposes. This method is for cases where
     * you don't want to deal with the OpeningType and simply want to write to
     * that file. Note that it's not defined wether it is possible to append to
     * the file or always write it complete new. However for small files you may
     * copy the content to the "new" file before writing the content to append.
     *
     * @return the OutputStream for writing to the file.
     * @throws IOException if an I/O Exception occures
     * @see #openAppend()
     * @since 0.1
     */
    public OutputStream openWrite() throws IOException;

    /**
     * Returns the name of the file. The name is only the name with the
     * extension and no path at all.
     *
     * @return the name for the file
     * @since 0.1
     */
    public String getName();

    /**
     * Returns the size of this file in bytes.
     *
     * @return the size of the file in bytes
     * @throws IOException if the file does not exists or an other I/O Exception
     * occures
     * @since 0.1
     */
    public long getSize() throws IOException;

    /**
     * Returns the size of the file in the given sizeunit.
     *
     * @param unit the unit for the filesize
     * @return the filesize in the given sizeunit
     * @throws IOException if an I/O Exception occures
     * @see #getSize()
     * @since 0.1
     */
    public default double getSize(SizeUnit unit) throws IOException
    {
        return Objects.requireNonNull(unit).convert(getSize(), SizeUnit.BYTE);
    }

    /**
     * Formats the file using the given converter and returns it. This method
     * formats the interpretation of the file rather the concrete file itself.
     * In other words a text file converted to an image will stay an text file.
     *
     * @param <R> the type the file should be formatted to
     * @param formatter the function to format the file
     * @return the formatted file
     * @since 0.2.0
     */
    @Override
    public default <R> R as(TryingFunction<SimpleFile, R> formatter)
    {
        return formatter.apply(this);
    }

    /**
     * Returns the charset of this file.
     *
     * @return the files charset
     * @since 0.2.0
     */
    public default Charset getCharset()
    {
        return Charset.defaultCharset();
    }

    /**
     * Returns an OuputStream that appends all data written to it at the end of
     * the file. The default implementation writes all existing bytes to the
     * file and then returns the open stream to write to. Subclasses should
     * overwrite this behaiviour since its not very efficient especially for
     * large files.
     *
     * @return the OutputStream for appending the file
     * @throws IOException if an I/O Exception occures
     * @since 0.2.0 * @see #openWrite()
     */
    public default OutputStream openAppend() throws IOException
    {

//        byte[] data;
//        try(ByteArrayOutputStream bout=new ByteArrayOutputStream();InputStream in=openRead())
//        {
//            IOUtils.copy(in, bout);
//            data=bout.toByteArray();
//        }
        OutputStream out = openWrite();
//        try(ByteArrayInputStream bin=new ByteArrayInputStream(data))
//        {
//            IOUtils.copy(bin, out);
//        }
        try (InputStream in = openRead())
        {
            IOUtils.copy(in, out);
        }
        return out;
    }

    /**
     * Checks wether or not the given files are the same or not. A file is equal
     * when they have the same parent and an equal name (case sensitive).
     *
     * @param file1 the first file to check
     * @param file2 the second file to check
     * @return {@code true} if and only if the given files have the same name
     * and parents, {@code false} otherwise.
     * @since 0.2.0
     */
    public static boolean equals(SimpleFile file1, SimpleFile file2)
    {
        if (file1 == file2)
        {
            return true;
        }
        if (file1 == null)
        {
            return false;
        }
        if (Directory.equals(file1.getParent(), file2.getParent()))
        {
            return file1.getName().equals(file2.getName());
        }
        return false;
    }

    /**
     * Returns the path of the file. The path identifies the file in the current
     * filesystem.
     *
     * @return the path of the file
     * @since 0.3.0
     */
    public String getPath();

    /**
     * Returns the files attributes.
     *
     * @return the files attributes
     * @since 0.3.0
     */
    public default FileAttributes getFileAttributes()
    {
        return SimpleFileAttributes.defaultAttributes();
    }

    /**
     * Creates a read only file version from the given file. The returned file
     * will only allow operations that dont risk the files content. This means
     * you cant delete or write to the file but read from it. Creating is also
     * allowed to be sure and a file to read from should exist though. However
     * this doesn't prevent the originial file to be deleted somewhere else.
     *
     * @param orig the original file
     * @return a read only file representing the given one
     * @since 0.3.0
     */
    public static SimpleFile readOnly(SimpleFile orig)
    {
        SimpleFile self = orig;
        return new SimpleFile()
        {
            @Override
            public boolean exists()
            {
                return self.exists();
            }

            @Override
            public void create() throws IOException
            {
                throw new UnsupportedOperationException(
                        "Read only files don't support this operation");
            }

            @Override
            public void delete() throws IOException
            {
                throw new UnsupportedOperationException(
                        "Read only files don't support this operation");
            }

            @Override
            public InputStream openRead() throws IOException
            {
                return self.openRead();
            }

            @Override
            public OutputStream openWrite() throws IOException
            {
                throw new UnsupportedOperationException(
                        "Read only files don't support this operation");
            }

            @Override
            public String getName()
            {
                return self.getName();
            }

            @Override
            public long getSize() throws IOException
            {
                return self.getSize();
            }

            @Override
            public String getPath()
            {
                return self.getPath();
            }

            @Override
            public Directory getParent()
            {
                throw new UnsupportedOperationException(
                        "Read only files don't support this operation");
            }
        };
    }
}
