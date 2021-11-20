package com.example.recycler.model;

import java.io.Serializable;

public class recycleModel extends ModelID implements Serializable {

    String latitude;
    String length;
    String date;
    String photo;
    String code;
    String bottle;
    String paperboard;
    String state;
    String points;

    public recycleModel() {

    }

    public recycleModel(String latitude, String length, String date, String photo, String code, String bottle, String paperboard, String state, String points) {
        this.latitude = latitude;
        this.length = length;
        this.date = date;
        this.photo = photo;
        this.code = code;
        this.bottle = bottle;
        this.paperboard = paperboard;
        this.state = state;
        this.points = points;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getBottle() {
        return bottle;
    }

    public void setBottle(String bottle) {
        this.bottle = bottle;
    }

    public String getPaperboard() {
        return paperboard;
    }

    public void setPaperboard(String paperboard) {
        this.paperboard = paperboard;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPoints() {
        return points;
    }

    public void setPoints(String points) {
        this.points = points;
    }
}
