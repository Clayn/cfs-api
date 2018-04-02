package net.bplaced.clayn.cfs.ext.functional;

import java.util.function.Consumer;

/**
 *
 * @author Clayn <clayn_osmato@gmx.de>
 * @since 0.1
 */
public interface TryingConsumer<T> extends Consumer<T>
{

    public void tryAccept(T val) throws Exception;

    @Override
    public default void accept(T t)
    {
        try
        {
            tryAccept(t);
        } catch (Exception ex)
        {
            throw new RuntimeException(ex);
        }
    }

}
