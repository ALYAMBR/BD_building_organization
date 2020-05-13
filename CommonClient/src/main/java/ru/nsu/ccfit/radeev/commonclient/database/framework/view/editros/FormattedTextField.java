package ru.nsu.ccfit.radeev.commonclient.database.framework.view.editros;

import ru.nsu.ccfit.radeev.commonclient.database.framework.view.Editor;

import javax.swing.*;
import java.text.SimpleDateFormat;

public class FormattedTextField implements Editor {

    private final JFormattedTextField formattedTextField;

    public FormattedTextField() {
        formattedTextField = new JFormattedTextField();
    }

    public FormattedTextField(SimpleDateFormat timeStampFormat) {
        formattedTextField = new JFormattedTextField(timeStampFormat);
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
