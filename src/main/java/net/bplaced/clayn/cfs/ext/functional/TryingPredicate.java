package net.bplaced.clayn.cfs.ext.functional;

import java.util.function.Predicate;

/**
 * An interface similar to {@link Predicate} to allow methods that may throw an
 * exception to be referenced.
 *
 * @author Clayn
 * @param <T> the type of the objects to check
 * @ext
 * @since 0.1.0
 */
@FunctionalInterface
public interface TryingPredicate<T> extends Predicate<T>
{

    /**
     * Tries to evaluate this predicate on the given argument. This may throw an 
     * exception.
     *
     * @param val the input argument
     * @return {@code true} if the input argument matches the predicate,
     * otherwise {@code false}
     * @throws Exception if the evaluation fails.
     * @see #test(java.lang.Object)
     * @since 0.1.0
     */
    public boolean tryTest(T val) throws Exception;

    @Override
    public default boolean test(T t)
    {
        try
        {
            return tryTest(t);
        } catch (Exception ex)
        {
            throw new RuntimeException(ex);
        }
    }

}
