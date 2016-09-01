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
