package com.maria.pastelhub.activity.draganddrop;

import android.app.Activity;
import android.content.ClipData;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.DragEvent;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.beloo.widget.chipslayoutmanager.ChipsLayoutManager;
import com.beloo.widget.chipslayoutmanager.gravity.IChildGravityResolver;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.maria.pastelhub.R;
import com.maria.pastelhub.SingleTon;
import com.maria.pastelhub.activity.colordrawing.MainActivity;
import com.maria.pastelhub.activity.match.LkgMatchActivtiy;
import com.maria.pastelhub.alert.AlertClass;
import com.maria.pastelhub.api.RetrofitClient;
import com.maria.pastelhub.api.SaveScore;
import com.maria.pastelhub.emptystatus.EmptyStatus;
import com.maria.pastelhub.networkstatus.NetworkStatusFinder;
import com.maria.pastelhub.prefference.Pref;
import com.google.gson.JsonObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@RequiresApi(api = Build.VERSION_CODES.M)
public class DragandDroplu extends AppCompatActivity implements View.OnClickListener {


    NetworkStatusFinder networkStatusFinder;

    @BindView(R.id.back_major_header)
    ImageView back_major_header;

    @BindView(R.id.major_title)
    TextView major_title;

    String language = "";
    List<String> img = new ArrayList<>();
    Pref pref;
    String selectedClass ="", title ="", actName ="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dragand_drop);
        pref = new Pref(this);
        networkStatus();

        ButterKnife.bind(this);
        int classnum = getIntent().getIntExtra("class", 0);
        clickEvent();
        if (classnum == 0) {
            if (pref.getLesson().equalsIgnoreCase("lesson1")) {
                img.add("LKG/jumble_words/lesson1/Arrange the Jumbled_1_1_1.png");
                img.add("LKG/jumble_words/lesson1/Arrange the Jumbled_1_1_2.png");
                img.add("LKG/jumble_words/lesson1/Arrange the Jumbled_1_1_3.png");
            } else if (pref.getLesson().equalsIgnoreCase("lesson2")) {
                img.add("LKG/jumble_words/lesson2/Arrange the Jumbled_1_2_1.png");
                img.add("LKG/jumble_words/lesson2/Arrange the Jumbled_1_2_2.png");
                img.add("LKG/jumble_words/lesson2/Arrange the Jumbled_1_2_3.png");
            } else if (pref.getLesson().equalsIgnoreCase("lesson3")) {
                img.add("LKG/jumble_words/lesson3/Arrange the Jumbled_1_3_1.png");
                img.add("LKG/jumble_words/lesson3/Arrange the Jumbled_1_3_2.png");
                img.add("LKG/jumble_words/lesson3/Arrange the Jumbled_1_3_3.png");
            } else if (pref.getLesson().equalsIgnoreCase("lesson4")) {
                img.add("LKG/jumble_words/lesson4/Arrange the Jumbled_1_4_1.png");
                img.add("LKG/jumble_words/lesson4/Arrange the Jumbled_1_4_2.png");
                img.add("LKG/jumble_words/lesson4/Arrange the Jumbled_1_4_3.png");
            } else if (pref.getLesson().equalsIgnoreCase("lesson5")) {
                img.add("LKG/jumble_words/lesson5/Arrange the Jumbled_1_5_1.png");
                img.add("LKG/jumble_words/lesson5/Arrange the Jumbled_1_5_2.png");
                img.add("LKG/jumble_words/lesson5/Arrange the Jumbled_1_5_3.png");
            } else if (pref.getLesson().equalsIgnoreCase("lesson6")) {
                img.add("LKG/jumble_words/lesson6/Arrange the Jumbled_1_6_1.png");
                img.add("LKG/jumble_words/lesson6/Arrange the Jumbled_1_6_2.png");
                img.add("LKG/jumble_words/lesson6/Arrange the Jumbled_1_6_3.png");
            } else if (pref.getLesson().equalsIgnoreCase("lesson7")) {
                img.add("LKG/jumble_words/lesson7/Arrange the Jumbled_1_7_1.png");
                img.add("LKG/jumble_words/lesson7/Arrange the Jumbled_1_7_2.png");
                img.add("LKG/jumble_words/lesson7/Arrange the Jumbled_1_7_3.png");
                img.add("LKG/jumble_words/lesson7/Arrange the Jumbled_1_7_4.png");
            } else if (pref.getLesson().equalsIgnoreCase("lesson8")) {
                img.add("LKG/jumble_words/lesson8/Arrange the Jumbled_1_8_1.png");
                img.add("LKG/jumble_words/lesson8/Arrange the Jumbled_1_8_2.png");
                img.add("LKG/jumble_words/lesson8/Arrange the Jumbled_1_8_3.png");
                img.add("LKG/jumble_words/lesson8/Arrange the Jumbled_1_8_4.png");
            }
        } else {
            if (pref.getLesson().equalsIgnoreCase("lesson1")) {
                img.add("UKG/jumble_words/lesson1/Arrange the Jumbled_1_1.png");
                img.add("UKG/jumble_words/lesson1/Arrange the Jumbled_1_2.png");
                img.add("UKG/jumble_words/lesson1/Arrange the Jumbled_1_3.png");
                img.add("UKG/jumble_words/lesson1/Arrange the Jumbled_1_4.png");
                img.add("UKG/jumble_words/lesson1/Arrange the Jumbled_1_5.png");
                img.add("UKG/jumble_words/lesson1/Arrange the Jumbled_1_6.png");
            } else if (pref.getLesson().equalsIgnoreCase("lesson2")) {
                img.add("UKG/jumble_words/lesson2/Arrange the Jumbled_2_1.png");
                img.add("UKG/jumble_words/lesson2/Arrange the Jumbled_2_2.png");
                img.add("UKG/jumble_words/lesson2/Arrange the Jumbled_2_3.png");
                img.add("UKG/jumble_words/lesson2/Arrange the Jumbled_2_4.png");
                img.add("UKG/jumble_words/lesson2/Arrange the Jumbled_2_5.png");
                img.add("UKG/jumble_words/lesson2/Arrange the Jumbled_2_6.png");
            } else if (pref.getLesson().equalsIgnoreCase("lesson3")) {
                img.add("UKG/jumble_words/lesson3/Arrange the Jumbled_3_1.png");
                img.add("UKG/jumble_words/lesson3/Arrange the Jumbled_3_2.png");
                img.add("UKG/jumble_words/lesson3/Arrange the Jumbled_3_3.png");
                img.add("UKG/jumble_words/lesson3/Arrange the Jumbled_3_4.png");
                img.add("UKG/jumble_words/lesson3/Arrange the Jumbled_3_5.png");
                img.add("UKG/jumble_words/lesson3/Arrange the Jumbled_3_6.png");
            } else if (pref.getLesson().equalsIgnoreCase("lesson4")) {
                img.add("UKG/jumble_words/lesson4/Arrange the Jumbled_4_1.png");
                img.add("UKG/jumble_words/lesson4/Arrange the Jumbled_4_2.png");
                img.add("UKG/jumble_words/lesson4/Arrange the Jumbled_4_3.png");
                img.add("UKG/jumble_words/lesson4/Arrange the Jumbled_4_4.png");
                img.add("UKG/jumble_words/lesson4/Arrange the Jumbled_4_5.png");
                img.add("UKG/jumble_words/lesson4/Arrange the Jumbled_4_6.png");
            } else if (pref.getLesson().equalsIgnoreCase("lesson5")) {
                img.add("UKG/jumble_words/lesson5/Arrange the Jumbled_5_1.png");
                img.add("UKG/jumble_words/lesson5/Arrange the Jumbled_5_2.png");
                img.add("UKG/jumble_words/lesson5/Arrange the Jumbled_5_3.png");
                img.add("UKG/jumble_words/lesson5/Arrange the Jumbled_5_4.png");
                img.add("UKG/jumble_words/lesson5/Arrange the Jumbled_5_5.png");
                img.add("UKG/jumble_words/lesson5/Arrange the Jumbled_5_6.png");
            } else if (pref.getLesson().equalsIgnoreCase("lesson6")) {
                img.add("UKG/jumble_words/lesson6/Arrange the Jumbled_6_1.png");
                img.add("UKG/jumble_words/lesson6/Arrange the Jumbled_6_2.png");
                img.add("UKG/jumble_words/lesson6/Arrange the Jumbled_6_3.png");
                img.add("UKG/jumble_words/lesson6/Arrange the Jumbled_6_4.png");
                img.add("UKG/jumble_words/lesson6/Arrange the Jumbled_6_5.png");
                img.add("UKG/jumble_words/lesson6/Arrange the Jumbled_6_6.png");
            } else if (pref.getLesson().equalsIgnoreCase("lesson7")) {
                img.add("UKG/jumble_words/lesson7/Arrange the Jumbled_7_1.png");
                img.add("UKG/jumble_words/lesson7/Arrange the Jumbled_7_2.png");
                img.add("UKG/jumble_words/lesson7/Arrange the Jumbled_7_3.png");
                img.add("UKG/jumble_words/lesson7/Arrange the Jumbled_7_4.png");
                img.add("UKG/jumble_words/lesson7/Arrange the Jumbled_7_5.png");
                img.add("UKG/jumble_words/lesson7/Arrange the Jumbled_7_6.png");
            } else if (pref.getLesson().equalsIgnoreCase("lesson8")) {
                img.add("UKG/jumble_words/lesson8/Arrange the Jumbled_8_1.png");
                img.add("UKG/jumble_words/lesson8/Arrange the Jumbled_8_2.png");
                img.add("UKG/jumble_words/lesson8/Arrange the Jumbled_8_3.png");
                img.add("UKG/jumble_words/lesson8/Arrange the Jumbled_8_4.png");
                img.add("UKG/jumble_words/lesson8/Arrange the Jumbled_8_5.png");
            }
        }

        if (getIntent().getStringExtra("language").equals("1"))
            major_title.setText("Arrange the Jumbled Bible names");
        else
            major_title.setText(R.string.drag_drop_tamil);

        language = getIntent().getStringExtra("language");
        title = getIntent().getStringExtra("title");
        selectedClass = getIntent().getStringExtra("selectedClass");
        actName = getIntent().getStringExtra("actName");
        System.out.println("value--------------"+ language);
        System.out.println("value--------------"+ selectedClass);
        System.out.println("value--------------"+ title);
        System.out.println("value--------------"+ actName);

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new DragandDropAdapter(SingleTon.getDragWordsData(this, getIntent().getIntExtra("class", 0), getIntent().getIntExtra("Position", 0), language), img));
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
    private void clickEvent() {
        back_major_header.setOnClickListener(this);


    }

    private void networkStatus() {

        networkStatusFinder = new NetworkStatusFinder();

        if (!networkStatusFinder.networkStatus(DragandDroplu.this)) {
            Intent intent = new Intent(DragandDroplu.this, EmptyStatus.class);
            intent.putExtra("status", "no_net");
            startActivity(intent);
            finish();
        }

    }

    @Override
    public void onBackPressed() {
        finish();
        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.back_major_header) {
            onBackPressed();
        }
    }

    private class DragandDropAdapter extends RecyclerView.Adapter<DragandDropAdapter.Holder> {
        boolean[] completed;
        int[] progress;
        String[] colors = {"#C5E3E1", "#B6C6DF", "#E3CBDA"};
        int[] borders = {R.drawable.input_border_1, R.drawable.input_border_2, R.drawable.input_border_3};

        @NonNull
        @Override
        public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new Holder(getLayoutInflater().inflate(R.layout.item_draganddrop_l_u, parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull Holder holder, int position) {
            Object[] item = contents.get(position);
            holder.footer.setVisibility(View.INVISIBLE);
            String imgn = img.get(position);
            Glide.with(getApplicationContext())
                    .load(("http://157.245.111.98/plesk-site-preview/icatapp-tnbclc.in/https/157.245.111.98/maria_backend/uploads/" + imgn))
                    .into(holder.ivJw);
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < progress[position]; i++)
                builder.append(((String[]) item[0])[i]);
            holder.boxview.setText(builder.toString());
            ((CardView) holder.itemView).setCardBackgroundColor(Color.parseColor(colors[position % colors.length]));
            holder.boxview.setBackgroundResource(borders[position % borders.length]);
            ChipsLayoutManager chipsLayoutManager = ChipsLayoutManager.newBuilder(DragandDroplu.this)
                    .setChildGravity(Gravity.CENTER)
                    .setScrollingEnabled(false)
                    .setMaxViewsInRow(3)
                    .setGravityResolver(new IChildGravityResolver() {
                        @Override
                        public int getItemGravity(int position) {
                            return Gravity.CENTER;
                        }
                    })
//                    //you are able to break row due to your conditions. Row breaker should return true for that views
//                    .setRowBreaker(new IRowBreaker() {
//                        @Override
//                        public boolean isItemBreakRow(@IntRange(from = 0) int position) {
//                            return position == 6 || position == 11 || position == 2;
//                        }
//                    })
                    .setOrientation(ChipsLayoutManager.HORIZONTAL)
                    .setRowStrategy(ChipsLayoutManager.STRATEGY_CENTER)
                    .withLastRow(true)
                    .build();
            holder.contentview.setLayoutManager(chipsLayoutManager);
            holder.contentview.setNestedScrollingEnabled(false);
            String[] content = (String[]) item[0];
            Log.i(getClass().getName(), "======================   " + content.length);
            holder.contentview.setAdapter(new DragandDropItenAdapter(Arrays.copyOfRange(content, progress[position], content.length), position));
            holder.boxview.setOnDragListener((v, event) -> {
                int dragEvent = event.getAction();
                if (dragEvent == DragEvent.ACTION_DROP) {
                    String original = ((String[]) item[0])[progress[position]].trim().replaceAll(",", "").replaceAll("\\.", "");
                    String dragged = event.getClipData().getDescription().getLabel().toString();
                    Log.i(getClass().getName(), "   " + original + "\n" + dragged);
                    if (original.equals(dragged)) {
                        progress[position] += 1;
                        if (progress[position] == content.length)
                            completed[position] = true;
                        notifyItemChanged(position);
                        checkCompleted();
                    } else {
                        if (language.equals("1"))
                            new AlertClass(DragandDroplu.this, "Login", DragandDroplu.this, "Incorrect!", "Choose the correct answer.", 3);
                        else
                            new AlertClass(DragandDroplu.this, "", DragandDroplu.this, "தவறு!", "சரியான விடையைத் தேர்ந்தெடுக்கவும்.", 3);
                    }
                }


                return true;
            });
        }

        @Override
        public int getItemCount() {
            return contents.size();
        }


        class Holder extends RecyclerView.ViewHolder {
            RecyclerView contentview;
            TextView boxview, footer;
            ImageView ivJw;

            public Holder(@NonNull View itemView) {
                super(itemView);
                contentview = itemView.findViewById(R.id.content);
                boxview = itemView.findViewById(R.id.inputbox);
                footer = itemView.findViewById(R.id.footer);
                ivJw = itemView.findViewById(R.id.ivJw);
            }
        }

        public DragandDropAdapter(ArrayList<Object[]> position, List<String> img) {
            contents = position;
            completed = new boolean[contents.size()];
            progress = new int[contents.size()];
            this.img = img;
        }

        ArrayList<Object[]> contents;
        List<String> img;

        private void checkCompleted() {
            for (boolean is : completed)
                if (!is)
                    return;
            callAlert(pref.getLesson());
        }
    }

    void callAlert(String position) {
        Log.i(getClass().getName(), "===================" + position);
        switch (position) {
            case "lesson1":
            case "lesson2":
            case "lesson3":
            case "lesson4":
            case "lesson5":
            case "lesson6":
            case "lesson7":
            case "lesson8":
            case "lesson9":
            case "lesson10":
            case "lesson11":
            case "lesson12":
                if (selectedClass.equalsIgnoreCase("LKG & UKG")) {
                    selectedClass = pref.getPreference("regClass");
                }
                if (pref.getPreference("regClass").equalsIgnoreCase(selectedClass)) {
                    saveScore();
                } else {
                    if (language.equals("1"))
                        new AlertClass(DragandDroplu.this, "Two", DragandDroplu.this, "Fantastic!",
                                "You have successfully completed this activity.", 4);
                    else
                        new AlertClass(DragandDroplu.this, "Two Start", DragandDroplu.this, "மிக அருமை!",
                                "நீங்கள் இந்த செயல்பாட்டினை வெற்றிகரமாக முடித்து விட்டீர்கள்.", 4);
                }
                break;

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
                        new AlertClass(DragandDroplu.this, "Two", DragandDroplu.this, "Fantastic!",
                                "You have successfully completed this activity.", 4);
                    else
                        new AlertClass(DragandDroplu.this, "Two Start", DragandDroplu.this, "மிக அருமை!",
                                "நீங்கள் இந்த செயல்பாட்டினை வெற்றிகரமாக முடித்து விட்டீர்கள்.", 4);
                } else
                    new AlertClass(DragandDroplu.this, "Login", DragandDroplu.this, "Failed", r.getMessage(), 1);
            }

            @Override
            public void onFailure(Call<SaveScore> call, Throwable t) {
                Log.i(getClass().getName(), "=================error   " + t.getMessage());
                new AlertClass(DragandDroplu.this, "Login", DragandDroplu.this, "Failed", t.getMessage(), 1);
            }
        });
    }

    private class DragandDropItenAdapter extends RecyclerView.Adapter<DragandDropItenAdapter.Holder> {

        List<String> shuffled;
        String[] original;
        int bg;
        int[] darks = {R.drawable.input_darkgreen_1, R.drawable.input_darkgreen_2, R.drawable.input_darkgreen_3};

        public DragandDropItenAdapter(String[] o, int position) {
            original = o;
            shuffled = Arrays.asList(o);
            Collections.shuffle(shuffled);
            bg = darks[position % darks.length];
        }

        @NonNull
        @Override
        public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new Holder(getLayoutInflater().inflate(R.layout.item_draganddrop_content_l_u, parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull Holder holder, int position) {
            holder.content.setText(shuffled.get(position).trim().replaceAll(",", "").replaceAll("\\.", "").replaceAll(";", "")
                    .replaceAll("\"", "").replaceAll("!", ""));
            holder.content.setBackgroundResource(bg);
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    ClipData data = ClipData.newPlainText(shuffled.get(position).trim().replaceAll(",", "").replaceAll("\\.", ""), "");
                    View.DragShadowBuilder shadow = new View.DragShadowBuilder(view);
                    view.startDrag(data, shadow, view, 0);
                    return false;
                }
            });
            holder.itemView.setOnDragListener((v, event) -> true);
        }

        @Override
        public int getItemCount() {
            return shuffled.size();
        }

        class Holder extends RecyclerView.ViewHolder {
            TextView content;

            public Holder(@NonNull View itemView) {
                super(itemView);
                content = itemView.findViewById(R.id.text);
            }
        }
    }
}