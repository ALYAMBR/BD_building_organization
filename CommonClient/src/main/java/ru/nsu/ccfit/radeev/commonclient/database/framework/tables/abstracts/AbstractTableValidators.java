package ru.nsu.ccfit.radeev.commonclient.database.framework.tables.abstracts;

import ru.nsu.ccfit.radeev.commonclient.database.framework.tables.interfaces.tables.TableValidators;
import ru.nsu.ccfit.radeev.commonclient.database.framework.tables.validators.Validator;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractTableValidators implements TableValidators {
    private final Map<Integer, Validator> validators = new HashMap<>();

    public AbstractTableValidators() {

    }

    protected void addValidator(int column, Validator validator) {
        assert (column >= 0);
        validators.put(column, validator);

    }

    public Validator getValidator(int column) {
        Validator validator = validators.get(column);
        return validator;
    }
}
