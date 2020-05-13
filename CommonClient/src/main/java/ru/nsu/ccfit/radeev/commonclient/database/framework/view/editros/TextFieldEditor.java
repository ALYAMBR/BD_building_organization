package ru.nsu.ccfit.radeev.commonclient.database.framework.view.editros;

import ru.nsu.ccfit.radeev.commonclient.database.framework.view.Editor;

import javax.swing.*;

public class TextFieldEditor implements Editor {
    private final JTextField textField;
    
    public TextFieldEditor() {
        textField = new JTextField("");
    }

    public JComponent getComponent() {
        return textField;
    }

    public Object getValue() {
        String text = textField.getText();
        if (text.equals("")) {
            return null;
        }
        return text;
    }

    public void resetValue() {
        textField.setText("");
    }
}
