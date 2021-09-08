package com.example.wordsgame;

import java.io.Serializable;

public class Userfetchmodel implements Serializable {
    String id;
    String name;
    String email;
    String userName;
    String pass;

    public Userfetchmodel(String id, String name, String email, String userName, String pass) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.userName = userName;
        this.pass = pass;
    }
    public String getId(){
        return id;
    }
    public  String getName(){
        return  name;
    }
    public  String getEmail(){
        return  email;
    }
    public  String getUserName(){
        return userName;
    }
    public String getPass(){
        return pass;
    }
}
