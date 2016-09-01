/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.bplaced.clayn.cfs;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import net.bplaced.clayn.cfs.impl.local.ClaynFileSystem;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

/**
 *
 * @author Clayn
 */
@RunWith(Parameterized.class)
public class CFileSystemTest
{

    @Parameterized.Parameter
    public CFileSystem filesystem;

    public CFileSystemTest()
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

    @Parameterized.Parameters
    public static Collection getFileSystems() throws IOException
    {
        return Arrays.asList(new ClaynFileSystem(new File(".")));
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
     * Test of getSeparatorChar method, of class CFileSystem.
     */
    @Test
    public void testGetSeparatorChar()
    {

    }

    /**
     * Test of getSeparator method, of class CFileSystem.
     */
    @Test
    public void testGetSeparator()
    {

    }

    /**
     * Test of getRoot method, of class CFileSystem.
     */
    @Test
    public void testGetRoot() throws Exception
    {
        System.out.println(filesystem);
    }

    /**
     * Test of getDirectory method, of class CFileSystem.
     */
    @Test
    public void testGetDirectory() throws Exception
    {

    }

    /**
     * Test of isActiveDirectorySupported method, of class CFileSystem.
     */
    @Test
    public void testIsActiveDirectorySupported()
    {

    }

    /**
     * Test of getActiveDirectory method, of class CFileSystem.
     */
    @Test
    public void testGetActiveDirectory() throws Exception
    {

    }

    /**
     * Test of getActiveRoot method, of class CFileSystem.
     */
    @Test
    public void testGetActiveRoot() throws Exception
    {

    }

    /**
     * Test of getFileSettings method, of class CFileSystem.
     */
    @Test
    public void testGetFileSettings()
    {

    }

    /**
     * Test of getFile method, of class CFileSystem.
     */
    @Test
    public void testGetFile() throws Exception
    {

    }

    public class CFileSystemImpl implements CFileSystem
    {

        @Override
        public Directory getRoot() throws IOException
        {
            return null;
        }

        @Override
        public FileSettings getFileSettings()
        {
            return null;
        }
    }

}
