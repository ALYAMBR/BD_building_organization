package ru.nsu.ccfit.radeev.commonclient.database.framework.tables.abstracts;

import ru.nsu.ccfit.radeev.commonclient.database.DatabaseConnector;
import ru.nsu.ccfit.radeev.commonclient.database.framework.tables.interfaces.tables.TableDependencies;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public abstract class AbstractTableDependencies implements TableDependencies {
    private Set<Integer> foreignKeys;
    private Map<Integer, Map<Integer, String>> lookupMap;

    public AbstractTableDependencies() {
        foreignKeys = new HashSet<>();
        lookupMap = new HashMap<>();
    }
    protected void addLookupValues(int keyColumn, String query) throws SQLException {
        assert (null != query);
        assert (keyColumn >= 0);

        foreignKeys.add(keyColumn);
        Map<Integer, String> lookupValues = new HashMap<>();
        ResultSet lookupResultSet = DatabaseConnector.getInstance().DoSelect(query);
        while (lookupResultSet.next()) {
            lookupValues.put(lookupResultSet.getInt(1), lookupResultSet.getString(2));
        }

        lookupMap.put(keyColumn, lookupValues);
    }

    public boolean isForeignKey(int columnIndex) {
        assert (columnIndex >= 0);

        Integer columnInd = columnIndex;
        return foreignKeys.contains(columnInd);

    }

    public Map<Integer, String> getLookupValues(int column) {
        return lookupMap.get(column);
    }
}
