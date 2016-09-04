package net.bplaced.clayn.cfs.util.ff.csv;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.bplaced.clayn.cfs.SimpleFile;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
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
            Assert.assertNotNull(csv);
            String[] result = csv.getHeader();
            String[] expected = new String[]
            {
                "first", "second", "third", "fourth"
            };
            Assert.assertArrayEquals(expected, result);
        } catch (IOException ex)
        {
            Logger.getLogger(CSVFileTest.class.getName()).log(Level.SEVERE, null,
                    ex);
            Assert.fail("Failed testGetHeader: " + ex.getMessage());
        }
    }

    /**
     * Test of readLine method, of class CSVFile.
     */
    @Test
    public void testReadLine() throws Exception
    {

    }

}
