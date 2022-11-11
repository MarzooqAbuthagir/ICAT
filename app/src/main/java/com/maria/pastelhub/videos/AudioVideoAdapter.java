package com.maria.pastelhub.videos;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.AudioManager;
import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.maria.pastelhub.R;
import com.maria.pastelhub.prefference.Pref;
import com.google.android.youtube.player.YouTubePlayerView;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashMap;

public class AudioVideoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    ArrayList<AudioVideoBean> audioVideoList;
    private final Context context;
    private final int contentView;
    public int selectedPos = -1;
    public MediaPlayer player = new MediaPlayer();
    Pref pref;
    SharedPreferences sPref;
    String lan = "";
    int previousSelectedPos = -1;
    boolean isPlay = false;

    public AudioVideoAdapter(Context context, ArrayList<AudioVideoBean> audioVideoList, int contentView, String lan) {
        this.context = context;
        this.audioVideoList = audioVideoList;
        this.contentView = contentView;
        this.lan = lan;
        pref = new Pref(context);
        sPref = context.getSharedPreferences("SharedPrefManager", 0);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (contentView == 0)
            return new AudioHolder(layoutInflater.inflate(R.layout.item_audio_view, parent, false));
        else
            return new VideoHolder(layoutInflater.inflate(R.layout.item_video_view, parent, false));
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        AudioVideoBean bean = audioVideoList.get(position);
        if (holder instanceof AudioHolder) {
            try {
                AudioHolder audioHolder = ((AudioHolder) holder);
                audioHolder.tvTitle.setText(bean.getTitle());
                audioHolder.tvLyric.setText(bean.getLyric());
                previousSelectedPos = selectedPos;


//                audioHolder.ivPlay.setImageDrawable(selectedPos == -1 ? ContextCompat.getDrawable(context, R.drawable.ic_play) : ContextCompat.getDrawable(context, R.drawable.ic_pause));

                new Thread() {
                    @Override
                    public void run() {
                        try {
                            if (sPref.getString(bean.getUrl(), "").equals("")) {
                                MediaMetadataRetriever retriever = new MediaMetadataRetriever();
                                retriever.setDataSource(bean.getUrl(), new HashMap<String, String>());
                                String time = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION);
                                long timeInmillisec = Long.parseLong(time);
                                long duration = timeInmillisec / 1000;
                                long hours = duration / 3600;
                                long minutes = (duration - hours * 3600) / 60;
                                long seconds = duration - (hours * 3600 + minutes * 60);
                                sPref.edit().putString(bean.getUrl(), (minutes < 10 ? "0" + minutes : minutes) + ":" + (seconds < 10 ? "0" + seconds : seconds)).apply();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        ((Activity) context).runOnUiThread(new Runnable() {
                            public void run() {
                                audioHolder.tvDescription.setText(sPref.getString(bean.getUrl(), "--:--"));
                            }
                        });
                    }
                }.start();

                audioHolder.ivPlay.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try {
                            if (player == null)
                                player = new MediaPlayer();

                            if (previousSelectedPos == position) {
                                Log.i(getClass().getName(), "===================== IF   " + previousSelectedPos + "   " + selectedPos);
                                if (isPlay)
                                    audioHolder.ivPlay.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_pause));
                                else
                                    audioHolder.ivPlay.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_play));
                            } else {
                                Log.i(getClass().getName(), "===================== ELE  " + previousSelectedPos + "   " + selectedPos);

                                audioHolder.ivPlay.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_play));
                            }
                            if (!player.isPlaying()) {
                                selectedPos = position;
                                player.setAudioStreamType(AudioManager.STREAM_MUSIC);
                                player.setDataSource(bean.getUrl());
                                player.prepare();
                                player.start();
                                isPlay = true;
                            } else {
                                audioHolder.ivPlay.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_pause));
                                player.release();
                                player = null;
//                                selectedPos = -1;
                                isPlay = false;
                            }


                            notifyItemChanged(position);

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });

                if (player != null)
                    player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            // TODO Auto-generated method stub
                            mp.reset();
                            mp.release();
                            selectedPos = -1;
                            isPlay = false;
                            notifyItemChanged(position);
                        }
                    });
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {
            VideoHolder videoHolder = ((VideoHolder) holder);
            videoHolder.tvTitle.setText(bean.getTitle());
            videoHolder.rl_progress.setVisibility(View.VISIBLE);
            videoHolder.fullScreen.setVisibility(View.GONE);

            final MediaController mediacontroller = new MediaController(context);
            mediacontroller.setAnchorView(videoHolder.videoView);
            videoHolder.videoView.setMediaController(mediacontroller);
            try {
                videoHolder.videoView.setVideoURI(Uri.parse(bean.getUrl()));
            } catch (Exception e) {
                e.printStackTrace();
            }
            videoHolder.videoView.setKeepScreenOn(true);
            videoHolder.videoView.requestFocus();
            new Thread() {
                @Override
                public void run() {
                    if (sPref.getString(bean.getUrl(), "").equals("")) {
                        try {
                            Bitmap bitmapThumbnai = retriveVideoFrameFromVideo(bean.getUrl());
                            encodeBitmapTobase64(bitmapThumbnai, bean.getUrl());
                        } catch (Throwable throwable) {
                            throwable.printStackTrace();
                        }
                    }
                    ((Activity) context).runOnUiThread(new Runnable() {
                        public void run() {
                            videoHolder.thumbnailView.setImageBitmap(deCodeBitmap(sPref.getString(bean.getUrl(), "")));
                        }
                    });
                }
            }.start();

            videoHolder.videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    videoHolder.rl_progress.setVisibility(View.GONE);
                    videoHolder.fullScreen.setVisibility(View.VISIBLE);
                    mp.setOnVideoSizeChangedListener(new MediaPlayer.OnVideoSizeChangedListener() {
                        @Override
                        public void onVideoSizeChanged(MediaPlayer mp, int width, int height) {
                            videoHolder.videoView.setMediaController(mediacontroller);
                            mediacontroller.setAnchorView(videoHolder.videoView);
                            try {
                                if (mp.isPlaying())
                                    videoHolder.thumbnailView.setVisibility(View.GONE);


                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            });

            videoHolder.videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    mp.release();
                    Toast.makeText(context, "Videos completed", Toast.LENGTH_SHORT).show();
                    videoHolder.thumbnailView.setVisibility(View.VISIBLE);
                }
            });

            videoHolder.videoView.setOnErrorListener(new MediaPlayer.OnErrorListener() {
                @Override
                public boolean onError(MediaPlayer mp, int what, int extra) {
                    Log.d("DEBUG", "What " + what + " extra " + extra);
                    videoHolder.rl_progress.setVisibility(View.GONE);
                    return false;
                }
            });

            videoHolder.fullScreen.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        Intent intent = new Intent(context, FullScreenVideoView.class);
                        intent.putExtra("videoUrl", bean.getUrl());
                        intent.putExtra("duration", videoHolder.videoView.getCurrentPosition());
                        context.startActivity(intent);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });

        }

    }

    public static Bitmap retriveVideoFrameFromVideo(String videoPath) throws Throwable {
        Bitmap bitmap = null;
        MediaMetadataRetriever mediaMetadataRetriever = null;
        try {
            mediaMetadataRetriever = new MediaMetadataRetriever();
            mediaMetadataRetriever.setDataSource(videoPath, new HashMap<String, String>());
            bitmap = mediaMetadataRetriever.getFrameAtTime(8000000, MediaMetadataRetriever.OPTION_CLOSEST_SYNC);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Throwable("Exception in retriveVideoFrameFromVideo(String videoPath)" + e.getMessage());

        } finally {
            if (mediaMetadataRetriever != null) {
                mediaMetadataRetriever.release();
            }
        }
        return bitmap;
    }

    public void encodeBitmapTobase64(Bitmap bitmap, String title) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 90, stream);
        byte[] image = stream.toByteArray();
        String img_str = android.util.Base64.encodeToString(image, android.util.Base64.DEFAULT);
        sPref.edit().putString(title, img_str).apply();
    }

    public Bitmap deCodeBitmap(String bitmapStr) {
        //decode string to image
        byte[] imageAsBytes = android.util.Base64.decode(bitmapStr.getBytes(), android.util.Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.length);
    }

    @Override
    public int getItemCount() {
        return audioVideoList.size();
    }

    static class VideoHolder extends RecyclerView.ViewHolder {
        TextView tvTitle;
        VideoView videoView;
        ImageView thumbnailView, fullScreen;
        RelativeLayout rl_progress;
        YouTubePlayerView youTubePlayerView;

        public VideoHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.title_tv);
            videoView = itemView.findViewById(R.id.video_view);
            rl_progress = itemView.findViewById(R.id.rl_progress);
            thumbnailView = itemView.findViewById(R.id.image);
            fullScreen = itemView.findViewById(R.id.fullScreen);
        }
    }

    static class AudioHolder extends RecyclerView.ViewHolder {
        TextView tvTitle, tvDescription, tvLyric;
        ImageView ivPlay;


        public AudioHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.title_tv);
            tvDescription = itemView.findViewById(R.id.file_des_tv);
            ivPlay = itemView.findViewById(R.id.play_iv);
            tvLyric = itemView.findViewById(R.id.tv_lyrics);
        }
    }
}