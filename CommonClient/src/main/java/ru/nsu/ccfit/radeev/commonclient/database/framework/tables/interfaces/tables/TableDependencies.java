package ru.nsu.ccfit.radeev.commonclient.database.framework.tables.interfaces.tables;

import java.util.Map;

public interface TableDependencies {
    Map<Integer, String> getLookupValues(int columnIndex);
    boolean isForeignKey(int columnIndex);
}
