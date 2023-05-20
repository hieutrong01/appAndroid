package com.ocr.navigation.OOP;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.Currency;


public class ProductList implements Serializable {
    private int idProduct;
    private int resourceImage;
    private String gender;
    private String size;
    private String productName;
    private int price;
    private String formattedPrice;
    private Boolean isFavorite;

    public ProductList(int idProduct, int resourceImage, String gender, String size, String name, String price, Boolean isFavorite) {}

    public ProductList(int idProduct,int resourceImage, String gender, String size, String productName, int price, Boolean isFavorite) {
        this.idProduct=idProduct;
        this.resourceImage = resourceImage;
        this.gender = gender;
        this.size = size;
        this.productName = productName;
        this.price = price;
this.isFavorite = isFavorite;
    }

    public String getFormattedPrice() {
        if (formattedPrice == null) {
            NumberFormat format = NumberFormat.getCurrencyInstance();
            Currency vnd = Currency.getInstance("VND");
            format.setCurrency(vnd);
            formattedPrice = format.format(price);
        }
        return formattedPrice;
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

    public void setFormattedPrice(String formattedPrice) {
        this.formattedPrice = formattedPrice;
    }

    public Boolean getFavorite() {
        return isFavorite;
    }

    public void setFavorite(Boolean favorite) {
        isFavorite = favorite;
    }

    @Override
    public String toString() {
        return String.format("%s|%s|%s|%s|%s|%s",getIdProduct(),getResourceImage(), getGender(), getSize(), getProductName(), getFormattedPrice());
    }


}