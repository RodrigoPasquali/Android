package com.example.listexampleapi.Model;

//ITEM
public class Post {
    private String title;
    private String price;
    private String image;

    public Post() {
    }

    public Post(String title, String price, String image) {
        this.title = title;
        this.price = price;
        this.setImage(image);
    }

    public Post(String title, String price) {
        this.title = title;
        this.price = price;
//        this.setImage(image);
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

    public String getImage() {
        return image;
    }


    public void setImage(String image) {
        // Se agrega una s para formar "https", ya que las imagenes son "http"
        if(!(image.substring(4,5).equals("s"))){
            this.image = image.substring(0,4) + "s" + image.substring(4);
        }else{
            this.image = image;
        }
    }
}