package ru.nsu.ccfit.radeev.commonclient.clientdescription.tables.technicshistory;

import ru.nsu.ccfit.radeev.commonclient.database.framework.tables.abstracts.AbstractTableDependencies;

import java.sql.SQLException;

public class Dependencies extends AbstractTableDependencies {

    public Dependencies() throws SQLException{
        super();
        addLookupValues(1, "select id, name from technics");
        addLookupValues(4, "select id, name from building_object");
        addLookupValues(5, "select id, name from district");
    }
}
