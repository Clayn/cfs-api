package net.bplaced.clayn.cfs.help;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;
import net.bplaced.clayn.cfs.Directory;
import net.bplaced.clayn.cfs.SimpleFile;
import static org.mockito.Mockito.*;

/**
 *
 * @author Clayn <clayn_osmato@gmx.de>
 */
public class TestHelper
{
    public static SimpleFile mockFile(String name,String content, Charset cs) throws IOException
    {
        SimpleFile file=mock(SimpleFile.class);
        when(file.exists()).thenReturn(true);
        when(file.getName()).thenReturn(name);
        when(file.getCharset()).thenReturn(cs);
        when(file.openRead()).thenReturn(new ByteArrayInputStream(content.getBytes(cs)));
        return file;
    }
    
    public static Directory mockDirectory(String name,boolean root,Directory ...subs) throws IOException
    {
        Directory dir=mock(Directory.class);
        when(dir.exists()).thenReturn(true);
        when(dir.isRoot()).thenReturn(root);
        when(dir.getName()).thenReturn(name);
        for(Directory sub:subs)
        {
            when(sub.getParent()).thenReturn(dir);
            when(dir.changeDirectory(sub.getName())).thenReturn(sub);
        }
        when(dir.listDirectories()).thenReturn(Arrays.asList(subs));
        return dir;
    }
    
    public static void mockFilesToDir(Directory dir, SimpleFile ...files) throws IOException
    {
        String parent=dir.toString();
        when(dir.listFiles()).thenReturn(Arrays.asList(files));
        for(SimpleFile file:files)
        {
            when(file.getParent()).thenReturn(dir);
            String name=file.getName();
            when(file.toString()).thenReturn(parent+name);
        }
    }

    public static SimpleFile mockFile(String name, String content) throws IOException
    {
        return mockFile(name, content, Charset.defaultCharset());
    }
}
