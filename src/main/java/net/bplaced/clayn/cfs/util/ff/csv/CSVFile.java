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
package net.bplaced.clayn.cfs.util.ff.csv;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.regex.Pattern;
import net.bplaced.clayn.cfs.SimpleFile;
import net.bplaced.clayn.cfs.err.CFSException;
import net.bplaced.clayn.cfs.util.ff.FormatedFile;

/**
 * Formated file for the csv format. The values may be separated by different
 * separators. Currently supported separators are {@code ;} {@code ,} {@code \t}
 * and {@code ' '} (whitespace). The first occurence of one of these separators
 * defines which one will be used for the file.
 *
 * @author Clayn
 * @since 0.2.0 
 */
public class CSVFile extends FormatedFile implements Closeable
{

    /**
     * The currently supported separation characters
     */
    public static final String SEPARATION_CHARS = ";,\t ";

    private final String[] header;
    private final BufferedReader reader;
    private String separation;

    /**
     * Interprets the given file as a csv file. When calling this constructor
     * the headers an separation char will be read and available without any
     * other action needed.
     *
     * @param file the file to interpret
     * @throws IOException if an I/O Exception occures
     * @throws CFSException if no separation cahracter was found
     * @since 0.2.0
     */
    public CSVFile(SimpleFile file) throws IOException
    {
        super(file);
        Objects.requireNonNull(file);
        reader = new BufferedReader(new InputStreamReader(file.openRead(),
                file.getCharset()));
        header = readHeaders();
        Optional.ofNullable(separation).orElseThrow(CFSException::new);
    }

    private String[] readHeaders() throws IOException
    {
        String line = reader.readLine();
        if (line == null)
        {
            return null;
        }
        for (char ch : line.toCharArray())
        {
            int i = SEPARATION_CHARS.indexOf(ch);
            if (i >= 0)
            {
                separation = String.valueOf(SEPARATION_CHARS.charAt(i));
                break;
            }
        }
        if (separation == null)
        {
            return null;
        }
        String[] split = line.split(Pattern.quote(separation));
        return split;
    }

    /**
     * Returns the headers read from this file.
     *
     * @return the headers
     * @since 0.2.0
     */
    public String[] getHeader()
    {
        return Arrays.copyOf(header, header.length);
    }

    /**
     * Reads all available lines from this file using {@link #readLine()} until
     * the end of the file is reached. If any read line causes an error no
     * already read lines will be returned
     *
     * @return a list with all lines from this file.
     * @throws IOException if an I/O Exception occures during the reading
     * @throws RuntimeException if a line caused an exception
     * @since 0.2.0 * @see #readLine()
     */
    public List<Map<String, String>> readAllLines() throws IOException
    {
        List<Map<String, String>> res = new ArrayList<>();
        Map<String, String> tmp;
        while ((tmp = readLine()) != null)
        {
            res.add(tmp);
        }
        return res;
    }

    /**
     * Reads a line an stores the values in a map with the column header of a
     * value as key. Every header returned by {@link #getHeader() } will be in
     * the keyset of the returned map. If a line has more or less values than
     * the count of headers an exception gets thrown.
     *
     * @return the values for the values of one line with the column headers as
     * keys
     * @throws IOException if an I/O Exception occures while reading
     * @throws RuntimeException if the count of read columns doesn't match the
     * headers count
     * @since 0.2.0 * @see #readAllLines()
     */
    public Map<String, String> readLine() throws IOException
    {
        String line = reader.readLine();
        if (line == null)
        {
            return null;
        }
        String[] split = line.split(Pattern.quote(separation));
        if (split.length != header.length)
        {
            throw new RuntimeException(String.format(
                    "Excpected %d columns but got %d", header.length,
                    split.length));
        }
        Map<String, String> result = new HashMap<>();
        for (int i = 0; i < split.length; ++i)
        {
            result.put(header[i], split[i]);
        }
        return result;
    }

    /**
     * Returns the character that was used for separation of the values. The
     * returned character is guaranted to be one of {@link #SEPARATION_CHARS}.
     *
     * @return the character used for separation
     * @since 0.2.0
     */
    public char getSeparationCharacter()
    {
        return separation.charAt(0);
    }

    /**
     * {@inheritDoc}
     *
     * @throws IOException {@inheritDoc }
     * @since 0.2.0 * @see AutoCloseable#close()
     */
    @Override
    public void close() throws IOException
    {
        reader.close();
    }

}
