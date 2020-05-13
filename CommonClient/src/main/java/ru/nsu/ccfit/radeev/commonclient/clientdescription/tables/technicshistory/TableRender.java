package ru.nsu.ccfit.radeev.commonclient.clientdescription.tables.technicshistory;

import ru.nsu.ccfit.radeev.commonclient.database.framework.tables.abstracts.AbstractTableRenders;
import ru.nsu.ccfit.radeev.commonclient.view.utils.dateworks.DateRender;

public class TableRender extends AbstractTableRenders {

    public TableRender() {
        super();
        addRender(2, new DateRender());
        addRender(3, new DateRender());
    }
}
