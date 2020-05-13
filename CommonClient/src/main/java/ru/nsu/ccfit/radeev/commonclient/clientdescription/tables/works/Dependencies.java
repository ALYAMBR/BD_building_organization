package ru.nsu.ccfit.radeev.commonclient.clientdescription.tables.works;

import ru.nsu.ccfit.radeev.commonclient.database.framework.tables.abstracts.AbstractTableDependencies;

import java.sql.SQLException;

public class Dependencies extends AbstractTableDependencies {

    public Dependencies() throws SQLException{
        super();
        addLookupValues(1, "select id, name from type_works");
        addLookupValues(2, "select id, name from building_object");
        addLookupValues(3, "select b.id, concat(concat(concat(emp.NAME_SECOND, ' ['), OBJ.NAME), ']') " +
                "from slave sl inner join brigade b on b.id_boss=sl.id " +
                "inner join employee emp on sl.id = emp.id " +
                "inner join building_object obj on b.id_obj=obj.id");
    }
}
