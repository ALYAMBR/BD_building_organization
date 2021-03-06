package ru.nsu.ccfit.radeev.commonclient.clientdescription.tables.depdistrictconnections;

import ru.nsu.ccfit.radeev.commonclient.database.framework.tables.abstracts.AbstractTableDependencies;

import java.sql.SQLException;

public class Dependencies extends AbstractTableDependencies {

    public Dependencies() throws SQLException{
        super();
        addLookupValues(0, "select id, name from department");
        addLookupValues(1, "select id, name from district");
        addLookupValues(2, "select e.id, concat(concat(NAME_SECOND, ' '), NAME_FIRST) " +
                "from employee e left join slave s on e.id=s.id" +
                " where s.id is null");
    }
}
