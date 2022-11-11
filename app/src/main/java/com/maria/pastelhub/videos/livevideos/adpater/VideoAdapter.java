package com.maria.pastelhub.videos.livevideos.adpater;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.maria.pastelhub.R;
import com.maria.pastelhub.videos.playvideo.PlayVideo;
import com.maria.pastelhub.databinding.VideoBinding;
import com.maria.pastelhub.videos.livevideos.interfaces.ClickListener;
import com.maria.pastelhub.videos.livevideos.viewmodel.VideoViewModel;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubeThumbnailLoader;
import com.google.android.youtube.player.YouTubeThumbnailView;

import java.util.List;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.VideoView> {

    List<VideoViewModel> videoViewModelsList;
    private LayoutInflater layoutInflater;

    public VideoAdapter(List<VideoViewModel> videoViewModelsList) {
        this.videoViewModelsList = videoViewModelsList;
    }

    @NonNull
    @Override
    public VideoView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.getContext());
        }
        VideoBinding videoBinding = VideoBinding.inflate(layoutInflater, parent, false);
        YouTubeThumbnailView imageview_book = videoBinding.ytt;
        videoBinding.ytt.initialize(parent.getContext().getString(R.string.youtube_api_key), new YouTubeThumbnailView.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubeThumbnailView youTubeThumbnailView, final YouTubeThumbnailLoader youTubeThumbnailLoader) {
                youTubeThumbnailLoader.setVideo(videoBinding.getVideoViewModel().url);

                youTubeThumbnailLoader.setOnThumbnailLoadedListener(new YouTubeThumbnailLoader.OnThumbnailLoadedListener() {
                    @Override
                    public void onThumbnailLoaded(YouTubeThumbnailView youTubeThumbnailView, String s) {
                        //when thumbnail loaded successfully release the thumbnail loader as we are showing thumbnail in adapter
                        System.out.println("image url "+s);
                        youTubeThumbnailLoader.release();
                    }

                    @Override
                    public void onThumbnailError(YouTubeThumbnailView youTubeThumbnailView, YouTubeThumbnailLoader.ErrorReason errorReason) {
                        //print or show error when thumbnail load failed
                        Log.e(getClass().getName(), "Youtube Thumbnail Error");
                    }
                });
            }

            @Override
            public void onInitializationFailure(YouTubeThumbnailView youTubeThumbnailView, YouTubeInitializationResult youTubeInitializationResult) {

            }
        });
        videoBinding.setPresenter(new ClickListener() {
            @Override
            public void onclickListener() {

                Log.d("click me ", "click me " + videoBinding.getVideoViewModel().id);
//                Toast.makeText(parent.getContext(), "" + videoBinding.getVideoViewModel().url, Toast.LENGTH_LONG).show();
                Intent intent = new Intent(parent.getContext(), PlayVideo.class);
                intent.putExtra("path", videoBinding.getVideoViewModel().url.toString());
                parent.getContext().startActivity(intent);
            }
        });
        return new VideoAdapter.VideoView(videoBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull VideoView holder, int position) {
        VideoViewModel videoViewModel = videoViewModelsList.get(position);
        holder.bind(videoViewModel);
    }

    @Override
    public int getItemCount() {
        return videoViewModelsList.size();
    }

    public class VideoView extends RecyclerView.ViewHolder {

        private VideoBinding videoBinding;

        public VideoView(VideoBinding videoBinding) {
            super(videoBinding.getRoot());
            this.videoBinding = videoBinding;
        }

        public void bind(VideoViewModel videoViewModel) {
            this.videoBinding.setVideoViewModel(videoViewModel);
        }

        public VideoBinding getBookBinding() {
            return videoBinding;
        }

    }

}
