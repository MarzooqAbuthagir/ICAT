package com.maria.pastelhub.book_landing.model;

public class BookLandingUser {

    public String id;
    public String name;
    public String rating;
    public String date_time;
    public String review;
    public String first_letter_review;

    public BookLandingUser(String id, String name, String rating, String date_time, String review, String first_letter_review) {
        this.id = id;
        this.name = name;
        this.rating = rating;
        this.date_time = date_time;
        this.review = review;
        this.first_letter_review = first_letter_review;
    }

    public BookLandingUser() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getDate_time() {
        return date_time;
    }

    public void setDate_time(String date_time) {
        this.date_time = date_time;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public String getFirst_letter_review() {
        return first_letter_review;
    }

    public void setFirst_letter_review(String first_letter_review) {
        this.first_letter_review = first_letter_review;
    }

}
