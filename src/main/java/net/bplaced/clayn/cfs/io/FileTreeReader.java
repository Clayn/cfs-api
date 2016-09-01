package net.bplaced.clayn.cfs.io;

import java.io.IOException;
import net.bplaced.clayn.cfs.util.FileTree;

/**
 *
 * @author Clayn
 * @since 0.1
 * @version $Revision: 318 $
 * @development
 */
public abstract class FileTreeReader implements AutoCloseable
{

    public abstract FileTree read(char separator) throws IOException;

}
