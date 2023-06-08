package com.ocr.navigation.OOP;

import java.io.Serializable;
import java.util.List;

public class Product implements Serializable {
    private String name;
    private int product_id;
    private String description;
    private int price;
    private int quantity;
    private String gender;
    private String image;
    private List<KichCo> kichco;

    public Product() {
    }

    public Product(String name, int product_id, String description, int price, int quantity, String gender, String image, List<KichCo> kichco) {
        this.name = name;
        this.product_id = product_id;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
        this.gender = gender;
        this.image = image;
        this.kichco = kichco;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<KichCo> getKichco() {
        return kichco;
    }

    public void setKichco(List<KichCo> kichco) {
        this.kichco = kichco;
    }
}
