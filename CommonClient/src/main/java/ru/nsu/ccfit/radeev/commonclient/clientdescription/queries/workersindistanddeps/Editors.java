package ru.nsu.ccfit.radeev.commonclient.clientdescription.queries.workersindistanddeps;


import ru.nsu.ccfit.radeev.commonclient.database.framework.tables.abstracts.AbstractTableNewRecordEditors;
import ru.nsu.ccfit.radeev.commonclient.database.framework.view.editros.LookupCombobox;

public class Editors extends AbstractTableNewRecordEditors {

    public Editors() {
        addEditor(0, new LookupCombobox("select id, name from department"));
        addEditor(1, new LookupCombobox("select id, name from district"));
    }
}
