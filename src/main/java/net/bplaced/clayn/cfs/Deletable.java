package net.bplaced.clayn.cfs;

import java.io.IOException;

/**
 * An Interface for all classes that may claim some deletable resources. Classes
 * that implement this interface have to ensure, that after deletion the
 * resources are no longer valid or existing. In other words operations that
 * require the resources to exist, must fail after they were deleted.
 *
 * @author Clayn
 * @since 0.2
 */
public interface Deletable
{

    /**
     * Attempts to delete whatever resources are wrapped by this instance.
     *
     * @throws IOException if an I/O Exception occures during the deletion
     * @since 0.2
     */
    public void delete() throws IOException;
}
