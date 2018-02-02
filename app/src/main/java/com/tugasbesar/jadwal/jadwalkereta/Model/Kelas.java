package com.tugasbesar.jadwal.jadwalkereta.Model;

/**
 * Created by Wahyu on 1/30/2018.
 */

public class Kelas {
    private String Kelas;
    private String Image;

    public Kelas() {
    }

    public Kelas(String kelas, String image) {
        Kelas = kelas;
        Image = image;
    }

    public String getKelas() {
        return Kelas;
    }

    public void setKelas(String kelas) {
        Kelas = kelas;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }
}
