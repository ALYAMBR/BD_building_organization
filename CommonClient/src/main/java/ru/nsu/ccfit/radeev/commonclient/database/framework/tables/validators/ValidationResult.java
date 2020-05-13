package ru.nsu.ccfit.radeev.commonclient.database.framework.tables.validators;

public interface ValidationResult {
    boolean isValid();
    String getMessage();
}
