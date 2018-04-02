/*
 * Copyright (C) 2017 Clayn <clayn_osmato@gmx.de>
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
package net.bplaced.clayn.cfs.util.mir;

import java.io.IOException;
import java.util.Objects;
import net.bplaced.clayn.cfs.ActiveDirectory;
import net.bplaced.clayn.cfs.CFileSystem;
import net.bplaced.clayn.cfs.FileModification;
import net.bplaced.clayn.cfs.SimpleFile;
import net.bplaced.clayn.cfs.err.CFSException;
import net.bplaced.clayn.cfs.util.IOUtils;

/**
 *
 * @author Clayn <clayn_osmato@gmx.de>
 * @since 0.1
 */
public class FileSystemMirror
{
    private final CFileSystem sourceCFS;
    private final CFileSystem destCFS;

    public FileSystemMirror(CFileSystem sourceCFS, CFileSystem destCFS) throws IOException
    {
        
        this.sourceCFS = Objects.requireNonNull(sourceCFS);
        this.destCFS = Objects.requireNonNull(destCFS);
        if(!sourceCFS.isActiveDirectorySupported())
        {
            throw new CFSException("Source filesystem must support ActiveDirectory");
        }
        init();
    }
    
    private void init() throws IOException
    {
        ActiveDirectory srcRoot=sourceCFS.getActiveRoot();
        srcRoot.setOnCreate(this::newFile);
        srcRoot.setOnModify(this::newFile);
        srcRoot.setOnDelete(this::deleteFile);
    }
    
    private void newFile(FileModification mod) throws IOException
    {
        SimpleFile file=mod.getFile();
        String path=file.getPath();
        SimpleFile mirrored=destCFS.getFile(path);
        mirrored.createSafe();
        IOUtils.copy(file, mirrored);
    }
    
    private void deleteFile(FileModification mod) throws IOException
    {
        String path=mod.getFile().getPath();
        SimpleFile file=destCFS.getFile(path);
        if(file.exists())
        {
            file.delete();
        }
    }
}
