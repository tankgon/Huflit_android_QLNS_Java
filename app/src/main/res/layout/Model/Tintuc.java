package com.example.login.Model;

import java.io.Serializable;

public class Tintuc implements Serializable{
    String name;
    String image;
    String content;

    public Tintuc() {
    }

    public Tintuc(String name, String image,  String content) {
        this.name = name;
        this.image = image;
        this.content = content;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getResKey() {
        return content;
    }

    public void setResKey(String content) {
        this.content = content;
    }



    @Override
    public String toString() {
        return "Tintuc{" +
                "name='" + name + '\'' +
                ", image=" + image +
                ", resKey='" + content + '\'' +
                '}';
    }
}

