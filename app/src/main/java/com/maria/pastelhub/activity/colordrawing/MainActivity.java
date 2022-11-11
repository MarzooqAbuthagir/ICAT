package com.maria.pastelhub.activity.colordrawing;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.maria.pastelhub.R;
import com.maria.pastelhub.SingleTon;
import com.maria.pastelhub.activity.colordrawing.colorpicker.ColorPickerPopup;
import com.maria.pastelhub.alert.AlertClass;
import com.maria.pastelhub.api.RetrofitClient;
import com.maria.pastelhub.api.SaveScore;
import com.maria.pastelhub.prefference.Pref;
import com.google.gson.JsonObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    BitmapView bitmapView;
    Bitmap bitmap;
    int lastsource;

    ColorAdapter adapter;

    boolean hasColor = false;
    String language = "", selectedClass = "", title = "", actName = "";
    Pref pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main1);
            pref = new Pref(this);
            bitmapView = findViewById(R.id.bitmapview);

            findViewById(R.id.back_major_header).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onBackPressed();
                }
            });
            language = getIntent().getStringExtra("language");
            title = getIntent().getStringExtra("title");
            selectedClass = getIntent().getStringExtra("selectedClass");
            actName = getIntent().getStringExtra("actName");
            System.out.println("value--------------" + language);
            System.out.println("value--------------" + selectedClass);
            System.out.println("value--------------" + title);
            System.out.println("value--------------" + actName);
            int classn = getIntent().getIntExtra("class", 1);
            if (language.equals("1")) {
                if (classn > 1) {
                    classn = classn - 1;
                    lastsource = getResources().getIdentifier("colouring_" + classn + "_" +
                                    getIntent().getIntExtra("Position", 1), "drawable",
                            getPackageName());
                } else if (classn == 1) {
                    lastsource = getResources().getIdentifier("colouring_" + "0_1_" +
                                    getIntent().getIntExtra("Position", 1), "drawable",
                            getPackageName());
                } else {
                    lastsource = getResources().getIdentifier("colouring_" + "0_" +
                                    getIntent().getIntExtra("Position", 1), "drawable",
                            getPackageName());
                }

            } else if (language.equals("0") && classn == 0) {
                int pos = (getIntent().getIntExtra("Position", 1));
                if (pos > 12) {
                    lastsource = getResources().getIdentifier("colouring_" + "1_2_" +
                                    pos, "drawable",
                            getPackageName());
                } else
                    lastsource = getResources().getIdentifier("colouring_" + "1_1_" +
                                    pos, "drawable",
                            getPackageName());

            } else {
                lastsource = getResources().getIdentifier("colouring_" + classn + "_" +
                                getIntent().getIntExtra("Position", 1), "drawable",
                        getPackageName());
            }


            if (getIntent().getStringExtra("language").equals("1"))
                ((TextView) findViewById(R.id.major_title)).setText(getString(R.string.celebrating_colouring));
            else
                ((TextView) findViewById(R.id.major_title)).setText(getString(R.string.coloring_activity_tamil));
            initView();

            RecyclerView recyclerView = findViewById(R.id.recyclerview);
            recyclerView.setLayoutManager(new GridLayoutManager(this, 8));
            recyclerView.setAdapter(adapter = new ColorAdapter());
            lastcolor = Color.parseColor("#f2753d");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        SingleTon.setListener(this, findViewById(R.id.sound_icon));
    }

    //    @Override
//    protected void onPause() {
//        super.onPause();
//        stopService(new Intent(this,MusicService.class));
//    }
    private void initView() {
        try {
            System.gc();

            hasColor = false;

            if (bitmap != null) {
                bitmap.recycle();
                bitmap = null;
            }
            bitmap = getOriginal().copy(Bitmap.Config.RGB_565, true);
            bitmapView.setImage(bitmap);
            final QueueLinearFloodFiller floodFiller = new QueueLinearFloodFiller(bitmap, Color.WHITE, lastcolor);
            bitmapView.setOnBitmapClickListener(new BitmapView.OnBitmapClickListener() {
                @Override
                public void onBitmapClicked(final int x, final int y) {
                    hasColor = true;
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            int pixel = bitmap.getPixel(x, y);
                            //then do what you want with the pixel data, e.g
                            int redValue = Color.red(pixel);
                            int blueValue = Color.blue(pixel);
                            int greenValue = Color.green(pixel);

                            if (isColorDark(redValue, greenValue, blueValue)) {
                                Log.d("Tag", "Touch position is dark");
                                return;
                            }
                            floodFiller.setTargetColor(Color.rgb(redValue, blueValue, greenValue));
                            if (isErasing) {
                                if (Color.rgb(redValue, blueValue, greenValue) == Color.WHITE)
                                    return;
                                floodFiller.fillColor = Color.WHITE;
                            } else {
                                if (Color.rgb(redValue, blueValue, greenValue) == lastcolor)
                                    return;
                                floodFiller.fillColor = lastcolor;
                            }
                            floodFiller.setTolerance(100);
                            floodFiller.floodFill(x, y);
                        }
                    }).start();
                }
            });
        } catch (RuntimeException e) {
            e.printStackTrace();
            Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show();
        }

    }

    public boolean isColorDark(int r, int g, int b) {
        double darkness = 1 - (0.299 * r + 0.587 * g + 0.114 * b) / 255;
        Log.d("TAg", "Dark" + darkness);
        return (darkness >= 0.9);
    }


    @Override
    public void onBackPressed() {
        finish();
        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
    }

    private Bitmap getOriginal() {
        try {
            DisplayMetrics displayMetrics = new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            int height = displayMetrics.heightPixels;
            int width = displayMetrics.widthPixels;
            final BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeResource(getResources(), lastsource, options);

            // Calculate inSampleSize
            options.inSampleSize = calculateInSampleSize(options, width, height);

            // Decode bitmap with inSampleSize set
            options.inJustDecodeBounds = false;
            return BitmapFactory.decodeResource(getResources(), lastsource, options);


//
//            Bitmap bitmap= BitmapFactory.decodeResource(getResources(), lastsource);
//
//            float ratio=Math.max(width/(float)bitmap.getWidth(),height/(float)bitmap.getHeight());
//            Log.d("Compl",""+ratio);
//            if(ratio>=1)
//                return bitmap;
//            else if(ratio<=0)
//                return  Bitmap.createScaledBitmap(bitmap,(int)(bitmap.getWidth()*0.1),(int)(bitmap.getHeight()*0.1),false);
//
//            return  Bitmap.createScaledBitmap(bitmap,(int)(bitmap.getWidth()*ratio),(int)(bitmap.getHeight()*ratio),false);
        } catch (Exception e) {

            e.printStackTrace();
            return null;
        }

    }

    public int calculateInSampleSize(
            BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) >= reqHeight
                    && (halfWidth / inSampleSize) >= reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }


    int lastcolor;

    public void openColorPicker(View view) {
        if (isErasing) {
            isErasing = false;
            findViewById(R.id.eraser).setBackgroundColor(Color.WHITE);
        }
        new ColorPickerPopup.Builder(this)
                .initialColor(lastcolor) // Set initial color
                .showIndicator(true)
                .showValue(true)
                .build()
                .show(view, new ColorPickerPopup.ColorPickerObserver() {
                    @Override
                    public void onColorPicked(int color, String hex) {
                        if (color != Color.BLACK) {
                            lastcolor = color;
                            adapter.notifyDataSetChanged();

                        }
                    }

                });
    }

    boolean isErasing;

    public void startErasing(View view) {
        isErasing = !isErasing;
        if (isErasing) {
            view.setBackgroundColor(Color.GRAY);
        } else {
            view.setBackgroundColor(Color.WHITE);
        }
    }

    public void clearColoring(View view) {
        if (isErasing) {
            isErasing = false;
            findViewById(R.id.eraser).setBackgroundColor(Color.WHITE);
        }
        initView();

    }

    public void onSubmitColoring(View view) {
        if (!hasColor) {
            if (language.equals("1"))
                new AlertClass(MainActivity.this, "Login", MainActivity.this, "Incorrect!",
                        "please complete the activity.", 3);
            else
                new AlertClass(MainActivity.this, "", MainActivity.this, "தவறு!",
                        "தயவுசெய்து செயல்பாட்டை முடிக்கவும்.", 3);

        } else {
            if (selectedClass.equalsIgnoreCase("LKG & UKG")) {
                selectedClass = pref.getPreference("regClass");
            }
            if (pref.getPreference("regClass").equalsIgnoreCase(selectedClass)) {
                saveScore();
            } else {
                if (language.equals("1"))
                    new AlertClass(MainActivity.this, "Two", MainActivity.this, "Fantastic!",
                            "You have successfully completed this activity.", 4);
                else
                    new AlertClass(MainActivity.this, "Two Start", MainActivity.this, "மிக அருமை!",
                            "நீங்கள் இந்த செயல்பாட்டினை வெற்றிகரமாக முடித்து விட்டீர்கள்.", 4);
            }
        }
    }

    private void saveScore() {
        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
        String formattedDate = df.format(c);

        JsonObject params = new JsonObject();
        params.addProperty("user_id", pref.getPreference("id"));
        params.addProperty("date", formattedDate);
        params.addProperty("medium", (language.equals("0") ? "Tamil" : "English"));
        params.addProperty("class", selectedClass);
        params.addProperty("session", title);
        params.addProperty("activity_name", actName);
        params.addProperty("score", 2);

        Call<SaveScore> call = RetrofitClient.getInstance().getMyApi().saveScore(params);
        call.enqueue(new Callback<SaveScore>() {
            @Override
            public void onResponse(Call<SaveScore> call, Response<SaveScore> response) {
                SaveScore r = response.body();
                if (r.getStatus() == 200) {
                    if (language.equals("1"))
                        new AlertClass(MainActivity.this, "Two", MainActivity.this, "Fantastic!",
                                "You have successfully completed this activity.", 4);
                    else
                        new AlertClass(MainActivity.this, "Two Start", MainActivity.this, "மிக அருமை!",
                                "நீங்கள் இந்த செயல்பாட்டினை வெற்றிகரமாக முடித்து விட்டீர்கள்.", 4);
                } else
                    new AlertClass(MainActivity.this, "Login", MainActivity.this, "Failed", r.getMessage(), 1);
            }

            @Override
            public void onFailure(Call<SaveScore> call, Throwable t) {
                Log.i(getClass().getName(), "=================error   " + t.getMessage());
                new AlertClass(MainActivity.this, "Login", MainActivity.this, "Failed", t.getMessage(), 1);
            }
        });
    }

    private class ColorAdapter extends RecyclerView.Adapter<ColorAdapter.Holder> {
        ArrayList<String> colors = new ArrayList<>();

        ColorAdapter() {
            String colorss =
                    "#d4814b\n" +
                            "#f2753d\n" +
                            "#d14338\n" +
                            "#a44754\n" +
                            "#e5539b\n" +
                            "#f3d161\n" +
                            "#e6e209\n" +
                            "#a8d743\n" +
                            "#3c7b61\n" +
                            "#419ed8\n" +
                            "#3685f7\n" +
                            "#596ca1\n" +
                            "#72448c\n" +
                            "#78a1be\n" +
                            "#9c96ba\n" +
                            "#6f6c74";

            for (String col : colorss.split("\n"))
                colors.add(col);

//            colors.add("D81EC6");
//            colors.add("D81E1E");
//            colors.add("6B1ED8");
//            colors.add("1E3DD8");
//            colors.add("1EB3D8");
//            colors.add("1ED881");
//            colors.add("46D81E");
//            colors.add("D8CF1E");
//            colors.add("D8941E");
//            colors.add("A54141");
//            colors.add("A56941");
//            colors.add("A59B41");
//            colors.add("5CA541");
//            colors.add("41A58E");
//            colors.add("4166A5");
//            colors.add("6741A5");
//            colors.add("A5419B");

        }

        @NonNull
        @Override
        public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new Holder(getLayoutInflater().inflate(R.layout.item_color, parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull Holder holder, int position) {
            final int color = Color.parseColor(colors.get(position));
            ((CardView) holder.itemView).setCardBackgroundColor(color);
            holder.view.setVisibility(lastcolor == color ? View.VISIBLE : View.GONE);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    lastcolor = color;
                    notifyDataSetChanged();
                }
            });

        }

        @Override
        public int getItemCount() {
            return colors.size();
        }

        class Holder extends RecyclerView.ViewHolder {

            ImageView view;

            public Holder(@NonNull View itemView) {
                super(itemView);
                view = itemView.findViewById(R.id.tick);
            }
        }
    }
}