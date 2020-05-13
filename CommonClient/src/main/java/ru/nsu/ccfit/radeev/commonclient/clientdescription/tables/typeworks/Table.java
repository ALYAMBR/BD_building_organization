package ru.nsu.ccfit.radeev.commonclient.clientdescription.tables.typeworks;

import ru.nsu.ccfit.radeev.commonclient.database.framework.tables.interfaces.tables.TableEditors;
import ru.nsu.ccfit.radeev.commonclient.database.framework.tables.interfaces.tables.TableNewRecordEditors;
import ru.nsu.ccfit.radeev.commonclient.database.framework.tables.interfaces.tables.TableRenders;
import ru.nsu.ccfit.radeev.commonclient.database.framework.tables.interfaces.tables.TableValidators;

import java.sql.SQLException;

public class Table implements ru.nsu.ccfit.radeev.commonclient.database.framework.tables.interfaces.tables.Table {
    private final TableInfo tableInfo;
    private final TableEditors editors;
    private final TableRenders renders;
    private final TableNewRecordEditors newRecordEditors;
    private final TableValidators validators;

    public Table() throws SQLException {
        tableInfo = new TableInfo();
        editors = new TableEditor();
        renders = new TableRender();
        newRecordEditors = new NewRecordsEditors();
        validators = new TableValidator();
    }

    public TableInfo getTableInfo() {
        return tableInfo;
    }

    public TableEditors getEditors() {
        return editors;
    }

    public TableRenders getRenders() {
        return renders;
    }

    public TableNewRecordEditors getNewRecordEditors() {
        return newRecordEditors;
    }

    public TableValidators getValidators() {
        return validators;
    }
}
