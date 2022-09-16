package com.example.login.Model;

import java.io.Serializable;

public class Nhanvien implements Serializable {
    String chucvu, diachi, email, gioitinh, img, name, namsinh, sdt, phongban;

    public Nhanvien(){
    }

    public Nhanvien(String chucvu, String diachi, String email,
                    String gioitinh, String img, String name,
                    String namsinh,String sdt,String phongban) {
        this.chucvu = chucvu;
        this.diachi = diachi;
        this.email = email;
        this.gioitinh = gioitinh;
        this.img = img;
        this.name = name;
        this.namsinh = namsinh;
        this.sdt = sdt;
        this.phongban = phongban;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getGioitinh() {
        return gioitinh;
    }
    public void setGioitinh(String gioitinh) {
        this.gioitinh = gioitinh;
    }

    public String getNamsinh() {
        return namsinh;
    }
    public void setNamsinh(String namsinh) {
        this.namsinh = namsinh;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getDiachi() {
        return diachi;
    }
    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public String getChucvu() {
        return chucvu;
    }
    public void setChucvu(String chucvu) {
        this.chucvu = chucvu;
    }

    public String getImg() { return img; }
    public void setImg(String img) {
        this.img = img;
    }

    public String getSdt() {
        return sdt;
    }
    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getPhongban() {
        return phongban;
    }
    public void setPhongban(String phongban) {
        this.phongban = phongban;
    }
}

