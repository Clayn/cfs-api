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
import net.bplaced.clayn.cfs.util.SizeUnit;

/**
 * A link represents a file without beeing a real file. A link works just like a
 * real file but only delegates the calls to the linked file. This means reading
 * and writing operations are made on the real file instead of the link file.
 * Due to that behaviour links are 'followed' automatic.
 *
 * @author Clayn <clayn_osmato@gmx.de>
 * @since 0.3.0
 */
public class Link implements SimpleFile
{

    private final SimpleFile linkedFile;

    /**
     * Creates a new link to the given file. One file can have more than one 
     * link. 
     * @param linkedFile the file to be linkend. Musn't be {@code null}.
     * @since 0.3.0
     */
    public Link(SimpleFile linkedFile)
    {
        this.linkedFile = Objects.requireNonNull(linkedFile, "Can't create link for 'null'");
    }

    @Override
    public boolean exists()
    {
        return linkedFile.exists();
    }

    @Override
    public void create() throws IOException
    {
        linkedFile.create();
    }

    @Override
    public void createSafe() throws IOException
    {
        linkedFile.createSafe();
    }

    @Override
    public void delete() throws IOException
    {
        linkedFile.delete();
    }

    @Override
    public IOStream open(OpeningType type) throws IOException
    {
        return linkedFile.open(type);
    }

    @Override
    public InputStream openRead() throws IOException
    {
        return linkedFile.openRead();
    }

    @Override
    public OutputStream openWrite() throws IOException
    {
        return linkedFile.openWrite();
    }

    @Override
    public String getName()
    {
        return linkedFile.getName();
    }

    @Override
    public long getSize() throws IOException
    {
        return linkedFile.getSize();
    }

    @Override
    public double getSize(SizeUnit unit) throws IOException
    {
        return linkedFile.getSize(unit);
    }

    @Override
    public <R> R as(TryingFunction<SimpleFile, R> formatter)
    {
        return linkedFile.as(formatter);
    }

    @Override
    public Charset getCharset()
    {
        return linkedFile.getCharset();
    }

    @Override
    public OutputStream openAppend() throws IOException
    {
        return linkedFile.openAppend();
    }

    @Override
    public Directory getParent()
    {
        return linkedFile.getParent();
    }

    @Override
    public String toString()
    {
        return linkedFile.toString();
    }

    @Override
    public String getPath()
    {
        return linkedFile.getPath();
    }

}
