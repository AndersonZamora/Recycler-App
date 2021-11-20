package com.example.recycler.model;

public class locationModel extends ModelID{

    String latitude, length, name, code;

    public locationModel() {
    }

    public locationModel(String latitude, String length, String name, String code) {
        this.latitude = latitude;
        this.length = length;
        this.name = name;
        this.code = code;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
