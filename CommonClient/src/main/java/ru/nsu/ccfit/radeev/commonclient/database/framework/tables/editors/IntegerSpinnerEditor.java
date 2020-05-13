package ru.nsu.ccfit.radeev.commonclient.database.framework.tables.editors;

import javax.swing.*;
import javax.swing.table.TableCellEditor;
import java.awt.*;

public class IntegerSpinnerEditor extends AbstractCellEditor implements TableCellEditor {

    private JSpinner spinner;


    public IntegerSpinnerEditor() {
        spinner = new JSpinner(new SpinnerNumberModel(0, 0, Integer.MAX_VALUE, 1));
        spinner.setBorder(null);

    }

    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        spinner.setValue(value);
        return spinner;
    }

    public Object getCellEditorValue() {
        return spinner.getValue();
    }

    public boolean stopCellEditing() {
        try {
            spinner.commitEdit();
        } catch (java.text.ParseException exc) {
            JOptionPane.showMessageDialog(null, "Некорректное значение");
            return false;
        }
        return super.stopCellEditing();
    }
}
