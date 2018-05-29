package com.example.jarviz.reyclerview_static;
//model
public class Product {

//variables

    private int id;
    private  String title,shortdesc;
    private double rating;
    private double price;
    private String image;
//generate constructor
    public Product(int id, String title, String shortdesc, double rating, double price, String image) {
        this.id = id;
        this.title = title;
        this.shortdesc = shortdesc;
        this.rating = rating;
        this.price = price;
        this.image = image;
    }



    // generate  getter
    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getShortdesc() {
        return shortdesc;
    }

    public double getRating() {
        return rating;
    }

    public double getPrice() {
        return price;
    }

    public String getImage() {
        return image;
    }
}
