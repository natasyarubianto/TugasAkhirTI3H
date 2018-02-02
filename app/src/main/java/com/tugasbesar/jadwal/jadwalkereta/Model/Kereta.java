package com.tugasbesar.jadwal.jadwalkereta.Model;

/**
 * Created by Wahyu on 2/1/2018.
 */

public class Kereta {
    private String Name,Image,Tujuan, Price, MenuId;

    public Kereta() {
    }

    public Kereta(String name, String image, String tujuan, String price, String menuId) {
        Name = name;
        Image = image;
        Tujuan = tujuan;
        Price = price;
        MenuId = menuId;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getTujuan() {
        return Tujuan;
    }

    public void setTujuan(String tujuan) {
        Tujuan = tujuan;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getMenuId() {
        return MenuId;
    }

    public void setMenuId(String menuId) {
        MenuId = menuId;
    }
}
