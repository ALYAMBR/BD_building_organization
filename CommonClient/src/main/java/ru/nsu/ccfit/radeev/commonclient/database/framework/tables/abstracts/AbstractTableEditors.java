package ru.nsu.ccfit.radeev.commonclient.database.framework.tables.abstracts;

import ru.nsu.ccfit.radeev.commonclient.database.framework.tables.interfaces.tables.TableEditors;

import javax.swing.table.TableCellEditor;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractTableEditors implements TableEditors {

    private final Map<Integer, TableCellEditor> editorMap = new HashMap<>();

    protected void addEditor(int column, TableCellEditor editor) {
        assert (column >= 0);
        editorMap.put(column, editor);
    }

    public TableCellEditor getEditor(int column) {
        TableCellEditor editor = editorMap.get(column);
        return editor;
    }
}
