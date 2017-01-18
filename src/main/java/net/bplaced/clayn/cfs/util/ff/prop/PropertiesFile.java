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
package net.bplaced.clayn.cfs.util.ff.prop;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;
import net.bplaced.clayn.cfs.SimpleFile;
import net.bplaced.clayn.cfs.util.ff.FormatedFile;

/**
 *
 * @author Clayn <clayn_osmato@gmx.de>
 */
public class PropertiesFile extends FormatedFile
{
    private final Properties prop=new Properties();
    private final SimpleFile sf;
    public PropertiesFile(SimpleFile file) throws IOException
    {
        super(file);
        this.sf=file;
    }
    
    public void load() throws IOException
    {
        if(!sf.exists())
            return;
        try(InputStream in=sf.openRead())
        {
            prop.load(in);
        }
    }
    
    public void store() throws IOException
    {
        try(OutputStream out=sf.openWrite())
        {
            prop.store(out, null);
        }
    }
    
    public String getProperty(String key, String def)
    {
        return prop.getProperty(key, def);
    }
    
    public String getProperty(String key)
    {
        return prop.getProperty(key);
    }
    
    public void setProperty(String key, String val)
    {
        prop.setProperty(key, val);
    }
}
