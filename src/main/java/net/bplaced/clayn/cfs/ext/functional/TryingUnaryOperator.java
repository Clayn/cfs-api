package net.bplaced.clayn.cfs.ext.functional;

import java.util.function.UnaryOperator;

/**
 *
 * @author Clayn
 * @param <T>
 * @ext
 */
public interface TryingUnaryOperator<T> extends UnaryOperator<T>
{

    public T tryApply(T val) throws Exception;

    @Override
    public default T apply(T t)
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
