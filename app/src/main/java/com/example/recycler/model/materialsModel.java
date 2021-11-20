package com.example.recycler.model;

import java.io.Serializable;

public class materialsModel extends ModelID implements Serializable {

    String name;
    String price;
    String number_whatsapp;
    String image;
    String quantity;
    String description;

    public materialsModel() {
    }

    public materialsModel(String name, String price, String number_whatsapp, String image, String quantity, String description) {
        this.name = name;
        this.price = price;
        this.number_whatsapp = number_whatsapp;
        this.image = image;
        this.quantity = quantity;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getNumber_whatsapp() {
        return number_whatsapp;
    }

    public void setNumber_whatsapp(String number_whatsapp) {
        this.number_whatsapp = number_whatsapp;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
