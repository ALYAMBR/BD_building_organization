package ru.nsu.ccfit.radeev.commonclient.clientdescription.tables.groupworkers;

import ru.nsu.ccfit.radeev.commonclient.database.framework.tables.abstracts.AbstractTableDependencies;

import java.sql.SQLException;

public class Dependencies extends AbstractTableDependencies {

    public Dependencies() throws SQLException{
        super();
        addLookupValues(1, "select id, name from district");
    }
}
