package com.maria.pastelhub.videos.livevideos.viewmodel;

import androidx.databinding.BindingAdapter;
import androidx.lifecycle.ViewModel;

import com.bumptech.glide.Glide;
import com.maria.pastelhub.videos.livevideos.model.VideoUser;
import com.google.android.youtube.player.YouTubeThumbnailView;
//import com.squareup.picasso.Picasso;


public class VideoViewModel extends ViewModel {

    public String id;
    public String name;
    public String url;
    public int image;

    public VideoViewModel() {
    }

    public VideoViewModel(VideoUser videoUser) {
        this.id = videoUser.id;
        this.name = videoUser.name;
        this.url = videoUser.url;
        this.image = videoUser.image;
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

    @BindingAdapter(value = "imageUrl")
    public static void loadImage(YouTubeThumbnailView imageView, int imageUrl) {
//        Picasso.get().load(imageUrl).fit().into(imageView);
        Glide.with(imageView).load(imageUrl).fitCenter();
    }

}
