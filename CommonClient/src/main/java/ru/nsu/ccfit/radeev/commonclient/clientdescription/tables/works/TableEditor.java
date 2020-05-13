package ru.nsu.ccfit.radeev.commonclient.clientdescription.tables.works;

import ru.nsu.ccfit.radeev.commonclient.database.framework.tables.abstracts.AbstractTableEditors;
import ru.nsu.ccfit.radeev.commonclient.view.utils.dateworks.DateEditor;

public class TableEditor extends AbstractTableEditors {
    public TableEditor() {
        super();
        addEditor(5, new DateEditor());
        addEditor(6, new DateEditor());
        addEditor(7, new DateEditor());
        addEditor(8, new DateEditor());
    }
}