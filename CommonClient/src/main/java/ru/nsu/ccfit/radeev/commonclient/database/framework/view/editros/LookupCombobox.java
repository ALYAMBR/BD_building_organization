package ru.nsu.ccfit.radeev.commonclient.database.framework.view.editros;

import ru.nsu.ccfit.radeev.commonclient.database.DatabaseConnector;
import ru.nsu.ccfit.radeev.commonclient.database.framework.view.Editor;
import ru.nsu.ccfit.radeev.commonclient.view.utils.LookupEntry;

import javax.swing.*;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LookupCombobox implements Editor {
    private final JComboBox combobox;

    public LookupCombobox(String query) {
        assert (query != null);
        combobox = new JComboBox();

        ResultSet lookupResultSet = null;
        try {
            lookupResultSet = DatabaseConnector.getInstance().DoSelect(query);
            while (lookupResultSet.next()) {
                LookupEntry lookupEntry = new LookupEntry(lookupResultSet.getInt(1), lookupResultSet.getString(2));
                combobox.addItem(lookupEntry);
            }
        } catch (SQLException e) {
            System.out.println("Ошибка получения данных в Combobox.");
            e.printStackTrace();
        }

    }

    public JComponent getComponent() {
        return combobox;
    }

    public Object getValue() {
        Object item = combobox.getSelectedItem();
        assert (item instanceof LookupEntry);
        return ((LookupEntry) item).getId();
    }

    public void resetValue() {

    }
}