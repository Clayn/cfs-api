package net.bplaced.clayn.cfs.util.ff;

import java.util.function.Function;
import net.bplaced.clayn.cfs.SimpleFile;
import net.bplaced.clayn.cfs.util.ff.csv.CSVFile;

/**
 * Collection of all file formats provided by the CFS project. Additional
 * formats are possible. This collection may be extended in further releases.
 *
 * @author Clayn
 * @since 0.2
 * @version $Revision: 334 $
 */
public class FileFormats
{

    /**
     * The constant format for {@link CSVFile csv} files.
     *
     * @since 0.2
     */
    public static final Function<SimpleFile, CSVFile> CSV = CSVFile::new;
}
