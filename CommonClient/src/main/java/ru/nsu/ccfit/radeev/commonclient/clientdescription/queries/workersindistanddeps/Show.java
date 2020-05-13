package ru.nsu.ccfit.radeev.commonclient.clientdescription.queries.workersindistanddeps;
import ru.nsu.ccfit.radeev.commonclient.database.framework.core.visual.QueryVisual;
import ru.nsu.ccfit.radeev.commonclient.view.mainform.MainShow;

import javax.swing.*;
import java.sql.SQLException;

public class Show implements MainShow {
    private final Query query;

    private final JComponent representation;

    public Show() throws SQLException {
        query = new Query();
        representation = new QueryVisual(query);
    }

    public JComponent getRepresentation() {

        return representation;
    }
}
