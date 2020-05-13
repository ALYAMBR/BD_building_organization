package ru.nsu.ccfit.radeev.commonclient.view.utils;

public class LookupEntry {
    private final int id;
    private final String name;

    public LookupEntry(int id, String name){
        this.id = id;
        this.name = name;
    }

    public int getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public String toString(){
        return name;
    }
}
