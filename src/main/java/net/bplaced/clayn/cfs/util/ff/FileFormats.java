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
 * @since 0.2.0 * @version $Revision: 334 $
 */
public class FileFormats
{

    /**
     * The constant format for {@link CSVFile csv} files.
     *
     * @since 0.2.0     */
    public static final TryingFunction<SimpleFile, CSVFile> CSV = CSVFile::new;
    
    public static final TryingFunction<SimpleFile,TemporaryFile> TMP=TemporaryFile::new;
}
