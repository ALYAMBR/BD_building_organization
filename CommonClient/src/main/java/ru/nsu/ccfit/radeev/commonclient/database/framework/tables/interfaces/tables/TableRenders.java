package ru.nsu.ccfit.radeev.commonclient.database.framework.tables.interfaces.tables;

import javax.swing.table.TableCellRenderer;

public interface TableRenders {
    TableCellRenderer getRender(int column);
}
