package ru.nsu.ccfit.radeev.commonclient.database.framework.tables.interfaces.tables;

import javax.swing.table.TableCellEditor;

public interface TableEditors {
    TableCellEditor getEditor(int column);
}
