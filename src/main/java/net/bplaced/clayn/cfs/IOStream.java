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

/**
 * The IOStream can wrapp an Input and Ouputstream. There is no need that both
 * streams are set (Not even one needs to be set). This class is used for
 * operations where you may recieve both streams simultaneously.
 *
 * @author Clayn
 * @since 0.1
 * @version $Revision: 318 $
 */
public interface IOStream
{

    /**
     * Returns the Inputstream from this IOStream if there is one. The stream
     * may be intialized when this method gets called.
     *
     * @return the Inputstream from this IOStream
     * @throws IOException if an I/O Exception occures
     * @since 0.1
     */
    public InputStream getInput() throws IOException;

    /**
     * Returns the Outputstream from this IOStream if there is one. The stream
     * may be intialized when this method gets called.
     *
     * @return the Outputstream from this IOStream
     * @throws IOException if an I/O Exception occures
     * @since 0.1
     */
    public OutputStream getOutput() throws IOException;
}
