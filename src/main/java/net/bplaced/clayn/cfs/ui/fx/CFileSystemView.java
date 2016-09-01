package net.bplaced.clayn.cfs.ui.fx;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javafx.scene.Node;
import javafx.scene.control.MultipleSelectionModel;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.ImageView;
import net.bplaced.clayn.cfs.CFileSystem;
import net.bplaced.clayn.cfs.Directory;
import net.bplaced.clayn.cfs.SimpleFile;

/**
 *
 * @author Clayn
 * @ext
 */
public class CFileSystemView implements Supplier<Node>
{

    public static final class CFileSystemViewItem
    {

        private final Object item;

        private CFileSystemViewItem(Object item)
        {
            this.item = item;
            if (!isDirectory() && !isFile())
            {
                throw new IllegalArgumentException(
                        "given item is neither directory nor file");
            }
        }

        public boolean isDirectory()
        {
            return item instanceof Directory;
        }

        public boolean isFile()
        {
            return item instanceof SimpleFile;
        }

        public Directory toDirectory()
        {
            return (Directory) item;
        }

        public SimpleFile toFile()
        {
            return (SimpleFile) item;
        }

        @Override
        public String toString()
        {
            return isDirectory() ? toDirectory().getName() : toFile().getName();
        }

    }

    private final TreeView<CFileSystemViewItem> tree = new TreeView<>();
    private final CFileSystem fileSystem;
    private Function<Directory, Node> directoryGraphicsFactory;
    private Function<SimpleFile, Node> fileGraphicsFactory;

    private void initBindings()
    {

    }

    public List<CFileSystemViewItem> getSelectedItems()
    {
        return tree.getSelectionModel().getSelectedItems().stream().map(
                TreeItem<CFileSystemViewItem>::getValue).collect(
                        Collectors.toList());
    }

    public CFileSystemViewItem getSelectedItem()
    {
        return tree.getSelectionModel().getSelectedItem().getValue();
    }

    public MultipleSelectionModel<TreeItem<CFileSystemViewItem>> getSelectionModel()
    {
        return tree.getSelectionModel();
    }

    /**
     * Creates a new view for the given filesystem. The file will be represented
     * using a {@link TreeView tree}.
     *
     * @param fileSystem the filesystem to display.
     */
    public CFileSystemView(CFileSystem fileSystem)
    {
        try
        {
            this.fileSystem = fileSystem;
            tree.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
            tree.setRoot(fill(fileSystem.getRoot()));
            tree.setCellFactory(CFileSystemViewCell::new);
            initBindings();
        } catch (IOException ex)
        {
            Logger.getLogger(CFileSystemView.class.getName()).log(Level.SEVERE,
                    null, ex);
            throw new RuntimeException(ex);
        }
    }

    public void setDirectoryGraphicsFactory(
            Function<Directory, Node> directoryGraphicsFactory)
    {
        this.directoryGraphicsFactory = directoryGraphicsFactory;
    }

    /**
     * Sets the factory that creates the icons for files. The factory may create
     * the same image for each file. Note that, if you use the {@link ImageView}
     * class you should create an instance for each file. However you may use
     * different kind of {@link Node nodes}.
     *
     * @param fileGraphicsFactory the function that creates the
     * {@link TreeItem#setGraphic(javafx.scene.Node) icon} for a given file.
     * @see #setDirectoryGraphicsFactory(java.util.function.Function) 
     */
    public void setFileGraphicsFactory(
            Function<SimpleFile, Node> fileGraphicsFactory)
    {
        this.fileGraphicsFactory = fileGraphicsFactory;
    }

    private class CFileSystemViewCell extends TreeCell<CFileSystemViewItem>
    {

        public CFileSystemViewCell(TreeView<CFileSystemViewItem> param)
        {

        }

        @Override
        protected void updateItem(CFileSystemViewItem item, boolean empty)
        {
            super.updateItem(item, empty);
            if (item == null || empty)
            {
                setText(null);
                setGraphic(null);
                return;
            }
            Node gr = null;
            if (item.isDirectory() && directoryGraphicsFactory != null)
            {
                gr = directoryGraphicsFactory.apply(item.toDirectory());
            } else if (item.isFile() && fileGraphicsFactory != null)
            {
                gr = fileGraphicsFactory.apply(item.toFile());
            }
            Optional.ofNullable(gr).ifPresent(this::setGraphic);
            setText(item.isDirectory() ? item.toDirectory().getName() : item.toFile().getName());
        }

    }

    private TreeItem<CFileSystemViewItem> fill(Directory dir)
    {
        try
        {
            TreeItem<CFileSystemViewItem> item = new TreeItem<>(
                    new CFileSystemViewItem(dir));
            Stream.concat(dir.listDirectories().stream().map(this::fill),
                    dir.listFiles().stream()
                    .map(CFileSystemViewItem::new)
                    .map(TreeItem::new))
                    .forEach(item.getChildren()::add);
            return item;
        } catch (IOException ex)
        {
            Logger.getLogger(CFileSystemView.class.getName()).log(Level.SEVERE,
                    null, ex);
            throw new RuntimeException(ex);
        }
    }

    @Override
    public Node get()
    {
        return tree;
    }

}
