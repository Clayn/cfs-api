package net.bplaced.clayn.cfs.ext.functional;

import java.util.function.Supplier;

/**
 * An interface similar to {@link Supplier} to allow methods that may throw an
 * exception to be referenced.
 *
 * @author Clayn <clayn_osmato@gmx.de>
 * @param <T> the type of supplied object
 * @ext
 * @since 0.1.0
 */
@FunctionalInterface
public interface TryingSupplier<T> extends Supplier<T>
{

    /**
     * Tries to get the result. This may throw an exception.
     * @return a result if it doesn't fail
     * @throws Exception if the supplier fails
     * @see #get() 
     * @since 0.1.0
     */
    public T tryGet() throws Exception;

    @Override
    public default T get()
    {
        try
        {
            return tryGet();
        } catch (Exception ex)
        {
            throw new RuntimeException(ex);
        }
    }

}
