/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.bplaced.clayn.ccc.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import net.bplaced.clayn.cfs.Directory;
import net.bplaced.clayn.cfs.SimpleFile;
import net.bplaced.clayn.cfs.util.IOUtils;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Clayn
 */
public class IOUtilsTest
{

    public IOUtilsTest()
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

    private static class TestSimpleFile implements SimpleFile
    {

        private byte[] data;

        public void setData(byte[] data)
        {
            this.data = data;
        }

        public byte[] getData()
        {
            return data;
        }

        @Override
        public boolean exists()
        {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public void create() throws IOException
        {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public void delete() throws IOException
        {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public InputStream openRead() throws IOException
        {
            return new ByteArrayInputStream(data);
        }

        @Override
        public OutputStream openWrite() throws IOException
        {
            return new ByteArrayOutputStream()
            {
                @Override
                public void close() throws IOException
                {
                    data = toByteArray();
                    super.close();
                }

            };
        }

        @Override
        public String getName()
        {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public long getSize() throws IOException
        {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public Directory getParent()
        {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public Charset getCharset()
        {
            throw new UnsupportedOperationException("Not supported yet.");
        }

    }

    /**
     * Test of cleanPath method, of class IOUtils.
     */
    @Test
    public void testCleanPath()
    {
        String path = "/some/path";
        String result = "some/path";
        assertEquals(result, IOUtils.cleanPath(path));

        path = "some/path";
        result = "some/path";
        assertEquals(result, IOUtils.cleanPath(path));

        path = "some/path/";
        result = "some/path";
        assertEquals(result, IOUtils.cleanPath(path));

        path = "some///path";
        result = "some/path";
        assertEquals(result, IOUtils.cleanPath(path));

        path = "some/path///";
        result = "some/path";
        assertEquals(result, IOUtils.cleanPath(path));

        path = "some///path//";
        result = "some/path";
        assertEquals(result, IOUtils.cleanPath(path));

        path = "some///path/";
        result = "some/path";
        assertEquals(result, IOUtils.cleanPath(path));

        path = "///some/path";
        result = "some/path";
        assertEquals(result, IOUtils.cleanPath(path));
    }

    @Test
    public void testCopy_In_Out() throws IOException
    {
        String value = "Hello World this is a simple test for the copy of bytes";
        String expectedResult = value;
        byte[] valueBuffer = value.getBytes();
        byte[] excpectedBuffer = expectedResult.getBytes();
        String result;
        byte[] resultBuffer;

        try (ByteArrayInputStream bin = new ByteArrayInputStream(valueBuffer);
                ByteArrayOutputStream bout = new ByteArrayOutputStream(
                        valueBuffer.length))
        {
            IOUtils.copy(bin, bout);
            resultBuffer = bout.toByteArray();
        }

        result = new String(resultBuffer);
        assertArrayEquals(excpectedBuffer, resultBuffer);
        assertEquals(expectedResult, result);
    }

    @Test
    public void testReadAllBytes() throws IOException
    {
        byte[] value = "Hello World".getBytes();
        byte[] expcected = "Hello World".getBytes();
        byte[] result;

        TestSimpleFile file = new TestSimpleFile();
        file.setData(value);
        result = IOUtils.readAllBytes(file);

        assertEquals(expcected.length, result.length);
        assertArrayEquals(expcected, result);
    }

    @Test
    public void testCopyFiles() throws IOException
    {
        byte[] value = "Hello World".getBytes();
        byte[] expcected = "Hello World".getBytes();
        byte[] result;

        TestSimpleFile fileIn = new TestSimpleFile();
        TestSimpleFile fileOut = new TestSimpleFile();
        fileIn.setData(value);

        IOUtils.copy(fileIn, fileOut);

        result = fileOut.getData();
        assertEquals(expcected.length, result.length);
        assertArrayEquals(expcected, result);
    }
}
