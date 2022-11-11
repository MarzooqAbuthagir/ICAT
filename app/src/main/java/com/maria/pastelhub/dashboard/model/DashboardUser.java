package com.maria.pastelhub.dashboard.model;


public class DashboardUser {

    public String id = "";
    public String name= "";
    public String classes= "";
    public String rating = "";
    public int image = 0;
    public int bookLanguage= 0;

    public DashboardUser setPrice(float price) {
        this.price = price;
        return this;
    }

    public DashboardUser setTeacher(String teacher) {
        this.teacher = teacher;
        return this;
    }

    public float price;
    public String teacher;

    public DashboardUser(String id, String name, String classes, String rating, int image, int bookLanguage) {
        this.id = id;
        this.name = name;
        this.classes = classes;
        this.rating = rating;
        this.image = image;
        this.bookLanguage = bookLanguage;
    }

    public DashboardUser() {
    }

}
