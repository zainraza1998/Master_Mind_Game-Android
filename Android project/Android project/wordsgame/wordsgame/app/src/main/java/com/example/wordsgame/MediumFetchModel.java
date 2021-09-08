package com.example.wordsgame;

import java.io.Serializable;

public class MediumFetchModel implements Serializable {
    String id ;
    String MediumWord;

    public MediumFetchModel(String id, String mediumWord) {
        this.id = id;
        this.MediumWord = mediumWord;
    }
    public String getId(){
        return id;
    }
    public String getMediumWord(){
        return MediumWord;
    }
}
