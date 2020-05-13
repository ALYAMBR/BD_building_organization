package ru.nsu.ccfit.radeev.commonclient.database.framework.tables.interfaces.queries;

import ru.nsu.ccfit.radeev.commonclient.database.framework.tables.interfaces.tables.TableNewRecordEditors;

import java.util.List;

public interface Query {
    String getDescription();
    String getQuery();
    TableNewRecordEditors getEditors();
    List<String> getParamNames();
}
