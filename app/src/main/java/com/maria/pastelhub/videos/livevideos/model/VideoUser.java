package com.maria.pastelhub.videos.livevideos.model;

public class VideoUser {

    public String id;
    public String name;
    public String url;
    public int image;

    public VideoUser(String id, String name, String url, int image) {
        this.id = id;
        this.name = name;
        this.url = url;
        this.image = image;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
