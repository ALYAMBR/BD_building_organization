package ru.nsu.ccfit.radeev.commonclient.database.framework.view;

import javax.swing.*;

public interface Editor {
    JComponent getComponent();
    Object getValue();
    void resetValue();
}
