package ru.nsu.ccfit.radeev.commonclient.clientdescription.tables.typeworks;

import ru.nsu.ccfit.radeev.commonclient.database.framework.core.visual.TableVisual;
import ru.nsu.ccfit.radeev.commonclient.view.mainform.MainShow;

import javax.swing.*;
import java.sql.SQLException;

public class Show implements MainShow {
    private final Table table;
    private final JComponent representation;

    public Show() throws SQLException {
        table = new Table();
        representation = new TableVisual(table);
    }

    public JComponent getRepresentation() {
        return representation;
    }
}

