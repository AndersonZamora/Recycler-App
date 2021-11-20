package com.example.recycler.model;

import java.io.Serializable;

public class purchaseModel extends ModelID implements Serializable {

    String title, price, quantity, number, image, description, my_id;

    public purchaseModel() {
    }

    public purchaseModel(String title, String price, String quantity, String number, String image, String description, String my_id) {
        this.title = title;
        this.price = price;
        this.quantity = quantity;
        this.number = number;
        this.image = image;
        this.description = description;
        this.my_id = my_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMy_id() {
        return my_id;
    }

    public void setMy_id(String my_id) {
        this.my_id = my_id;
    }
}
