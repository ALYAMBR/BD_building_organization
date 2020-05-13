package ru.nsu.ccfit.radeev.commonclient.clientdescription.tables.slave;

import ru.nsu.ccfit.radeev.commonclient.database.framework.tables.abstracts.AbstractTableDependencies;

import java.sql.SQLException;

public class Dependencies extends AbstractTableDependencies {

    public Dependencies() throws SQLException{
        super();
        addLookupValues(0, "select e.id, concat(concat(NAME_SECOND, ' '), NAME_FIRST) " +
                "from employee e left join workers w on e.id=w.id where w.id is null");
        addLookupValues(1, "select b.id, concat(concat(concat(emp.NAME_SECOND, ' ['), OBJ.NAME), ']') " +
                "from slave sl inner join brigade b on b.id_boss=sl.id " +
                "inner join employee emp on sl.id = emp.id " +
                "inner join building_object obj on b.id_obj=obj.id");
        addLookupValues(2, "select id, name from type_slave");
    }
}
