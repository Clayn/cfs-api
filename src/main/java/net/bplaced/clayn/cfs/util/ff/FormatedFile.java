package net.bplaced.clayn.cfs.util.ff;

import java.io.IOException;
import net.bplaced.clayn.cfs.SimpleFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Basic class for all classes that may be formated from a {@link SimpleFile}.
 * Implementing classes can use the constructor for formating (see
 * {@link SimpleFile#as(java.util.function.Function)}). Additional this class
 * provides an {@link Logger logger} by using the {@code slf4j} framework.
 *
 * @author Clayn
 * @since 0.2.0
 */
public abstract class FormatedFile
{

    /**
     * A logger provided for subclasses. There is no need to use this logger 
     * over your own one.
     */
    protected final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * Creating a new instance of an formated file from the given
     * {@link SimpleFile file}.
     *
     * @param file the file to format.
     * @throws java.io.IOException if an I/O Exception occures while formating
     * @since 0.2.0
     */
    public FormatedFile(SimpleFile file) throws IOException
    {
    }
}
