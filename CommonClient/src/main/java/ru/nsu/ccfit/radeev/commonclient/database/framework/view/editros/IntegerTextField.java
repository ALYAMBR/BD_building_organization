package ru.nsu.ccfit.radeev.commonclient.database.framework.view.editros;

import ru.nsu.ccfit.radeev.commonclient.database.framework.view.Editor;

import javax.swing.*;
import java.text.NumberFormat;

public class IntegerTextField implements Editor {
    private static final NumberFormat formatter = NumberFormat.getIntegerInstance();
    private final JFormattedTextField formattedTextField;

    public IntegerTextField() {
        formattedTextField = new JFormattedTextField(formatter);
    }

    public JComponent getComponent() {
        return formattedTextField;
    }

    public Object getValue() {
        return formattedTextField.getValue();
    }

    public void resetValue() {
        formattedTextField.setText("");
    }
}

