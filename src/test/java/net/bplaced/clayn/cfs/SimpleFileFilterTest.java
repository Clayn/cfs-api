/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.bplaced.clayn.cfs;

import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.mockito.Mockito.*;

/**
 *
 * @author Clayn <clayn_osmato@gmx.de>
 */
public class SimpleFileFilterTest
{

    public SimpleFileFilterTest()
    {
    }

    @BeforeClass
    public static void setUpClass()
    {
    }

    @AfterClass
    public static void tearDownClass()
    {
    }

    @Before
    public void setUp()
    {
    }

    @After
    public void tearDown()
    {
    }

    /**
     * Test of extensionFilter method, of class SimpleFileFilter.
     */
    @Test
    public void testExtensionFilter()
    {
        System.out.println("extensionFilter");

        String[] values = new String[]
        {
            "Test.txt", "Test.abc.txt", "Test.txt.abc", "Testtxt", "Test.tXt"
        };
        boolean results[] = new boolean[]
        {
            true, true, false, false, false
        };
        SimpleFileFilter filter = SimpleFileFilter.extensionFilter(".txt");
        SimpleFile testFile = mock(SimpleFile.class);
        when(testFile.getName()).thenReturn(null, values);
        testFile.getName();
        assertEquals(results.length, values.length);
        for (int i = 0; i < values.length; ++i)
        {
            assertEquals(results[i], filter.accept(testFile));
        }
        verify(testFile, times(values.length + 1)).getName();
    }

    /**
     * Test of nameFilter method, of class SimpleFileFilter.
     */
    @Test
    public void testNameFilter()
    {
        System.out.println("nameFilter");
        SimpleFileFilter filter = SimpleFileFilter.nameFilter("Foo");
        String[] values = new String[]
        {
            "Test.txt","TestFoo.txt","Foo.txt","Testfoo.txt","FoO.txt","Test.foo","Test.Foo"
        };
        boolean results[] = new boolean[]
        {
            false,true,true,false,false,false,false
        };
        SimpleFile file = mock(SimpleFile.class);
        when(file.getName()).thenReturn(null, values);
        file.getName();
        assertEquals(results.length, values.length);
        for (int i = 0; i < results.length; ++i)
        {
            assertEquals(results[i], filter.accept(file));
        }
        verify(file, times(values.length + 1)).getName();
    }

}
