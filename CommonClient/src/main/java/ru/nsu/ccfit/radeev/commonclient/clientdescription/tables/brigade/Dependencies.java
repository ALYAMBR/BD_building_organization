package ru.nsu.ccfit.radeev.commonclient.clientdescription.tables.brigade;

import ru.nsu.ccfit.radeev.commonclient.database.framework.tables.abstracts.AbstractTableDependencies;

import java.sql.SQLException;

public class Dependencies extends AbstractTableDependencies {

    public Dependencies() throws SQLException{
        super();

        addLookupValues(1, "select slave.id, concat(concat(NAME_SECOND, ' '), NAME_FIRST) from employee" +
                " inner join slave on employee.id=slave.id");
        addLookupValues(2, "select id, name from building_object");
    }
}
