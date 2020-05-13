package ru.nsu.ccfit.radeev.commonclient.database.framework.view.editros;

import ru.nsu.ccfit.radeev.commonclient.database.framework.view.Editor;

import javax.swing.*;

public class CheckBox implements Editor {

    private final JCheckBox checkBox;

    public CheckBox() {
        checkBox = new JCheckBox();
    }

    public JComponent getComponent() {
        return checkBox;
    }

    public Object getValue() {
        if (checkBox.isSelected()) {
            return 1;
        } else {
            return 0;
        }
    }

    public void resetValue() {
        checkBox.setSelected(false);
    }
}
