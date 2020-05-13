package ru.nsu.ccfit.radeev.commonclient.database.framework.tables.abstracts;

import ru.nsu.ccfit.radeev.commonclient.database.framework.tables.interfaces.tables.TableRenders;

import javax.swing.table.TableCellRenderer;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractTableRenders implements TableRenders {

    private final Map<Integer, TableCellRenderer> renderMap = new HashMap<>();

    protected void addRender(int column, TableCellRenderer render) {
        assert (column >= 0);
        renderMap.put(column, render);
    }

    public TableCellRenderer getRender(int column) {
        TableCellRenderer render = renderMap.get(column);
        return render;
    }
}