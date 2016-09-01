package net.bplaced.clayn.cfs;

/**
 * Simple implementation for the filesettings. If you don't need something
 * special this class will be just fine.
 *
 * @author Clayn
 * @since 0.1
 * @version $Revision: 329 $
 */
public class SimpleFileSettings implements FileSettings
{

    private boolean createOnAccess;

    /**
     * {@inheritDoc }
     *
     * @return {@inheritDoc }
     * @since 0.1
     */
    @Override
    public boolean getCreateOnAccess()
    {
        return createOnAccess;
    }

    /**
     * {@inheritDoc }
     *
     * @param createOnAccess {@inheritDoc }
     * @since 0.1
     */
    @Override
    public void setCreateOnAccess(boolean createOnAccess)
    {
        this.createOnAccess = createOnAccess;
    }
}
