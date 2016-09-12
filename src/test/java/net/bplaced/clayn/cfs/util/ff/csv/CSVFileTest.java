package net.bplaced.clayn.cfs.util.ff.csv;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.bplaced.clayn.cfs.SimpleFile;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

/**
 *
 * @author Clayn
 */
public class CSVFileTest
{

    public CSVFileTest()
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
     * Test of getHeader method, of class CSVFile.
     */
    @Test
    public void testGetHeader()
    {
        try
        {
            SimpleFile sf = Mockito.mock(SimpleFile.class);
            Mockito.when(sf.getCharset()).thenReturn(Charset.defaultCharset());
            Mockito.when(sf.openRead()).thenReturn(
                    getClass().getResourceAsStream(
                            "/csv/Header1.csv"));
            CSVFile csv = new CSVFile(sf);
            assertNotNull(csv);
            String[] result = csv.getHeader();
            String[] expected = new String[]
            {
                "first", "second", "third", "fourth"
            };
            assertArrayEquals(expected, result);
        } catch (IOException ex)
        {
            Logger.getLogger(CSVFileTest.class.getName()).log(Level.SEVERE, null,
                    ex);
            fail("Failed testGetHeader: " + ex.getMessage());
        }
    }

    /**
     * Test of readLine method, of class CSVFile.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testReadLine() throws Exception
    {
        SimpleFile sf = Mockito.mock(SimpleFile.class);
        String[] hResult=new String[]{"h1","h2","h3","h4","h5"};
        Mockito.when(sf.getCharset()).thenReturn(Charset.defaultCharset());
        Mockito.when(sf.openRead()).thenReturn(
                getClass().getResourceAsStream(
                        "/csv/Content1.csv"));
        CSVFile csv = new CSVFile(sf);
        String[] header=csv.getHeader();
        assertEquals(hResult.length, header.length);
        assertArrayEquals(hResult, hResult);
        Map<String,String> line=csv.readLine();
        int lineRead=1;
        assertEquals(header.length, line.size());
        for(String h:hResult)
        {
            assertTrue(line.containsKey(h));
        }
        for(int i=0;i<hResult.length;++i)
        {
            int hIndex=i+1;
            assertEquals("v"+lineRead+hIndex, line.get(header[i]));
        }
    }

}
