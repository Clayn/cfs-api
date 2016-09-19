package net.bplaced.clayn.cfs.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import javafx.util.Pair;
import net.bplaced.clayn.cfs.CFileSystem;
import net.bplaced.clayn.cfs.Directory;
import net.bplaced.clayn.cfs.SimpleFile;
import net.bplaced.clayn.cfs.help.TestHelper;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import static org.mockito.Mockito.*;

/**
 *
 * @author Clayn <clayn_osmato@gmx.de>
 */
public class IOUtilsTest
{

    private static final List<Pair<String, String>> testPaths = new ArrayList<>();

    @BeforeClass
    public static void setUpClass()
    {
        testPaths.addAll(Arrays.asList(
                new Pair<>("/foo/bah", "foo/bah"),
                new Pair<>("/foo//bah", "foo/bah"),
                new Pair<>("foo/bah/hello world", "foo/bah/hello world")
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

    private void testPair(Pair<String, String> pair)
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
        Charset cs = Charset.defaultCharset();
        SimpleFile from = mock(SimpleFile.class);
        SimpleFile to = mock(SimpleFile.class);

        String data = "Hello World";
        ByteArrayOutputStream bout = new ByteArrayOutputStream(data.length());

        when(from.getCharset()).thenReturn(cs);
        when(to.getCharset()).thenReturn(cs);

        when(from.openRead()).thenReturn(new ByteArrayInputStream(data.getBytes(
                cs)));
        when(to.openWrite()).thenReturn(bout);

        when(from.exists()).thenReturn(true);
        when(to.exists()).thenReturn(true);

        IOUtils.copy(from, to);
        byte[] result = bout.toByteArray();

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
        String data = "Hello World";
        Charset cs = Charset.defaultCharset();
        byte[] expectedresult = data.getBytes(cs);
        InputStream in = new ByteArrayInputStream(expectedresult);
        ByteArrayOutputStream out = new ByteArrayOutputStream(
                expectedresult.length);
        IOUtils.copy(in, out);
        byte[] result = out.toByteArray();

        assertNotNull(result);
        assertEquals(expectedresult.length, result.length);
        assertArrayEquals(expectedresult, result);
        assertEquals(data, new String(result, cs));
    }

    @Test
    public void testBackupToZip() throws IOException
    {
        System.out.println("backup2zip");
        TemporaryFolder folder = new TemporaryFolder();
        folder.create();
        File zip = folder.newFile("Backup.zip");
        CFileSystem cfs = mock(CFileSystem.class);
        Directory root;
        Directory sub1 = TestHelper.mockDirectory("Sub1", false);
        Directory sub2;
        Directory sub3 = TestHelper.mockDirectory("Sub3", false);
        sub2 = TestHelper.mockDirectory("Sub2", false, sub3);
        root = TestHelper.mockDirectory("Root", true, sub1, sub2);
        when(root.toString()).thenReturn("Root/");
        when(sub1.toString()).thenReturn("Root/Sub1/");
        when(sub2.toString()).thenReturn("Root/Sub2/");
        when(sub3.toString()).thenReturn("Root/Sub2/Sub3");
        TestHelper.mockFilesToDir(sub1, TestHelper.mockFile("File1.txt",
                "Hello World"));
        TestHelper.mockFilesToDir(sub2, TestHelper.mockFile("File2.txt",
                "World Hello"), TestHelper.mockFile("File2_2.txt", "Foo"));
        TestHelper.mockFilesToDir(root, TestHelper.mockFile("File3.txt",
                "Roooot"));
        when(cfs.getRoot()).thenReturn(root);
        assertBackUpFileSystem(cfs);
        IOUtils.backUpToZip(cfs, zip);
        int fileC = 0;
        int dirC = 0;
        try (ZipFile zipFile = new ZipFile(zip))
        {
            assertEquals(7, zipFile.size());
            Enumeration<? extends ZipEntry> enume = zipFile.entries();
            while (enume.hasMoreElements())
            {
                ZipEntry entry = enume.nextElement();
                String name = entry.getName();
                if (name.contains("/"))
                {
                    if (name.endsWith("/"))
                    {
                        name = name.substring(0, name.length() - 1);
                    }
                    name = name.substring(name.lastIndexOf("/") + 1,
                            name.length());
                }
                if (entry.isDirectory())
                {
                    dirC++;
                    assertTrue(name.startsWith("Sub"));

                    System.out.println(name);
                } else
                {
                    fileC++;
                    assertTrue(name.startsWith("File"));
                    assertTrue(name.endsWith(".txt"));
                }
            }
            assertEquals(4, fileC);
            assertEquals(3, dirC);
            assertTrue(zipFile.stream().map(ZipEntry::getName).anyMatch(
                    "Root/Sub2/"::equals));
            ZipEntry file1 = zipFile.getEntry("Root/Sub1/File1.txt");
            ZipEntry file2 = zipFile.getEntry("Root/Sub2/File2.txt");
            ZipEntry file3 = zipFile.getEntry("Root/Sub2/File2_2.txt");
            ZipEntry file4 = zipFile.getEntry("Root/File3.txt");

            assertTrue(Stream.of(file1, file2, file3, file4).allMatch(
                    Objects::nonNull));

            assertContent(zipFile, file1, "Hello World");
            assertContent(zipFile, file2, "World Hello");
            assertContent(zipFile, file3, "Foo");
            assertContent(zipFile, file4, "Roooot");
        }
        zip = folder.newFile("Test2.zip");
        SimpleFile sf = mock(SimpleFile.class);
        when(sf.getName()).thenReturn("NoFile.txt");
        when(sf.exists()).thenReturn(false);
        TestHelper.mockFilesToDir(root, TestHelper.mockFile("File3.txt",
                "Roooot"), sf);
        IOUtils.backUpToZip(cfs, zip);
        try (ZipFile zFile = new ZipFile(zip))
        {
            assertEquals(7, zFile.size());
        }
    }

    private void assertContent(ZipFile file, ZipEntry entry, String content) throws IOException
    {
        try (InputStream in = file.getInputStream(entry); ByteArrayOutputStream bout = new ByteArrayOutputStream(
                (int) entry.getSize()))
        {
            IOUtils.copy(in, bout);
            String read = new String(bout.toByteArray());
            assertEquals(content, read);
        }
    }

    private void assertBackUpFileSystem(CFileSystem cfs) throws IOException
    {
        assertEquals("Root", cfs.getRoot().getName());
        assertEquals(2, cfs.getRoot().listDirectories().size());
        assertEquals(1, cfs.getRoot().listFiles().size());
        assertEquals(1,
                cfs.getRoot().changeDirectory("Sub2").listDirectories().size());
        assertEquals(0,
                cfs.getRoot().changeDirectory("Sub1").listDirectories().size());
        assertEquals(2, cfs.getRoot().changeDirectory("Sub2").listFiles().size());
    }
    
    @Test
    public void testMove() throws IOException
    {
        SimpleFile from=mock(SimpleFile.class);
        SimpleFile to=mock(SimpleFile.class);
        
        when(from.exists()).thenReturn(true);
        when(to.exists()).thenReturn(true);
        when(from.openRead()).thenReturn(new ByteArrayInputStream(new byte[128]));
        when(to.openWrite()).thenReturn(new ByteArrayOutputStream());
        
        IOUtils.move(from, to);
        
        verify(to,times(1)).createSafe();
        verify(from,times(1)).delete();
        verify(from,times(1)).openRead();
        verify(to,times(1)).openWrite();
        
        
    }
}
