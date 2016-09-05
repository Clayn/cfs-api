package net.bplaced.clayn.cfs.ui.fx;

import javafx.beans.property.ReadOnlyBooleanWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

/**
 *
 * @author Clayn
 */
public class CFileSystemSelection
{

    private final ObservableList<CFileSystemView.CFileSystemViewItem> selectedItems = FXCollections.observableArrayList();
    private final ReadOnlyBooleanWrapper itemsSelected = new ReadOnlyBooleanWrapper();

    CFileSystemSelection()
    {
        selectedItems.addListener(
                new ListChangeListener<CFileSystemView.CFileSystemViewItem>()
        {
            @Override
            public void onChanged(
                    ListChangeListener.Change<? extends CFileSystemView.CFileSystemViewItem> c)
            {
                itemsSelected.set(!selectedItems.isEmpty());
            }
        });
    }
}
