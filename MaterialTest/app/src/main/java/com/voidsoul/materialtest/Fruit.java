package com.voidsoul.materialtest;

public class Fruit {
    private String name;
    private int imgId;

    public Fruit(String name, int imgId){
        this.name = name;
        this.imgId = imgId;
    }

    public int getImgId() {
        return imgId;
    }

    public String getName() {
        return name;
    }
}
