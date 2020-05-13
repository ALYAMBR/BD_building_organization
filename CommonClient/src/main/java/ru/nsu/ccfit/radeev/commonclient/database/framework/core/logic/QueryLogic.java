package ru.nsu.ccfit.radeev.commonclient.database.framework.core.logic;

import ru.nsu.ccfit.radeev.commonclient.database.DatabaseConnector;

import javax.swing.table.AbstractTableModel;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Map;
import java.util.Set;

public class QueryLogic extends AbstractTableModel {
    private final String query;
    private ResultSet resultSet = null;

    public QueryLogic(String query) {
        this.query = query;
    }

    public void getData(Map<Integer, Object> params) throws SQLException {
        assert (null != params);
        PreparedStatement prepareStatement = DatabaseConnector.getInstance().getConnection().prepareStatement(query,
                ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_READ_ONLY);

        Set<Integer> paramColumns = params.keySet();
        for (Integer column : paramColumns) {
            prepareStatement.setObject(column + 1, params.get(column));
        }
        resultSet = prepareStatement.executeQuery();
    }

    public int getRowCount() {
        try {
            resultSet.last();
            int rowCount = resultSet.getRow();
            return rowCount;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public String getColumnName(int column) {
        ResultSetMetaData metadata = null;
        try {
            metadata = resultSet.getMetaData();
            String columnName = metadata.getColumnName(column + 1);
            return columnName;
        } catch (SQLException e) {
            return "";
        }

    }

    public int getColumnCount() {
        try {
            ResultSetMetaData metadata = resultSet.getMetaData();
            return metadata.getColumnCount();
        } catch (SQLException e) {
            return 0;
        }
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        try {
            resultSet.absolute(rowIndex + 1);
            return resultSet.getObject(columnIndex + 1);
        } catch (SQLException e) {
            return null;
        }

    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }
}

