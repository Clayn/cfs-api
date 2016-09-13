/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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

    public File getFile()
    {
        return tempFile;
    }

}
