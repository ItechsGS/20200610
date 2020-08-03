package com.org.godspeed.model;

public class AppMenu {
    public int Id;
    public String Name;
    public String Image;
    public int ImageId;

    public AppMenu(int itemId, int imageId, String itemName) {
        this.Id = itemId;
        this.Name = itemName;
        this.ImageId = imageId;
    }

    public int getId() {
        return this.Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return this.Name;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public String getImage() {
        return this.Image;
    }

    public void setImage(String image) {
        this.Image = image;
    }

    public int getImageId() {
        return this.ImageId;
    }

    public void setImageId(int imageId) {
        this.ImageId = imageId;
    }
}