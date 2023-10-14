package com.lab.listviewexample;

public class Animal {

    private String type;
    private int picID;


    public Animal(String type, int picId)
    {
        this.picID = picId;
        this.type = type;
    }

    public String getType()
    {
        return this.type;
    }
    public void setType(String type)
    {
        this.type = type;
    }

    public int getPicID() {
        return picID;
    }

    public void setPicID(int picID) {
        this.picID = picID;
    }
}
