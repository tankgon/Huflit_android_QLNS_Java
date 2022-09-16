package com.example.login.Model;

import java.io.Serializable;

public class Thongbao implements Serializable{
    String name;
    String content;

    public Thongbao() {
    }

    public Thongbao(String name, String content) {
        this.name = name;
        this.content = content;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }



    @Override
    public String toString() {
        return "Thongbao{" +
                "name='" + name + '\'' +
                ", resKey='" + content + '\'' +
                '}';
    }
}

