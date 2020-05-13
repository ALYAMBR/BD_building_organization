package ru.nsu.ccfit.radeev.commonclient.database.framework.tables.interfaces.tables;

public interface Table {
    TableInfo getTableInfo();
    TableEditors getEditors();
    TableRenders getRenders();
    TableNewRecordEditors getNewRecordEditors();
    TableValidators getValidators();
}
