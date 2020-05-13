package ru.nsu.ccfit.radeev.commonclient.clientdescription.tables.groupworkers;

import ru.nsu.ccfit.radeev.commonclient.database.framework.tables.abstracts.AbstractTableInfo;
import ru.nsu.ccfit.radeev.commonclient.database.framework.tables.interfaces.tables.TableDependencies;

import java.sql.SQLException;

public class TableInfo extends AbstractTableInfo {
    private static final String tableName = "group_workers";
    private static final String[] tableColumns = {"id", "id_district"};
    private static final String[] displayColumns = {"id", "id_district"};
    private static final String[] newRecordColumns = {"id_district"};
    private static final int primaryKey = 0;
    private static final int[] requiredColumns = {1};

    private TableDependencies dependencies;

    public TableInfo() throws SQLException {
        super();
        dependencies = new Dependencies();
        setTableName(tableName);
        for (String tableColumn : tableColumns) {
            addTableColumn(tableColumn);
        }

        for (String displayColumn : displayColumns) {
            addDisplayColumn(displayColumn);
        }

        for (String addRecordColumn : newRecordColumns) {
            addNewRecordColumn(addRecordColumn);
        }

        addPrimaryKeyColumn(primaryKey);

        for (int requiredColumn : requiredColumns) {
            addRequiredColumn(requiredColumn);
        }
    }

    public int displayColumnToTableColumn(int columnIndex) {
        assert (columnIndex >= 0);
        String column = getDisplayColumns().get(columnIndex);
        int tableIndex = getTableColumns().indexOf(column);
        return tableIndex;
    }

    public int newRecordColumnToTableColumn(int columnIndex) {
        assert (columnIndex >= 0);
        String column = getNewRecordColumns().get(columnIndex);
        int tableIndex = getTableColumns().indexOf(column);
        return tableIndex;
    }

    public TableDependencies getDependencies() {
        return dependencies;
    }
}
