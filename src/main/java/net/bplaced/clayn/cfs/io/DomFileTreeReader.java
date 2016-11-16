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
package net.bplaced.clayn.cfs.io;

import java.io.IOException;
import java.io.InputStream;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import net.bplaced.clayn.cfs.util.FileTree;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author Clayn
 * @since 0.1
 * @version $Revision: 318 $
 * @development
 */
public class DomFileTreeReader extends FileTreeReader
{

    private final InputStream input;
    private final Document document;

    public DomFileTreeReader(InputStream in) throws ParserConfigurationException, SAXException, IOException
    {
        input = in;
        document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(
                in);
    }

    @Override
    public FileTree read(char separator) throws IOException
    {
        FileTree tree = new FileTree();
        return tree;
    }

    private void parseDirectory(char separator, String parent, NodeList nodes,
            FileTree tree)
    {
        for (int i = 0; i < nodes.getLength(); ++i)
        {
            Node n = nodes.item(i);
            if (n.getNodeName().equalsIgnoreCase("file"))
            {
                tree.addFile(
                        parent + separator + n.getAttributes().getNamedItem(
                                "name").getNodeValue());
            }
        }
    }

    @Override
    public void close() throws IOException
    {
        input.close();
    }
}
