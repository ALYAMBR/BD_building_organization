package ru.nsu.ccfit.radeev.commonclient.clientdescription.tables.estimate;

import ru.nsu.ccfit.radeev.commonclient.database.framework.tables.abstracts.AbstractTableDependencies;

import java.sql.SQLException;

public class Dependencies extends AbstractTableDependencies {

    public Dependencies() throws SQLException{
        super();
        addLookupValues(0, "select w.id, concat(concat(t.name, '. Договор №'), w.id_agreem) " +
                "from works w inner join type_works t on w.id_type = t.id");
    }
}
