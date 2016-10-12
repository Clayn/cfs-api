package net.bplaced.clayn.cfs.ext.functional;

import java.util.function.Function;

/**
 * An interface similar to {@link Function} to allow methods that may throw an
 * exception to be referenced.
 *
 * @author Clayn <clayn_osmato@gmx.de>
 * @param <T> the type of the parameter for the function
 * @param <R> the type of the result of the function
 * @ext
 * @since 0.1.0
 */
@FunctionalInterface
public interface TryingFunction<T, R> extends Function<T, R>
{

    /**
     * Tries to apply this function to the given parameter. This may throw a
     * checked exception.
     *
     * @param t the function argument
     * @return the function result
     * @throws Exception when the function fails.
     * @see #apply(java.lang.Object)
     * @since 0.1.0
     */
    public R tryApply(T t) throws Exception;

    @Override
    public default R apply(T t)
    {
        try
        {
            return tryApply(t);
        } catch (Exception ex)
        {
            throw new RuntimeException(ex);
        }
    }

}
