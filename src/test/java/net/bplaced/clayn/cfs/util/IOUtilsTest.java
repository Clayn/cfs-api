package net.bplaced.clayn.cfs.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javafx.util.Pair;
import net.bplaced.clayn.cfs.SimpleFile;
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
public class IOUtilsTest
{
    private static final List<Pair<String,String>> testPaths=new ArrayList<>();
    @BeforeClass
    public static void setUpClass()
    {
        testPaths.addAll(Arrays.asList(
                new Pair<>("/foo/bah","foo/bah"),
                new Pair<>("/foo//bah","foo/bah"),
                new Pair<>("foo/bah/hello world","foo/bah/hello world")
        ));
    }
    
    @AfterClass
    public static void tearDownClass()
    {
        testPaths.clear();
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
     * Test of cleanPath method, of class IOUtils.
     */
    @Test
    public void testCleanPath()
    {
        System.out.println("cleanPath");
        testPaths.stream().parallel().forEach(this::testPair);
        
    }
    
    private void testPair(Pair<String,String> pair)
    {
        testPath(pair.getKey(), pair.getValue());
    }
    
    private void testPath(String path, String expResult)
    {
        String result = IOUtils.cleanPath(path);
        assertEquals(expResult, result);
    }

    /**
     * Test of copy method, of class IOUtils.
     */
    @Test
    public void testCopy_SimpleFile_SimpleFile() throws Exception
    {
        System.out.println("copy");
        Charset cs=Charset.defaultCharset();
        SimpleFile from = mock(SimpleFile.class);
        SimpleFile to = mock(SimpleFile.class);
        
        String data="Hello World";
        ByteArrayOutputStream bout=new ByteArrayOutputStream(data.length());
        
        when(from.getCharset()).thenReturn(cs);
        when(to.getCharset()).thenReturn(cs);
        
        when(from.openRead()).thenReturn(new ByteArrayInputStream(data.getBytes(
                cs)));
        when(to.openWrite()).thenReturn(bout);
        
        when(from.exists()).thenReturn(true);
        when(to.exists()).thenReturn(true);
        
        IOUtils.copy(from, to);
        byte[] result=bout.toByteArray();
        
        assertNotNull(result);
        assertNotEquals(0, result.length);
        assertArrayEquals(data.getBytes(cs), result);
        assertEquals(data, new String(result, cs));
    }

    /**
     * Test of copy method, of class IOUtils.
     */
    @Test
    public void testCopy_InputStream_OutputStream() throws Exception
    {
        System.out.println("copy");
        String data="Hello World";
        Charset cs=Charset.defaultCharset();
        byte[] expectedresult=data.getBytes(cs);
        InputStream in = new ByteArrayInputStream(expectedresult);
        ByteArrayOutputStream out = new ByteArrayOutputStream(expectedresult.length);
        IOUtils.copy(in, out);
        byte[] result=out.toByteArray();
        
        assertNotNull(result);
        assertEquals(expectedresult.length, result.length);
        assertArrayEquals(expectedresult, result);
        assertEquals(data, new String(result,cs));
    } 
}
