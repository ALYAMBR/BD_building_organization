package ru.nsu.ccfit.radeev.commonclient.database.framework.tables.interfaces.tables;

import ru.nsu.ccfit.radeev.commonclient.database.framework.view.Editor;

public interface TableNewRecordEditors {
    Editor getEditor(int column);
}
