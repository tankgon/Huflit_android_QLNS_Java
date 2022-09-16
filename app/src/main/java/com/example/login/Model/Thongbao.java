package com.example.login.Model;

import java.io.Serializable;

public class Thongbao implements Serializable{
    String name;
    String content;
    String notiKey;

    public Thongbao() {
    }

    public Thongbao(String name, String content, String notiKey) {
        this.name = name;
        this.content = content;
        this.notiKey = notiKey;
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

    public String getNotiKey() {
        return notiKey;
    }

    public void setNotiKey(String notiKey) {
        this.notiKey = notiKey;
    }


    @Override
    public String toString() {
        return "Thongbao{" +
                "name='" + name + '\'' +
                ", content='" + content +
                '}';
    }
}

