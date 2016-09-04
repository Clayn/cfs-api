/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.bplaced.clayn.cfs.util;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import net.bplaced.clayn.cfs.Directory;
import net.bplaced.clayn.cfs.SimpleFile;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.mockito.Mockito.*;

/**
 *
 * @author Clayn
 */
public class CFilesTest
{
    
    public CFilesTest()
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
     * Test of readAllLines method, of class CFiles.
     */
    @Test
    public void testReadAllLines() throws Exception
    {
        System.out.println("readAllLines");
        List<String> lines=Arrays.asList("Hello World","Foo","Bah");
        byte[] data="Hello World\nFoo\nBah".getBytes();
        SimpleFile file=mock(SimpleFile.class);
        ByteArrayInputStream bin=new ByteArrayInputStream(data);
        when(file.openRead()).thenReturn(bin);
        when(file.exists()).thenReturn(true);
        when(file.getCharset()).thenReturn(Charset.defaultCharset());
        List<String> readLines=CFiles.readAllLines(file);
        assertFalse(readLines.isEmpty());
        assertEquals(lines.size(), readLines.size());
        assertEquals(lines, readLines);
        for(int i=0;i<lines.size();++i)
        {
            assertEquals(lines.get(i), readLines.get(i));
        }
    }

    /**
     * Test of newBufferedReader method, of class CFiles.
     */
    @Test
    public void testNewBufferedReader() throws Exception
    {
        System.out.println("newBufferedReader");
        String data="Hello World";
        SimpleFile sf = mock(SimpleFile.class);
        when(sf.openRead()).thenReturn(new ByteArrayInputStream(data.getBytes(Charset.defaultCharset())));
        when(sf.getCharset()).thenReturn(Charset.defaultCharset());
        when(sf.exists()).thenReturn(false, true);
        BufferedReader result = CFiles.newBufferedReader(sf);
        
        assertNull(result);
        result=CFiles.newBufferedReader(sf);
        assertNotNull(result);
        assertEquals(data, result.readLine());
        result.close();
    }

    /**
     * Test of readAllBytes method, of class CFiles.
     */
    @Test
    public void testReadAllBytes() throws Exception
    {
        System.out.println("readAllBytes");
        SimpleFile sf = mock(SimpleFile.class);
        Charset cs=Charset.defaultCharset();
        when(sf.getCharset()).thenReturn(cs);
        when(sf.exists()).thenReturn(false, true);
        byte[] expResult = "Hello Darkness my old friend!".getBytes(cs);
        when(sf.openRead()).thenReturn(new ByteArrayInputStream(expResult));
        
        byte[] empty=new byte[]{};
        byte[] result = CFiles.readAllBytes(sf);
        
        assertNotNull(result);
        Assert.assertArrayEquals(empty, result);
        
        result=CFiles.readAllBytes(sf);
        
        assertNotNull(result);
        assertNotEquals(empty, result);
        Assert.assertArrayEquals(expResult, result);
    }
    
    @Test
    public void testGetSize() throws IOException
    {
        Directory dir=mock(Directory.class);
        Directory sub=mock(Directory.class);
        SimpleFile file=mock(SimpleFile.class);
        SimpleFile file2=mock(SimpleFile.class);
        SimpleFile file3=mock(SimpleFile.class);
        
        when(dir.exists()).thenReturn(false, true);
        when(sub.exists()).thenReturn(true);
        when(dir.listDirectories()).thenReturn(Arrays.asList(sub));
        when(dir.listFiles()).thenReturn(Arrays.asList(file));
        when(sub.listDirectories()).thenReturn(Collections.EMPTY_LIST);
        when(sub.listFiles()).thenReturn(Arrays.asList(file2,file3));
        
        when(file.exists()).thenReturn(true);
        when(file.getSize()).thenReturn(1l);
        
        when(file2.exists()).thenReturn(true);
        when(file2.getSize()).thenReturn(1l);
        
        when(file3.exists()).thenReturn(true);
        when(file3.getSize()).thenReturn(1l);
        
        assertEquals(0, CFiles.getSize(dir));
        assertEquals(3, CFiles.getSize(dir));
    }
    
}
