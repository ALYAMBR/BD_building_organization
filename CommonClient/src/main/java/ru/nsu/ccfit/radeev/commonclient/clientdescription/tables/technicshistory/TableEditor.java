package ru.nsu.ccfit.radeev.commonclient.clientdescription.tables.technicshistory;

import ru.nsu.ccfit.radeev.commonclient.database.framework.tables.abstracts.AbstractTableEditors;
import ru.nsu.ccfit.radeev.commonclient.view.utils.dateworks.DateEditor;

public class TableEditor extends AbstractTableEditors {
    public TableEditor() {
        super();
        addEditor(2, new DateEditor());
        addEditor(3, new DateEditor());
    }
}