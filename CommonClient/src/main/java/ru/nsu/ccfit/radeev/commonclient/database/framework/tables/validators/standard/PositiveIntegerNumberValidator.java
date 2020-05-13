package ru.nsu.ccfit.radeev.commonclient.database.framework.tables.validators.standard;

import ru.nsu.ccfit.radeev.commonclient.database.framework.tables.validators.ValidationResult;
import ru.nsu.ccfit.radeev.commonclient.database.framework.tables.validators.ValidationResultBean;
import ru.nsu.ccfit.radeev.commonclient.database.framework.tables.validators.Validator;

public class PositiveIntegerNumberValidator implements Validator {
    @Override
    public ValidationResult validate(Object object) {
        if(object == null){
            return new ValidationResultBean(false, "Is null");
        }

        if(!(object instanceof Number)){
            return new ValidationResultBean(false, "NaN");
        }

        if(((Number)object).intValue() < 0){
            return new ValidationResultBean(false, "less than zero");
        }

        return new ValidationResultBean(true);
    }
}
