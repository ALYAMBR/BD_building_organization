package ru.nsu.ccfit.radeev.commonclient.config;

public class ClientConfig {
    private static int id = 0;
    private static boolean assigned = false;

    public static void setId(int newId){
        if(!assigned){
            id = newId;
            assigned = true;
        }
    }

    public static int getId(){
        return id;
    }
}
