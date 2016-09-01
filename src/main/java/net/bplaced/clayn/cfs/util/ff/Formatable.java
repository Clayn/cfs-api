package net.bplaced.clayn.cfs.util.ff;

import java.util.function.Function;

/**
 * Classes that implement this interface can be formated using every function
 * that can format this class. Therefore a implementing class can be formated to
 * every type as long as a formater exists.
 *
 * @author Clayn
 * @param <T> the type that should be formated. Normally the class itself
 * @since 0.2
 * @version $Revision: 329 $
 */
public interface Formatable<T>
{

    /**
     * Formats the class using the given function.
     *
     * @param <R> the type the class gets formatted to
     * @param formater the formater to use
     * @return the result of the given formater
     * @since 0.2
     */
    public <R> R as(Function<T, R> formater);
}
