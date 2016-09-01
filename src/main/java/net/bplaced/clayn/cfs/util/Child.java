package net.bplaced.clayn.cfs.util;

/**
 * Implementations of this class can have a parent that manages this class. A
 * child allows to get it's parent. It's not necessary that a parent exists
 *
 * @author Clayn
 * @param <P> the type for the parent of this class
 * @since 0.1
 * @version $Revision: 318 $
 */
public interface Child<P>
{

    /**
     * Returns the parent file from this instance. The type depends on the
     * implementation. Implementing classes should assure that you can't access
     * an invalid value using this method e.g. get the parent of the root from a
     * filesystem. The parent may be {@code null}
     *
     * @return the parent of this instance
     * @since 0.1
     */
    public P getParent();
}
