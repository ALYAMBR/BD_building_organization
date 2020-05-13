package ru.nsu.ccfit.radeev.commonclient.view.utils.dateworks;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateEditor extends DefaultCellEditor {
    DateFormat format;

    Date value;

    public DateEditor(DateFormat format) {
        super(new JTextField());
        assert(format != null);
        getComponent().setName("Table.editor");
        this.format = format;
    }

    public DateEditor() {
        super(new JTextField());
        getComponent().setName("Table.editor");
        this.format = new SimpleDateFormat("dd-MM-yyyy");
    }

    public boolean stopCellEditing() {
        String s = (String) super.getCellEditorValue();
        if ("".equals(s)) {
            super.stopCellEditing();
        }
        try {
            value = format.parse(s);
        } catch (ParseException e) {
            ((JComponent) getComponent()).setBorder(new LineBorder(Color.red));
            return false;
        }
        return super.stopCellEditing();
    }

    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        this.value = null;
        if (value instanceof Date) {
            value = format.format((Date) value);
        }
        ((JComponent) getComponent()).setBorder(new LineBorder(Color.black));
        return super.getTableCellEditorComponent(table, value, isSelected, row, column);
    }

    public Object getCellEditorValue() {
        return value;
    }
}

