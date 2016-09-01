package net.bplaced.clayn.cfs;

/**
 * The filesettings define several configurations that may be accessed by
 * operations of a filesystem. These may expand during the development. To make
 * sure these changes won't break your code rely on {@link SimpleFileSettings}.
 * However changes may use default implementations an therefor stay compatible.
 *
 * @author Clayn
 * @since 0.1
 * @version $Revision: 318 $
 */
public interface FileSettings
{

    /**
     * Sets if the file should be created when you access (i.e. open) it or not.
     * If set to {@code true} these operations shouldn't fail if the file
     * doesn't exist.
     *
     * @param coa the flag if the file should be created on access
     */
    public void setCreateOnAccess(boolean coa);

    /**
     * Returns if the file will be created when you access it or not.
     *
     * @return {@code true} if the file will be created when you try to access
     * it {@code false} otherwise.
     */
    public boolean getCreateOnAccess();
}
