package com.example.wordsgame;

import java.io.Serializable;

public class HardDataModel implements Serializable {
    String id;
    String hardWord;

    public HardDataModel(String id, String hardWord) {
        this.id = id;
        this.hardWord = hardWord;
    }
    public String getId(){
        return id;
    }
    public String getHardWord(){
        return hardWord;
    }
}
