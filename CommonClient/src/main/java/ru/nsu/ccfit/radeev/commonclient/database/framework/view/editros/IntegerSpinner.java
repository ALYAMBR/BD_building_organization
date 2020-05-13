package ru.nsu.ccfit.radeev.commonclient.database.framework.view.editros;

import ru.nsu.ccfit.radeev.commonclient.database.framework.view.Editor;

import javax.swing.*;

public class IntegerSpinner implements Editor {
    private final JSpinner spinner;
    private final SpinnerModel model;

    public IntegerSpinner() {
        model = new SpinnerNumberModel(0, 0, Integer.MAX_VALUE, 1);
        spinner = new JSpinner(model);
    }

    public JComponent getComponent() {
        return spinner;
    }

    public Object getValue() {
        return model.getValue();
    }

    public void resetValue() {
        model.setValue(0);
    }
}

