package com.example.listexampleapi.Model;

import android.graphics.Bitmap;

public class Article {
    private String title;
    private String price;
    private String imageUrl;
    private String id;
    private Bitmap image;
    private String condition;
    private String brand;
    private String model;
    private String quantityAvailable;
    private String quantitySold;
    private String linkToPublication;

    public Article(String id) {
        this.id = id;
        this.image = null;
    }

    public Article(String id, String title, String price, String imageUrl) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.setUrlImage(imageUrl);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getUrlImage() {
        return imageUrl;
    }


    public void setUrlImage(String image) {
        // Se agrega una s para formar "https", ya que las imagenes son "http"
        if(!(image.substring(4,5).equals("s"))){
            this.imageUrl = image.substring(0,4) + "s" + image.substring(4);
        }else{
            this.imageUrl = image;
        }
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public Bitmap getImage() {
        return this.image;
    }

    public void setLinkToPublication(String link) {
        this.linkToPublication = link;
    }

    public String getLinkToPublication(){
        return this.linkToPublication;
    }
}