package com.example.recycler.model;

import java.io.Serializable;

public class eventsModel extends ModelID implements Serializable {

    String name, date, image, description, link;

    public eventsModel() {

    }

    public eventsModel(String name, String date, String image, String description, String link) {
        this.name = name;
        this.date = date;
        this.image = image;
        this.description = description;
        this.link = link;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
