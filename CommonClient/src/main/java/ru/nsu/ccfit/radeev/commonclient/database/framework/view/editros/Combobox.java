package ru.nsu.ccfit.radeev.commonclient.database.framework.view.editros;

import ru.nsu.ccfit.radeev.commonclient.database.framework.view.Editor;

import javax.swing.*;

public class Combobox implements Editor {

    private final JComboBox combobox;


    public Combobox(JComboBox combobox) {
        assert (null != combobox);
        this.combobox = combobox;

    }

    public Combobox(String... strings) {
        combobox = new JComboBox();
        for (String string : strings) {
            combobox.addItem(string);
        }

    }


    public JComponent getComponent() {
        return combobox;
    }

    public Object getValue() {
        return combobox.getSelectedItem();
    }

    public void resetValue() {

    }
}
