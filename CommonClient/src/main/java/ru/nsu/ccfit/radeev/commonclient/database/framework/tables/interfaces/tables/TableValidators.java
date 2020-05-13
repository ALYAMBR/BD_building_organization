package ru.nsu.ccfit.radeev.commonclient.database.framework.tables.interfaces.tables;

import ru.nsu.ccfit.radeev.commonclient.database.framework.tables.validators.Validator;

public interface TableValidators {
    public Validator getValidator(int column);
}
