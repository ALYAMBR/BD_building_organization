package ru.nsu.ccfit.radeev.commonclient.database.framework.core.logic;

import com.sun.rowset.CachedRowSetImpl;
import ru.nsu.ccfit.radeev.commonclient.database.DatabaseConnector;
import ru.nsu.ccfit.radeev.commonclient.database.framework.tables.interfaces.tables.TableDependencies;
import ru.nsu.ccfit.radeev.commonclient.database.framework.tables.interfaces.tables.TableInfo;
import ru.nsu.ccfit.radeev.commonclient.view.utils.LookupEntry;

import javax.sql.RowSetMetaData;
import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.spi.SyncProviderException;
import javax.swing.table.AbstractTableModel;
import java.sql.*;
import java.util.List;
import java.util.Map;

public class TableLogic extends AbstractTableModel {
    private final TableInfo tableInfo;
    private CachedRowSet resultSet;

    public TableLogic(TableInfo tableInfo) throws SQLException{
        assert(tableInfo != null);
        this.tableInfo = tableInfo;
        getData();
    }

    private void getData() throws SQLException{
        String getAllDAtaQuery = buildGetDataQuery();
        ResultSet resSet = DatabaseConnector.getInstance().DoSelect(getAllDAtaQuery);
        resultSet = new CachedRowSetImpl();
        resultSet.setTableName(tableInfo.getTableName());
        resultSet.populate(resSet);
    }

    private String buildGetDataQuery(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("SELECT ");

        List<String> columnList = tableInfo.getTableColumns();
        for(String column : columnList){
            stringBuilder.append(column);
            stringBuilder.append(", ");
        }
        stringBuilder.setLength(stringBuilder.length() - 2);
        stringBuilder.append(" FROM ");
        stringBuilder.append(tableInfo.getTableName());

        return stringBuilder.toString();
    }

    public int getRowCount(){
        try{
            resultSet.last();
            int rowCount = resultSet.getRow();
            return rowCount;
        }catch(SQLException e){
            System.out.println("Row count error.");
            e.printStackTrace();
            return 0;
        }
    }

    public int getColumnCount(){
        assert (tableInfo.getDisplayColumns() != null);
        assert(tableInfo.getDisplayColumns().size() > 0);
        return tableInfo.getDisplayColumns().size();
    }

    public String getColumnName(int column){
        return tableInfo.getDisplayColumns().get(column);
    }

    public Object getValueAt(int row, int column){
        int modelColumn = tableInfo.displayColumnToTableColumn(column);
        try{
            TableDependencies dependencies = tableInfo.getDependencies();
            if(dependencies != null){
                if(dependencies.isForeignKey(modelColumn)){ // todo change to just "column"
                    Map<Integer, String> lookupValues = dependencies.getLookupValues(modelColumn);
                    assert (lookupValues != null);

                    resultSet.absolute(row + 1);
                    Object data = resultSet.getObject(modelColumn + 1);
                    if(data == null){
                        return "-";
                    }
                    assert(data instanceof Number);

                    String value = lookupValues.get(((Number)data).intValue());
                    return value == null ? "-" : value;
                }
            }
            resultSet.absolute(row + 1);
            return resultSet.getObject(modelColumn + 1);
        }catch(SQLException e){
            System.out.println("Get value at error.");
            e.printStackTrace();
            return null;
        }
    }


    @Override
    public void setValueAt(Object aValue, int row, int column){
        try{
            int modelColumn = tableInfo.displayColumnToTableColumn(column);
            Object insertData = prepareData(aValue, modelColumn);
            resultSet.absolute(row + 1);
            updateData(insertData, modelColumn + 1);
            resultSet.updateRow();
        }catch(SQLException e){
            System.out.println("Set value at error.");
            e.printStackTrace();
        }
    }

    private void updateData(Object newValue, String column) throws SQLException{
        resultSet.updateObject(column, newValue);
    }

    private void updateData(Object newValue, int column) throws SQLException{
        resultSet.updateObject(column, newValue);
    }

       private Object prepareData(Object aValue, int column) {
        if (aValue == null) {
            return null;
        }
        Object resultObject = aValue;

        TableDependencies tableDependencies = tableInfo.getDependencies();
        if (tableDependencies != null) {
            if (tableDependencies.isForeignKey(column)) {
                assert (aValue instanceof LookupEntry);
                resultObject = ((LookupEntry) aValue).getId();
            }
        }
        if(aValue instanceof Date){
            resultObject = new Timestamp(((Date)aValue).getTime());
        }
        return resultObject;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int column){
        return true;
    }

    public boolean isCellEditable(int rowIndex, String column){
        return true;
    }

    public void saveChanges() throws SyncProviderException{
        Connection connection = DatabaseConnector.getInstance().getConnection();
        resultSet.acceptChanges(connection);
    }

    public void discardChanges() throws SQLException{
        resultSet.restoreOriginal();
    }

    public void deleteRow(int rowIndex) throws SQLException{
        int curRow = resultSet.getRow();
        try{
            resultSet.absolute(rowIndex + 1);
            resultSet.deleteRow();
        }finally{
            resultSet.absolute(curRow);
        }
    }

    public Class getModelColumnClass(int column){
        try{
            RowSetMetaData metaData = (RowSetMetaData) resultSet.getMetaData();
            String className = metaData.getColumnClassName(column + 1);
            Class columnClass = Class.forName(className);
            return columnClass;
        }catch(ClassNotFoundException e){
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("getModelColumnClass error");
            e.printStackTrace();
        }
        return null;
    }

    public void addRecord(Map<Integer, Object> addRecordMap) throws SQLException {
        assert (addRecordMap != null);
        try {
            resultSet.moveToInsertRow();
            // TODO придумать, как сделать по-другому
            Integer primaryKeyColumn = tableInfo.getPrimaryKeyColumn();
            assert (null != primaryKeyColumn);
            resultSet.updateObject(primaryKeyColumn + 1, null);

            for (Map.Entry<Integer, Object> entry : addRecordMap.entrySet()) {
                if (entry.getValue() != null) {
                    Object insertObject = prepareData(entry.getValue(), entry.getKey());
                    updateData(insertObject, entry.getKey() + 1);
                }
            }

            resultSet.insertRow();

        } finally {
            resultSet.moveToCurrentRow();
        }
    }
}
