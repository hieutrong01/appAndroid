package com.ocr.navigation.OOP;

public class GioHang {
    private int idProduct;
    private int resourceImage;
    private String gender;
    private String size;
    private String productName;
    private int price;
    private int soluong;


    public GioHang() {
    }

    public GioHang(int idProduct, int resourceImage, String gender, String size, String productName, int price, int soluong) {
        this.idProduct = idProduct;
        this.resourceImage = resourceImage;
        this.gender = gender;
        this.size = size;
        this.productName = productName;
        this.price = price;
        this.soluong=soluong;

    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public int getResourceImage() {
        return resourceImage;
    }

    public void setResourceImage(int resourceImage) {
        this.resourceImage = resourceImage;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }
}
