package net.bplaced.clayn.cfs.util;

import static net.bplaced.clayn.cfs.util.SizeUnit.*;
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
public class SizeUnitTest
{

    public SizeUnitTest()
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
     * Test of values method, of class SizeUnit.
     */
    @Test
    public void testValues()
    {
        System.out.println("values");
        SizeUnit[] expResult = new SizeUnit[]
        {
            BYTE, KILO_BYTE, MEGA_BYTE, GIGA_BYTE, TERRA_BYTE, PETA_BYTE, EXA_BYTE
        };
        SizeUnit[] result = SizeUnit.values();
        assertEquals(expResult.length, result.length);
        assertArrayEquals(expResult, result);

    }

    /**
     * Test of toByte method, of class SizeUnit.
     */
    @Test
    public void testToByte()
    {
        System.out.println("toByte");
        double val = 1000.0;
        SizeUnit instance = BYTE;
        double expResult = val;
        double result = instance.toByte(val);
        assertEquals(expResult, result, 0.1);
        instance = KILO_BYTE;
        expResult *= 1000.0;
        result = instance.toByte(val);
        assertEquals(expResult, result, 0.1);

        instance = MEGA_BYTE;
        expResult *= 1000.0;
        result = instance.toByte(val);
        assertEquals(expResult, result, 0.1);

        instance = GIGA_BYTE;
        expResult *= 1000.0;
        result = instance.toByte(val);
        assertEquals(expResult, result, 0.1);

        instance = TERRA_BYTE;
        expResult *= 1000.0;
        result = instance.toByte(val);
        assertEquals(expResult, result, 0.1);

        instance = PETA_BYTE;
        expResult *= 1000.0;
        result = instance.toByte(val);
        assertEquals(expResult, result, 0.1);
    }

    /**
     * Test of toKiloByte method, of class SizeUnit.
     */
    @Test
    public void testToKiloByte()
    {
        System.out.println("toKiloByte");
        double val = 1000.0;
        SizeUnit instance = BYTE;
        double expResult = 1.0;
        double result = instance.toKiloByte(val);
        assertEquals(expResult, result, 0.1);
        instance = KILO_BYTE;
        expResult = val;
        result = instance.toKiloByte(val);
        assertEquals(expResult, result, 0.1);

        instance = MEGA_BYTE;
        expResult *= 1000.0;
        result = instance.toKiloByte(val);
        assertEquals(expResult, result, 0.1);

        instance = GIGA_BYTE;
        expResult *= 1000.0;
        result = instance.toKiloByte(val);
        assertEquals(expResult, result, 0.1);

        instance = TERRA_BYTE;
        expResult *= 1000.0;
        result = instance.toKiloByte(val);
        assertEquals(expResult, result, 0.1);

        instance = PETA_BYTE;
        expResult *= 1000.0;
        result = instance.toKiloByte(val);
        assertEquals(expResult, result, 0.1);
    }

    /**
     * Test of toMegaByte method, of class SizeUnit.
     */
    @Test
    public void testToMegaByte()
    {
        System.out.println("toMegaByte");
        double val = 1000000.0;
        SizeUnit instance = BYTE;
        double expResult = 1.0;
        double result = instance.toMegaByte(val);
        assertEquals(expResult, result, 0.1);
        instance = KILO_BYTE;
        expResult = 1000.0;
        result = instance.toMegaByte(val);
        assertEquals(expResult, result, 0.1);

        instance = MEGA_BYTE;
        expResult = val;
        result = instance.toMegaByte(val);
        assertEquals(expResult, result, 0.1);

        instance = GIGA_BYTE;
        expResult *= 1000.0;
        result = instance.toMegaByte(val);
        assertEquals(expResult, result, 0.1);

        instance = TERRA_BYTE;
        expResult *= 1000.0;
        result = instance.toMegaByte(val);
        assertEquals(expResult, result, 0.1);

        instance = PETA_BYTE;
        expResult *= 1000.0;
        result = instance.toMegaByte(val);
        assertEquals(expResult, result, 0.1);
    }

    /**
     * Test of toGigaByte method, of class SizeUnit.
     */
    @Test
    public void testToGigaByte()
    {
        System.out.println("toGigaByte");
        double val = 1000000000.0;
        SizeUnit instance = BYTE;
        double expResult = 1.0;
        double result = instance.toGigaByte(val);
        assertEquals(expResult, result, 0.1);
        instance = KILO_BYTE;
        expResult = 1000.0;
        result = instance.toGigaByte(val);
        assertEquals(expResult, result, 0.1);

        instance = MEGA_BYTE;
        expResult = 1000000.0;
        result = instance.toGigaByte(val);
        assertEquals(expResult, result, 0.1);

        instance = GIGA_BYTE;
        expResult = val;
        result = instance.toGigaByte(val);
        assertEquals(expResult, result, 0.1);

        instance = TERRA_BYTE;
        expResult *= 1000.0;
        result = instance.toGigaByte(val);
        assertEquals(expResult, result, 0.1);

        instance = PETA_BYTE;
        expResult *= 1000.0;
        result = instance.toGigaByte(val);
        assertEquals(expResult, result, 0.1);
    }

    /**
     * Test of toTerraByte method, of class SizeUnit.
     */
    @Test
    public void testToTerraByte()
    {
        System.out.println("toTerraByte");
        double val = 1000000000000.0;
        SizeUnit instance = BYTE;
        double expResult = 1.0;
        double result = instance.toTerraByte(val);
        assertEquals(expResult, result, 0.1);
        instance = KILO_BYTE;
        expResult = 1000.0;
        result = instance.toTerraByte(val);
        assertEquals(expResult, result, 0.1);

        instance = MEGA_BYTE;
        expResult = 1000000.0;
        result = instance.toTerraByte(val);
        assertEquals(expResult, result, 0.1);

        instance = GIGA_BYTE;
        expResult = 1000000000.0;
        result = instance.toTerraByte(val);
        assertEquals(expResult, result, 0.1);

        instance = TERRA_BYTE;
        expResult = val;
        result = instance.toTerraByte(val);
        assertEquals(expResult, result, 0.1);

        instance = PETA_BYTE;
        expResult *= 1000.0;
        result = instance.toTerraByte(val);
        assertEquals(expResult, result, 0.1);
    }

    /**
     * Test of toPetaByte method, of class SizeUnit.
     */
    @Test
    public void testToPetaByte()
    {
        System.out.println("toPetaByte");
        double val = 1000000000000000.0;
        SizeUnit instance = BYTE;
        double expResult = 1.0;
        double result = instance.toPetaByte(val);
        assertEquals(expResult, result, 0.1);
        instance = KILO_BYTE;
        expResult = 1000.0;
        result = instance.toPetaByte(val);
        assertEquals(expResult, result, 0.1);

        instance = MEGA_BYTE;
        expResult = 1000000.0;
        result = instance.toPetaByte(val);
        assertEquals(expResult, result, 0.1);

        instance = GIGA_BYTE;
        expResult = 1000000000.0;
        result = instance.toPetaByte(val);
        assertEquals(expResult, result, 0.1);

        instance = TERRA_BYTE;
        expResult = 1000000000000.0;
        result = instance.toPetaByte(val);
        assertEquals(expResult, result, 0.1);

        instance = PETA_BYTE;
        expResult = val;
        result = instance.toPetaByte(val);
        assertEquals(expResult, result, 0.1);
    }

    /**
     * Test of toExaByte method, of class SizeUnit.
     */
    @Test
    public void testToExaByte()
    {
        System.out.println("toExaByte");
        double val = 1000000000000000000.0;
        SizeUnit instance = BYTE;
        double expResult = 1.0;
        double result = instance.toExaByte(val);
        assertEquals(expResult, result, 0.1);
        instance = KILO_BYTE;
        expResult = val / 1000000000000000.0;
        result = instance.toExaByte(val);
        assertEquals(expResult, result, 0.1);

        instance = MEGA_BYTE;
        expResult = val / 1000000000000.0;
        result = instance.toExaByte(val);
        assertEquals(expResult, result, 0.1);

        instance = GIGA_BYTE;
        expResult = val / 1000000000.0;
        result = instance.toExaByte(val);
        assertEquals(expResult, result, 0.1);

        instance = TERRA_BYTE;
        expResult = val / 1000000.0;
        result = instance.toExaByte(val);
        assertEquals(expResult, result, 0.1);

        instance = PETA_BYTE;
        expResult = val / 1000.0;
        result = instance.toExaByte(val);
        assertEquals(expResult, result, 0.1);
        
        instance=EXA_BYTE;
        expResult=val;
        result=instance.toExaByte(val);
        assertEquals(expResult, result,0.1);
    }

    /**
     * Test of convert method, of class SizeUnit.
     */
    @Test
    public void testConvert()
    {
        System.out.println("convert");
        double val = 1.0;
        SizeUnit from = KILO_BYTE;
        SizeUnit instance = BYTE;
        double expResult = 1000.0;
        double result = instance.convert(val, from);
        assertEquals(expResult, result, 0.1);

        expResult = 0.001;
        result = from.convert(val, instance);
        assertEquals(expResult, result, 0.1);

        instance = MEGA_BYTE;
        from = GIGA_BYTE;
        expResult = 1000.0;
        result = instance.convert(val, from);

        assertEquals(expResult, result, 0.1);

        expResult = 0.001;
        result = from.convert(val, instance);
        assertEquals(expResult, result, 0.1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testToReadableString()
    {
        System.out.println("toReadableString");
        long val = 0;
        assertEquals(BYTE.toString(val), SizeUnit.toReadableString(val));
        val = 123l;
        assertEquals(BYTE.toString(val), SizeUnit.toReadableString(val));

        val = 1000l;
        assertEquals("1 kb", SizeUnit.toReadableString(val));

        val = 1023l;
        assertEquals("1 kb 23 b", SizeUnit.toReadableString(val));

        val = 1001001123l;
        assertEquals("1 Gb 1 Mb 1 kb 123 b", SizeUnit.toReadableString(val));

        val = 1201011123l;
        assertEquals("1 Gb 201 Mb 11 kb 123 b", SizeUnit.toReadableString(val));

        SizeUnit.toReadableString(-1);
    }

    @Test
    public void testToString_long()
    {
        System.out.println("toString_long");
        long val = 123;
        SizeUnit instance = BYTE;
        String expResult = "123 b";
        String result = instance.toString(val);
        assertEquals(expResult, result);

        instance = KILO_BYTE;
        expResult = "123 kb";
        result = instance.toString(val);
        assertEquals(expResult, result);

        instance = MEGA_BYTE;
        expResult = "123 Mb";
        result = instance.toString(val);
        assertEquals(expResult, result);

        instance = GIGA_BYTE;
        expResult = "123 Gb";
        result = instance.toString(val);
        assertEquals(expResult, result);

        instance = TERRA_BYTE;
        expResult = "123 Tb";
        result = instance.toString(val);
        assertEquals(expResult, result);

        instance = PETA_BYTE;
        expResult = "123 Pb";
        result = instance.toString(val);
        assertEquals(expResult, result);

        instance = EXA_BYTE;
        expResult = "123 Eb";
        result = instance.toString(val);
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class SizeUnit.
     */
    @Test
    public void testToString_double()
    {
        System.out.println("toString_double");
        double val = 1000.0;
        SizeUnit instance = BYTE;
        String expResult = "1000.0 b";
        String result = instance.toString(val);
        assertEquals(expResult, result);

        instance = KILO_BYTE;
        expResult = "1000.0 kb";
        result = instance.toString(val);
        assertEquals(expResult, result);

        instance = MEGA_BYTE;
        expResult = "1000.0 Mb";
        result = instance.toString(val);
        assertEquals(expResult, result);

        instance = GIGA_BYTE;
        expResult = "1000.0 Gb";
        result = instance.toString(val);
        assertEquals(expResult, result);

        instance = TERRA_BYTE;
        expResult = "1000.0 Tb";
        result = instance.toString(val);
        assertEquals(expResult, result);

        instance = PETA_BYTE;
        expResult = "1000.0 Pb";
        result = instance.toString(val);
        assertEquals(expResult, result);

        instance = EXA_BYTE;
        expResult = "1000.0 Eb";
        result = instance.toString(val);
        assertEquals(expResult, result);

    }

}
