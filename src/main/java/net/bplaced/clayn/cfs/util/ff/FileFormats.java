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
package net.bplaced.clayn.cfs.util.ff;

import net.bplaced.clayn.cfs.SimpleFile;
import net.bplaced.clayn.cfs.ext.functional.TryingFunction;
import net.bplaced.clayn.cfs.util.ff.csv.CSVFile;
import net.bplaced.clayn.cfs.util.ff.tmp.TemporaryFile;

/**
 * Collection of all file formats provided by the CFS project. Additional
 * formats are possible. This collection may be extended in further releases.
 *
 * @author Clayn
 * @since 0.2.0
 */
public class FileFormats
{

    /**
     * The constant format for {@link CSVFile csv} files.
     *
     * @since 0.2.0
     */
    public static final TryingFunction<SimpleFile, CSVFile> CSV = CSVFile::new;

    /**
     * The constant format for {@link TemporaryFile temporary} file.
     *
     * @since 0.2.0
     */
    public static final TryingFunction<SimpleFile, TemporaryFile> TMP = TemporaryFile::new;
}
