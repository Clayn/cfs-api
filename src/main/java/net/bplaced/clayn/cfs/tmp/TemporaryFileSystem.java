package net.bplaced.clayn.cfs.tmp;

import java.io.Closeable;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.bplaced.clayn.cfs.ActiveDirectory;
import net.bplaced.clayn.cfs.CFileSystem;
import net.bplaced.clayn.cfs.Directory;
import net.bplaced.clayn.cfs.FileSettings;
import net.bplaced.clayn.cfs.SimpleFile;

/**
 *
 * @author Clayn
 * @since 0.1
 * @version $Revision: 318 $
 * @development
 */
public class TemporaryFileSystem implements Closeable, CFileSystem
{

    private final CFileSystem workingFileSystem;

    public TemporaryFileSystem(CFileSystem fileSystem)
    {
        this.workingFileSystem = fileSystem;
    }

    @Override
    public void close() throws IOException
    {
        removeDirectory(getRoot());
    }

    private void removeDirectory(Directory dir) throws IOException
    {
        if (!dir.exists())
        {
            return;
        }
        dir.listFiles().forEach((SimpleFile t)
                -> 
                {
                    try
                    {
                        t.delete();
                    } catch (IOException ex)
                    {
                        Logger.getLogger(TemporaryFileSystem.class.getName()).log(
                                Level.SEVERE,
                                null, ex);
                        throw new RuntimeException(ex);
                    }
        });
        for (Directory d : dir.listDirectories())
        {
            removeDirectory(d);
        }
        dir.delete();
    }

    @Override
    public char getSeparatorChar()
    {
        return workingFileSystem.getSeparatorChar();
    }

    @Override
    public String getSeparator()
    {
        return workingFileSystem.getSeparator();
    }

    @Override
    public Directory getRoot() throws IOException
    {
        return workingFileSystem.getRoot();
    }

    @Override
    public Directory getDirectory(String path) throws IOException
    {
        return workingFileSystem.getDirectory(path);
    }

    @Override
    public boolean isActiveDirectorySupported()
    {
        return workingFileSystem.isActiveDirectorySupported();
    }

    @Override
    public ActiveDirectory getActiveDirectory(String path) throws IOException
    {
        return workingFileSystem.getActiveDirectory(path);
    }

    @Override
    public ActiveDirectory getActiveRoot() throws IOException
    {
        return workingFileSystem.getActiveRoot();
    }

    @Override
    public FileSettings getFileSettings()
    {
        return workingFileSystem.getFileSettings();
    }

    @Override
    public SimpleFile getFile(String path) throws IOException
    {
        return workingFileSystem.getFile(path);
    }

}
