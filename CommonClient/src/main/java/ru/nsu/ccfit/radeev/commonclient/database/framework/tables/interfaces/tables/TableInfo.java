package ru.nsu.ccfit.radeev.commonclient.database.framework.tables.interfaces.tables;

import java.util.List;

public interface TableInfo {
    String getTableName();
    Integer getPrimaryKeyColumn();
    List<String> getTableColumns();
    List<String> getDisplayColumns();
    List<String> getNewRecordColumns();
    int displayColumnToTableColumn(int columnIndex);
    int newRecordColumnToTableColumn(int columnIndex);
    TableDependencies getDependencies();
    boolean isRequired(int column);
}
