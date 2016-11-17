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
package net.bplaced.clayn.cfs.util.ff.tmp;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import net.bplaced.clayn.cfs.CFileSystem;
import net.bplaced.clayn.cfs.SimpleFile;
import net.bplaced.clayn.cfs.util.IOUtils;
import net.bplaced.clayn.cfs.util.ff.FormatedFile;

/**
 * A local temporary file holds data from a existing {@link SimpleFile file}.
 * The temporary file will be deleted after the JVM terminates. This can become
 * usefull when you need a {@link File} but want to work with a
 * {@link CFileSystem cfs}. By using this class you can get all the content of
 * the file and store it temporary into your local filesystem.
 *
 * @author Clayn <clayn_osmato@gmx.de>
 * @since 0.2.0
 */
public class TemporaryFile extends FormatedFile
{

    private final File tempFile;

    public TemporaryFile(SimpleFile file) throws IOException
    {
        super(file);
        String name = file.getName();
        tempFile = Files.createTempFile(name.substring(0, name.lastIndexOf(".")),
                name.substring(name.lastIndexOf(".") + 1)).toFile();
        try (InputStream in = file.openRead(); OutputStream out = new FileOutputStream(
                tempFile))
        {
            IOUtils.copy(in, out);
        }
    }

    /**
     * Returns the temporary file that was created in the local filesystem.
     * @return the temporary created file.
     * @since 0.2.0
     */
    public File getFile()
    {
        return tempFile;
    }

}
