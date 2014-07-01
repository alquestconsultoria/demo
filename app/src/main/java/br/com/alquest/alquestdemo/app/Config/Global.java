package br.com.alquest.alquestdemo.app.Config;

public class Global {
    static String URL = "http://monitor.alquest.com.br/mobile/";
    static String DEBUGTAG = "Alquest Monitor";
    static String PROJECTID = "197276739740";
    static String GCMTOKEN = "invalid";
    static int LoginActivityId = 1;
    static int timeoutConnection = 10000;
    static boolean DEBUG = true;
    static boolean LOGIN = false;



    public static int getLoginActivityId(){
        return LoginActivityId;
    }

    public static boolean debug(){
        return DEBUG;
    }

    public static int getTimeoutConnection() {
        return timeoutConnection;
    }

    public static void setTimeoutConnection(int set) {
        timeoutConnection = set;
    }

    public static String getUrl() {
        return URL;
    }

    public static boolean getLogin() {
        return LOGIN;
    }

    public static void setLogin(boolean set) {
        LOGIN = set;
    }

    public static String getDebugTag() {
        return DEBUGTAG;
    }

    public static String getProjectId() {
        return PROJECTID;
    }

    public static String getToken() {
        return GCMTOKEN;
    }

    public static void setToken(String set) {
        GCMTOKEN = set;
    }
}
