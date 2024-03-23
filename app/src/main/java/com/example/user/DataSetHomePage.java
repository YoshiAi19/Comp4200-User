package com.example.user;

public class DataSetHomePage {

    public String text;
    public int image;

    public DataSetHomePage(String text, int image){
        this.text = text;
        this.image = image;
    }

    public int getImage(){
        return  image;
    }
    public String getText(){
        return text;
    }
}