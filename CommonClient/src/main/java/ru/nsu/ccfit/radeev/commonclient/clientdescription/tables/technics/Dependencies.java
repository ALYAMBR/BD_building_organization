package ru.nsu.ccfit.radeev.commonclient.clientdescription.tables.technics;

import ru.nsu.ccfit.radeev.commonclient.database.framework.tables.abstracts.AbstractTableDependencies;

import java.sql.SQLException;

public class Dependencies extends AbstractTableDependencies {

    public Dependencies() throws SQLException{
        super();
        addLookupValues(2, "select id, name from department");
        addLookupValues(3, "select id, name from building_object");
        addLookupValues(4, "select id, name from district");
    }
}
