package ru.nsu.ccfit.radeev.commonclient.view.utils.dateworks;

import javax.swing.table.DefaultTableCellRenderer;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class DateRender extends DefaultTableCellRenderer {
    DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

    public DateRender() {
        super();
    }

    public void setValue(Object value) {
        String text = "";
        if (null != value) {
            try {
                text = formatter.format(value);
            } catch (IllegalArgumentException e) {
                System.out.println("Bad argument in format(arg)" + value);
                throw e;
            }
        }
        setText(text);
    }
}
