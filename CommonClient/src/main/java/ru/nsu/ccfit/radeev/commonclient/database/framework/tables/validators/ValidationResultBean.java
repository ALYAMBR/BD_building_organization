package ru.nsu.ccfit.radeev.commonclient.database.framework.tables.validators;

public class ValidationResultBean implements ValidationResult {
    private final boolean isValid;
    private final String message;

    public ValidationResultBean(boolean isValid){
        this.isValid = isValid;
        this.message = "";
    }

    public ValidationResultBean(boolean isValid, String message){
        this.isValid = isValid;
        this.message = message;
    }

    @Override
    public boolean isValid() {
        return isValid;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
