package com.example.listexampleapi.Model;

import android.graphics.Bitmap;

public class Article {
    private String id;
    private String title;
    private String price;
    private String itemImageUrl;
    private Bitmap itemImage;
    private String condition;
    private String brand;
    private String model;
    private String availableQuantity;
    private String soldQuantity;
    private String linkToPublication;
    private String imageUrl;
    private Bitmap image;

    public Article(String id) {
        this.id = id;
        this.itemImage = null;
    }

    public Article(String id, String title, String price, String itemImageUrl) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.setUrlImage(itemImageUrl);
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPrice() {
        return this.price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getUrlImage() {
        return this.itemImageUrl;
    }


    public void setUrlImage(String image) {
        // Se agrega una s para formar "https", ya que las imagenes son "http"
        if(!(image.substring(4,5).equals("s"))){
            this.itemImageUrl = image.substring(0,4) + "s" + image.substring(4);
        }else{
            this.itemImageUrl = image;
        }
    }

    public void setItemImage(Bitmap itemImage) {
        this.itemImage = itemImage;
    }

    public Bitmap getItemImage() {
        return this.itemImage;
    }

    public void setLinkToPublication(String link) {
        this.linkToPublication = link;
    }

    public String getLinkToPublication(){
        return this.linkToPublication;
    }


    public String getItemImageUrl() {
        return this.itemImageUrl;
    }

    public void setItemImageUrl(String itemImageUrl) {
        this.itemImageUrl = itemImageUrl;
    }

    public String getCondition() {
        return this.condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getBrand() {
        return this.brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return this.model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getAvailableQuantity() {
        return this.availableQuantity;
    }

    public void setAvailableQuantity(String availableQuantity) {
        this.availableQuantity = availableQuantity;
    }

    public String getSoldQuantity() {
        return this.soldQuantity;
    }

    public void setSoldQuantity(String soldQuantity) {
        this.soldQuantity = soldQuantity;
    }

    public String getImageUrl() {
        return this.imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Bitmap getImage() {
        return this.image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

}