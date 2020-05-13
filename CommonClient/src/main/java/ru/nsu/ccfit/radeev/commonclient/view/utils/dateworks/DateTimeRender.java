package ru.nsu.ccfit.radeev.commonclient.view.utils.dateworks;

import javax.swing.table.DefaultTableCellRenderer;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class DateTimeRender extends DefaultTableCellRenderer {
    DateFormat formatter;

    public DateTimeRender() {
        super();
    }

    public void setValue(Object value) {
        if (formatter == null) {
            formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        }
        String text = "";
        if (null != value) {
            text = formatter.format(value);
        }
        setText(text);
    }
}
