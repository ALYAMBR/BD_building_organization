package ru.nsu.ccfit.radeev.commonclient.database.framework.tables.abstracts;

import ru.nsu.ccfit.radeev.commonclient.database.framework.tables.interfaces.tables.TableNewRecordEditors;
import ru.nsu.ccfit.radeev.commonclient.database.framework.view.Editor;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractTableNewRecordEditors implements TableNewRecordEditors {
    private Map<Integer, Editor> editorMap = new HashMap<>();

    public AbstractTableNewRecordEditors() {

    }

    protected void addEditor(int column, Editor editor) {
        assert (column >= 0);
        editorMap.put(column, editor);
    }

    public Editor getEditor(int column) {
        Editor editor = editorMap.get(column);
        return editor;
    }
}