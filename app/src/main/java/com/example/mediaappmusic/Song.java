package com.example.mediaappmusic;

import java.io.Serializable;

public class Song implements Serializable {
    private String tenBaiHat;
    private int file;
    private String nguoiTheHien;

    public Song(String title, int file) {
        this.tenBaiHat = title;
        this.file = file;
    }

    public Song(String title, String nguoiTheHien, int file) {
        this.tenBaiHat = title;
        this.nguoiTheHien = nguoiTheHien;
        this.file = file;
    }

    public Song(String title, String nguoiTheHien) {
        this.tenBaiHat = title;
        this.nguoiTheHien = nguoiTheHien;
    }


    public String getNguoiTheHien() {
        return nguoiTheHien;
    }

    public void setNguoiTheHien(String nguoiTheHien) {
        this.nguoiTheHien = nguoiTheHien;
    }

    public String getTenBaiHat() {
        return tenBaiHat;
    }

    public void setTenBaiHat(String tenBaiHat) {
        this.tenBaiHat = tenBaiHat;
    }

    public int getFile() {
        return file;
    }

    public void setFile(int file) {
        this.file = file;
    }
}
