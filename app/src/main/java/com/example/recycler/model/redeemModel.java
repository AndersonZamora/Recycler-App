package com.example.recycler.model;

import java.io.Serializable;

public class redeemModel extends ModelID implements Serializable {

    String name, points, exchange, image;

    public redeemModel() {
    }

    public redeemModel(String name, String points, String exchange, String image) {
        this.name = name;
        this.points = points;
        this.exchange = exchange;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPoints() {
        return points;
    }

    public void setPoints(String points) {
        this.points = points;
    }

    public String getExchange() {
        return exchange;
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
