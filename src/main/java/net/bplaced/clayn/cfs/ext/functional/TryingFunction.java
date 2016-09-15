package net.bplaced.clayn.cfs.ext.functional;

import java.util.function.Function;

/**
 *
 * @author Clayn <clayn_osmato@gmx.de>
 */
public interface TryingFunction<T, R> extends Function<T, R>
{

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
