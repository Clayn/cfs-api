/*
 * Copyright (C) 2016 Clayn <clayn_osmato@gmx.de>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package net.bplaced.clayn.cfs;

import java.nio.charset.Charset;
import net.bplaced.clayn.cfs.util.SizeUnit;
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
public class LinkTest
{

    public LinkTest()
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
     * Test of exists method, of class Link.
     */
    @Test
    public void testExists()
    {
        System.out.println("exists");
        Link instance = null;
        SimpleFile orig = getFile();
        instance = new Link(orig);

        when(orig.exists()).thenReturn(false, true, false);

        assertFalse(instance.exists());
        assertTrue(instance.exists());
        assertFalse(instance.exists());

        verify(orig, times(3)).exists();
    }

    /**
     * Test of create method, of class Link.
     */
    @Test
    public void testCreate() throws Exception
    {
        System.out.println("create");
        Link instance = null;
        SimpleFile orig = getFile();
        doNothing().when(orig).create();
        instance = new Link(orig);
        instance.create();

        verify(orig, times(1)).create();

    }

    /**
     * Test of createSafe method, of class Link.
     */
    @Test
    public void testCreateSafe() throws Exception
    {
        System.out.println("createSafe");
        Link instance = null;
        SimpleFile orig = getFile();
        doNothing().when(orig).createSafe();
        instance = new Link(orig);
        instance.createSafe();

        verify(orig, times(1)).createSafe();
    }

    /**
     * Test of delete method, of class Link.
     */
    @Test
    public void testDelete() throws Exception
    {
        System.out.println("delete");
        Link instance = null;
        SimpleFile orig = getFile();
        instance = new Link(orig);
        when(orig.exists()).thenReturn(true, false);
        doNothing().when(orig).delete();
        assertTrue(instance.exists());
        instance.delete();
        assertFalse(instance.exists());

        verify(orig, atLeast(1)).delete();
    }

    /**
     * Test of openRead method, of class Link.
     */
    @Test
    public void testOpenRead() throws Exception
    {
        System.out.println("openRead");
        Link instance = null;
        SimpleFile orig = getFile();
        when(orig.openRead()).thenReturn(null);
        instance = new Link(orig);
        instance.openRead();

        verify(orig, times(1)).openRead();
    }

    /**
     * Test of openWrite method, of class Link.
     */
    @Test
    public void testOpenWrite() throws Exception
    {
        System.out.println("openWrite");
        Link instance = null;
        SimpleFile orig = getFile();
        when(orig.openWrite()).thenReturn(null);
        instance = new Link(orig);
        instance.openWrite();

        verify(orig, times(1)).openWrite();
    }

    /**
     * Test of getName method, of class Link.
     */
    @Test
    public void testGetName()
    {
        System.out.println("getName");
        Link instance = null;
        String expResult = "Test.txt";

        SimpleFile orig = getFile();
        instance = new Link(orig);
        when(orig.getName()).thenReturn(expResult);
        assertEquals(expResult, instance.getName());
        verify(orig, atLeast(1)).getName();
    }

    private SimpleFile getFile()
    {
        return mock(SimpleFile.class);
    }

    /**
     * Test of getSize method, of class Link.
     */
    @Test
    public void testGetSize_0args() throws Exception
    {
        System.out.println("getSize");
        Link instance;
        SimpleFile orig = mock(SimpleFile.class);
        instance = new Link(orig);
        long[] results = new long[]
        {
            1l, 2l, 3l, 4l, 5l
        };
        when(orig.getSize()).thenReturn(1l, 2l, 3l, 4l, 5l);
        for (int i = 0; i < results.length; ++i)
        {
            assertEquals(results[i], instance.getSize());
        }
        verify(orig, atLeast(results.length)).getSize();
    }

    /**
     * Test of getSize method, of class Link.
     */
    @Test
    public void testGetSize_SizeUnit() throws Exception
    {
        System.out.println("getSize");
        Link instance = null;
        SimpleFile orig = mock(SimpleFile.class);
        when(orig.getSize(SizeUnit.BYTE)).thenReturn(1000.0);
        when(orig.getSize(SizeUnit.KILO_BYTE)).thenReturn(1.0);
        instance = new Link(orig);

        double expected = 1000.0;
        double result = instance.getSize(SizeUnit.BYTE);
        assertEquals(expected, result, 0.01);
        expected = 1.0;
        result = instance.getSize(SizeUnit.KILO_BYTE);
        assertEquals(expected, result, 0.01);
        verify(orig, atLeast(2)).getSize(any());
    }

    /**
     * Test of getCharset method, of class Link.
     */
    @Test
    public void testGetCharset()
    {
        System.out.println("getCharset");
        Link instance = null;
        SimpleFile orig = mock(SimpleFile.class);
        instance = new Link(orig);
        when(orig.getCharset()).thenReturn(Charset.defaultCharset());

        assertEquals(Charset.defaultCharset(), instance.getCharset());
        verify(orig, atLeast(1)).getCharset();

    }

    /**
     * Test of getParent method, of class Link.
     */
    @Test
    public void testGetParent()
    {
        System.out.println("getParent");
        Link instance = null;
        Directory expResult = mock(Directory.class);
        SimpleFile orig = mock(SimpleFile.class);
        when(orig.getParent()).thenReturn(expResult);
        String dirName = "Dir";
        when(expResult.getName()).thenReturn(dirName);

        instance = new Link(orig);

        Directory parent = instance.getParent();
        assertNotNull(parent);
        assertEquals(dirName, parent.getName());

        verify(orig, atLeast(1)).getParent();
        verify(expResult, atLeast(1)).getName();
    }

    /**
     * Test of toString method, of class Link.
     */
    @Test
    public void testToString()
    {
        System.out.println("toString");
        Link instance = null;
        SimpleFile orig = mock(SimpleFile.class);
        String name = "FileName.txt";
        stub(orig.toString()).toReturn(name);
        instance = new Link(orig);

        assertEquals(name, instance.toString());

    }

    @Test(expected = NullPointerException.class)
    public void testNew()
    {
        System.out.println("new");
        Link instance = null;
        SimpleFile orig = mock(SimpleFile.class);
        instance = new Link(orig);
        Link link = new Link(null);
    }

}
