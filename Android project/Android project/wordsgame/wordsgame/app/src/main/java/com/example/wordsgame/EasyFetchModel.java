package com.example.wordsgame;

import java.io.Serializable;

public class EasyFetchModel implements Serializable {
    String id ;
    String Easyword;
    public EasyFetchModel(String id, String wordName) {
        this.id = id;
        this.Easyword = wordName;
    }
    public String getId(){
        return id;
    }

    public  String getWords(){
        return Easyword;
    }

}
