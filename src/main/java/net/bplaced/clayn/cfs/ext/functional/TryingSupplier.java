/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.bplaced.clayn.cfs.ext.functional;

import java.util.function.Supplier;

/**
 *
 * @author Clayn
 * @param <T>
 * @ext
 */
public interface TryingSupplier<T> extends Supplier<T>
{

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
