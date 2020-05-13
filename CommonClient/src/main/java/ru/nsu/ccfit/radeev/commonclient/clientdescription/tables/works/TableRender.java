package ru.nsu.ccfit.radeev.commonclient.clientdescription.tables.works;

import ru.nsu.ccfit.radeev.commonclient.database.framework.tables.abstracts.AbstractTableRenders;
import ru.nsu.ccfit.radeev.commonclient.view.utils.dateworks.DateRender;

public class TableRender extends AbstractTableRenders {

    public TableRender() {
        super();
        addRender(5, new DateRender());
        addRender(6, new DateRender());
        addRender(7, new DateRender());
        addRender(8, new DateRender());
    }
}
