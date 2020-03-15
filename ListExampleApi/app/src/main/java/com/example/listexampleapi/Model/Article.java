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

    public Article(String id, String title, String price, String urlItemImage) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.setUrlItemImage(urlItemImage);
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

    public void setLinkToPublication(String link) {
        this.linkToPublication = link;
    }

    public String getLinkToPublication(){
        return this.linkToPublication;
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

    public String getUrlImage() {
        return this.imageUrl;
    }

    public void setUrlImage(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getUrlItemImage() {
        return this.itemImageUrl;
    }

    public void setUrlItemImage(String itemImageUrl) {
        this.itemImageUrl = checkUrl(itemImageUrl);
    }

    public void setItemImage(Bitmap itemImage) {
        this.itemImage = itemImage;
    }

    public Bitmap getItemImage() {
        return this.itemImage;
    }

    public Bitmap getImage() {
        return this.image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }
    private String checkUrl(String url){
        // Se agrega una s para formar "https", ya que las imagenes son "http"
        if(!(url.substring(4,5).equals("s"))){
            url = url.substring(0,4) + "s" + url.substring(4);
        }

        return url;
    }
}