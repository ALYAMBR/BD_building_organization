package ru.nsu.ccfit.radeev.commonclient.view.utils;

public class Action {
    private final String className;
    private final String screenName;

    public Action(String screenName, String className) {
        assert (className != null);
        assert (screenName != null);
        this.className = className;
        this.screenName = screenName;
    }

    @Override
    public String toString(){
        return screenName;
    }

    public String getClassName(){
        return className;
    }

    public String getScreenName(){
        return screenName;
    }
}
