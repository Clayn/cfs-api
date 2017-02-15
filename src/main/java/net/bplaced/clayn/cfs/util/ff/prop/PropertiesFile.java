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
 * A {@link FormatedFile formated file} that provides access to stored
 * properties. With this class you can load and save the properties.
 *
 * @author Clayn <clayn_osmato@gmx.de>
 * @since 0.3.0
 */
public class PropertiesFile extends FormatedFile
{

    private final Properties prop = new Properties();
    private final SimpleFile sf;

    /**
     * Creates a new properties file that reads from the given file.
     *
     * @param file file to use
     * @throws IOException if an I/O Exception occures
     * @since 0.3.0
     */
    public PropertiesFile(SimpleFile file) throws IOException
    {
        super(file);
        this.sf = file;
    }

    /**
     * Loads all properties from the file.
     *
     * @throws IOException if an I/O Exception occures
     * @since 0.3.0
     * @see Properties#load(java.io.InputStream)
     */
    public void load() throws IOException
    {
        if (!sf.exists())
        {
            return;
        }
        try (InputStream in = sf.openRead())
        {
            prop.load(in);
        }
    }

    /**
     * Saves the properties to the file. This makes every changes in the
     * properties persistent.
     *
     * @throws IOException if an I/O Exception occures
     * @since 0.3.0
     * @see Properties#store(java.io.OutputStream, java.lang.String)
     */
    public void store() throws IOException
    {
        try (OutputStream out = sf.openWrite())
        {
            prop.store(out, null);
        }
    }

    /**
     * Returns the property with the given key. If no such property was found,
     * the {@code def} value will be returned.
     *
     * @param key the key of the property to retrieve
     * @param def the default value returned if no property was found
     * @return the property stored with the given key or {@code def} if no such
     * property was found.
     * @since 0.3.0
     * @see #getProperty(java.lang.String)
     */
    public String getProperty(String key, String def)
    {
        return prop.getProperty(key, def);
    }

    /**
     * Returns the property with the given key.
     *
     * @param key the key of the property
     * @return the value stored with the given key or {@code null} if no such
     * property was found.
     * @since 0.3.0
     * @see #getProperty(java.lang.String, java.lang.String)
     */
    public String getProperty(String key)
    {
        return prop.getProperty(key);
    }

    /**
     * Sets the property with the given key to the given value. If the property
     * didn't exist it will be created. Setting the propert will have no affect
     * to the file until {@link #store() } was called.
     *
     * @param key the key for the property
     * @param val the new value for the property
     * @since 0.3.0
     */
    public void setProperty(String key, String val)
    {
        prop.setProperty(key, val);
    }
}
