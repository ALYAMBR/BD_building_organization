package ru.nsu.ccfit.radeev.commonclient.database.framework.tables.abstracts;

import ru.nsu.ccfit.radeev.commonclient.database.framework.tables.interfaces.tables.TableInfo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public abstract class AbstractTableInfo implements TableInfo {
    private String tableName = null;
    private final List<String> tableColumns = new ArrayList<>();
    private final List<String> displayColumns = new ArrayList<>();
    private final List<String> newRecordColumns = new ArrayList<>();
    private int primaryKeyColumn;
    private final Set<Integer> requiredColumns = new HashSet<>();

    public AbstractTableInfo() {

    }

    public String getTableName() {
        return tableName;
    }

    public List<String> getTableColumns() {
        return tableColumns;
    }

    public List<String> getDisplayColumns() {
        return displayColumns;
    }

    public Integer getPrimaryKeyColumn() {
        return primaryKeyColumn;
    }

    public List<String> getNewRecordColumns() {
        return newRecordColumns;
    }

    protected void setTableName(String tableName) {
        this.tableName = tableName;
    }

    protected void addTableColumn(String columnName) {
        tableColumns.add(columnName);
    }

    protected void addDisplayColumn(String columnName) {
        displayColumns.add(columnName);
    }

    protected void addNewRecordColumn(String column) {
        newRecordColumns.add(column);
    }

    protected void addPrimaryKeyColumn(int column) {
        primaryKeyColumn = column;
    }

    protected void addRequiredColumn(int column) {
        requiredColumns.add(column);
    }

    public boolean isRequired(int column) {
        return requiredColumns.contains(column);
    }
}

