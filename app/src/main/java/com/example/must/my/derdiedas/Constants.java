package com.example.must.my.derdiedas;

import java.util.ArrayList;

/**
 * Created by mustajab on 9/26/16.
 */

public class Constants {
    static INTERNETSTATE istate = INTERNETSTATE.CHECKING;
    static ArrayList<Noun> words = new ArrayList<>();
    static LEVEL level = LEVEL.ZERO;
    static String FILE_NAME = "";
    static String LOADING = "loading ...";
    final static String LEVEL_ZERO_FILE = "Words";
    final static String LEVEL_ONE_FILE = "LevelOne";

    static String getFileName(){
        return Constants.level.equals(Constants.LEVEL.ZERO) ? Constants.LEVEL_ZERO_FILE : Constants.LEVEL_ONE_FILE;
    }
    static ArrayList<Noun> getWords() {
        return words;
    }
    public static void setWords(ArrayList<Noun> nouns) {
        LOADING = "WORDS LOADED!";
        words = nouns;
    }


    enum INTERNETSTATE {
        CHECKING,
        CONNECTED,
        NOTCONNECTED;
    }

    enum LEVEL {
        ZERO,
        ONE;
    }
}
