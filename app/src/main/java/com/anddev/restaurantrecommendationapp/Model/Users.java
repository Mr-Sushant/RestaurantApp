package com.anddev.restaurantrecommendationapp.Model;

public class Users {

    String Id;
    String fname;
    String lname;
    String restname;
    String imageURL_one;
    String imageURL_two;
    String imageURL_three;
    String imageURL_four;
    String imageURL_five;
    String review;

    public Users(String id, String fname, String lname, String restname, String imageURL_one, String imageURL_two, String imageURL_three, String imageURL_four, String imageURL_five, String review) {
        Id = id;
        this.fname = fname;
        this.lname = lname;
        this.restname = restname;
        this.imageURL_one = imageURL_one;
        this.imageURL_two = imageURL_two;
        this.imageURL_three = imageURL_three;
        this.imageURL_four = imageURL_four;
        this.imageURL_five = imageURL_five;
        this.review = review;
    }

    public Users() {
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getRestname() {
        return restname;
    }

    public void setRestname(String restname) {
        this.restname = restname;
    }

    public String getImageURL_one() {
        return imageURL_one;
    }

    public void setImageURL_one(String imageURL_one) {
        this.imageURL_one = imageURL_one;
    }

    public String getImageURL_two() {
        return imageURL_two;
    }

    public void setImageURL_two(String imageURL_two) {
        this.imageURL_two = imageURL_two;
    }

    public String getImageURL_three() {
        return imageURL_three;
    }

    public void setImageURL_three(String imageURL_three) {
        this.imageURL_three = imageURL_three;
    }

    public String getImageURL_four() {
        return imageURL_four;
    }

    public void setImageURL_four(String imageURL_four) {
        this.imageURL_four = imageURL_four;
    }

    public String getImageURL_five() {
        return imageURL_five;
    }

    public void setImageURL_five(String imageURL_five) {
        this.imageURL_five = imageURL_five;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }
}
