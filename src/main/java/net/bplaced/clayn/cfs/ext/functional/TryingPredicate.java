/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.bplaced.clayn.cfs.ext.functional;

import java.util.function.Predicate;

/**
 *
 * @author Clayn
 * @param <T>
 * @ext
 */
public interface TryingPredicate<T> extends Predicate<T>
{
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
