package ru.nsu.ccfit.radeev.commonclient.clientdescription.tables.employee;

import ru.nsu.ccfit.radeev.commonclient.database.framework.tables.abstracts.AbstractTableRenders;
import ru.nsu.ccfit.radeev.commonclient.view.utils.dateworks.DateRender;

public class TableRender extends AbstractTableRenders {

    public TableRender() {
        super();
        addRender(6, new DateRender());
    }
}
