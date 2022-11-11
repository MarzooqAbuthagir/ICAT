package com.maria.pastelhub.activity.choose;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.maria.pastelhub.R;
import com.maria.pastelhub.SingleTon;
import com.maria.pastelhub.activity.BaseActivityExtends;
import com.maria.pastelhub.activity.choose.viewmodel.ChooseAnswerViewModel;
import com.maria.pastelhub.activity.colordrawing.MainActivity;
import com.maria.pastelhub.activity.lineconnector.LineConnector;
import com.maria.pastelhub.alert.AlertClass;
import com.maria.pastelhub.api.RetrofitClient;
import com.maria.pastelhub.api.SaveScore;
import com.maria.pastelhub.emptystatus.EmptyStatus;
import com.maria.pastelhub.networkstatus.NetworkStatusFinder;
import com.maria.pastelhub.prefference.Pref;
import com.maria.pastelhub.services.MusicService;
import com.google.gson.JsonObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChooseAnswerActivity extends AppCompatActivity implements View.OnClickListener {

    NetworkStatusFinder networkStatusFinder;
    String language = "";

    @BindView(R.id.back_major_header)
    ImageView back_major_header;

    @BindView(R.id.major_title)
    TextView major_title;

    String selectedClass = "", title = "", actName = "";
    Pref pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_answer);
        pref = new Pref(ChooseAnswerActivity.this);
        networkStatus();
        ButterKnife.bind(this);
        clickEvent();
        if (getIntent().getStringExtra("language").equals("1"))
            major_title.setText(R.string.choose_the_correct_ans);
        else
            major_title.setText(R.string.choose_tamil);
        language = getIntent().getStringExtra("language");
        title = getIntent().getStringExtra("title");
        selectedClass = getIntent().getStringExtra("selectedClass");
        actName = getIntent().getStringExtra("actName");
        System.out.println("value--------------" + language);
        System.out.println("value--------------" + selectedClass);
        System.out.println("value--------------" + title);
        System.out.println("value--------------" + actName);
        setRecyclerviewData(getIntent().getIntExtra("Position", 0), getIntent().getIntExtra("class", 0));

    }

    private void setRecyclerviewData(int position, int classn) {
        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new ChooseAnswerAdapter(SingleTon.getChooseAnswerData(this, classn, position, language)));
    }

    @Override
    protected void onResume() {
        super.onResume();
        SingleTon.setListener(this, findViewById(R.id.sound_icon));
    }


    private void networkStatus() {

        networkStatusFinder = new NetworkStatusFinder();

        if (!networkStatusFinder.networkStatus(ChooseAnswerActivity.this)) {
            Intent intent = new Intent(ChooseAnswerActivity.this, EmptyStatus.class);
            intent.putExtra("status", "no_net");
            startActivity(intent);
            finish();
        }

    }

    private void clickEvent() {
        back_major_header.setOnClickListener(this);

    }

    @Override
    public void onBackPressed() {
        backClicked();
    }

    private void backClicked() {
        finish();
        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.back_major_header) {
            backClicked();
        }
    }


    int completed = 0;

    private class ChooseAnswerAdapter extends RecyclerView.Adapter<ChooseAnswerAdapter.Holder> {

        int[] bg = {R.drawable.choose_content1, R.drawable.choose_content2, R.drawable.choose_content3, R.drawable.choose_content4,
                R.drawable.choose_content5,
                R.drawable.choose_content6,
                R.drawable.choose_content7, R.drawable.choose_content8, R.drawable.choose_content9, R.drawable.choose_content10};
        int[] qbg = {R.drawable.top_corner_blue, R.drawable.top_corner_orange, R.drawable.top_corner_red, R.drawable.top_corner_green,
                R.drawable.top_corner_purple,
                R.drawable.top_corner_orange
                , R.drawable.top_corner_dark_blue, R.drawable.top_corner_brown, R.drawable.top_corner_dark_green, R.drawable.top_corner_pink};

        @NonNull
        @Override
        public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new Holder(getLayoutInflater().inflate(R.layout.item_chooseanswer, parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull Holder holder, int position) {
            QAModel model = models.get(position);
            holder.question.setText(model.question);
            holder.choice1.setText(model.choice1);
            holder.choice2.setText(model.choice2);
            holder.choice3.setText(model.choice3);
            holder.choice4.setText(model.choice4);
            ((View) holder.question.getParent()).setBackgroundResource(qbg[position % qbg.length]);
            holder.itemView.setBackgroundResource(bg[position % bg.length]);
            int correct = model.anspos;
            holder.id.setText(String.format("%s)", String.valueOf(position + 1)));
            Pref pref = new Pref(getApplicationContext());
            holder.c1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (correct != model.selectedpos)
                        if (correct == 0) {
                            completed++;
                            model.selectedpos = 0;
                            notifyDataSetChanged();
                            Log.i(getClass().getName(), "1===================" + completed + "    " + getItemCount());
                            if (completed == getItemCount())
                                callAlert((pref.getLesson()));

                        } else {
                            if (language.equals("1"))
                                new AlertClass(ChooseAnswerActivity.this, "Login", ChooseAnswerActivity.this, "Incorrect!", "Choose the correct answer.", 3);
                            else
                                new AlertClass(ChooseAnswerActivity.this, "", ChooseAnswerActivity.this, "தவறு!", "சரியான விடையைத் தேர்ந்தெடுக்கவும்.", 3);
                        }

                }
            });

            holder.c2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (correct != model.selectedpos)
                        if (correct == 1) {
                            completed++;
                            model.selectedpos = 1;
                            notifyDataSetChanged();
                            if (completed == getItemCount())
                                callAlert(pref.getLesson());
                        } else {
                            if (language.equals("1"))
                                new AlertClass(ChooseAnswerActivity.this, "Login", ChooseAnswerActivity.this, "Incorrect!", "Choose the correct answer.", 3);
                            else
                                new AlertClass(ChooseAnswerActivity.this, "", ChooseAnswerActivity.this, "தவறு!", "சரியான விடையைத் தேர்ந்தெடுக்கவும்.", 3);
                        }
                }
            });
            holder.c3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (correct != model.selectedpos)

                        if (correct == 2) {
                            completed++;
                            model.selectedpos = 2;
                            notifyDataSetChanged();
                            if (completed == getItemCount())
                                callAlert((pref.getLesson()));
                        } else {
                            if (language.equals("1"))
                                new AlertClass(ChooseAnswerActivity.this, "Login", ChooseAnswerActivity.this, "Incorrect!", "Choose the correct answer.", 3);
                            else
                                new AlertClass(ChooseAnswerActivity.this, "", ChooseAnswerActivity.this, "தவறு!", "சரியான விடையைத் தேர்ந்தெடுக்கவும்.", 3);
                        }


                }
            });
            holder.c4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (correct != model.selectedpos)
                        if (correct == 3) {
                            completed++;
                            model.selectedpos = 3;
                            notifyDataSetChanged();

                            if (completed == getItemCount())
                                callAlert((pref.getLesson()));
                        } else {
                            if (language.equals("1"))
                                new AlertClass(ChooseAnswerActivity.this, "Login", ChooseAnswerActivity.this, "Incorrect!", "Choose the correct answer.", 3);
                            else
                                new AlertClass(ChooseAnswerActivity.this, "", ChooseAnswerActivity.this, "தவறு!", "சரியான விடையைத் தேர்ந்தெடுக்கவும்.", 3);
                        }

                }
            });
            holder.c1.setImageResource(correct == model.selectedpos && correct == 0 ? R.drawable.tick : R.drawable.circle);
            holder.c2.setImageResource(correct == model.selectedpos && correct == 1 ? R.drawable.tick : R.drawable.circle);
            holder.c3.setImageResource(correct == model.selectedpos && correct == 2 ? R.drawable.tick : R.drawable.circle);

            holder.c4.setImageResource(correct == model.selectedpos && correct == 3 ? R.drawable.tick : R.drawable.circle);
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
                            new AlertClass(ChooseAnswerActivity.this, "Two", ChooseAnswerActivity.this, "Fantastic!",
                                    "You have successfully completed this activity.", 4);
                        else
                            new AlertClass(ChooseAnswerActivity.this, "Two Start", ChooseAnswerActivity.this, "மிக அருமை!",
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
                            new AlertClass(ChooseAnswerActivity.this, "Two", ChooseAnswerActivity.this, "Fantastic!",
                                    "You have successfully completed this activity.", 4);
                        else
                            new AlertClass(ChooseAnswerActivity.this, "Two Start", ChooseAnswerActivity.this, "மிக அருமை!",
                                    "நீங்கள் இந்த செயல்பாட்டினை வெற்றிகரமாக முடித்து விட்டீர்கள்.", 4);
                    } else
                        new AlertClass(ChooseAnswerActivity.this, "Login", ChooseAnswerActivity.this, "Failed", r.getMessage(), 1);
                }

                @Override
                public void onFailure(Call<SaveScore> call, Throwable t) {
                    Log.i(getClass().getName(), "=================error   " + t.getMessage());
                    new AlertClass(ChooseAnswerActivity.this, "Login", ChooseAnswerActivity.this, "Failed", t.getMessage(), 1);
                }
            });
        }

        @Override
        public int getItemCount() {
            return models.size();
        }

        class Holder extends RecyclerView.ViewHolder {

            TextView question, choice1, choice2, choice3, choice4, id;
            ImageView c1, c2, c3, c4;

            public Holder(@NonNull View itemView) {
                super(itemView);
                id = itemView.findViewById(R.id.id);
                question = itemView.findViewById(R.id.question);
                c1 = itemView.findViewById(R.id.option1);
                choice1 = itemView.findViewById(R.id.option1_tv);

                c2 = itemView.findViewById(R.id.option2);
                choice2 = itemView.findViewById(R.id.option2_tv);
                c3 = itemView.findViewById(R.id.option3);
                choice3 = itemView.findViewById(R.id.option3_tv);
                c4 = itemView.findViewById(R.id.option4);
                choice4 = itemView.findViewById(R.id.option4_tv);

            }
        }

        ArrayList<QAModel> models;

        public ChooseAnswerAdapter(ArrayList<QAModel> chooseAnswerData) {
            models = chooseAnswerData;
            completed = 0;

        }
    }
}