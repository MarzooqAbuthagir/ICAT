package com.maria.pastelhub.activity.wordsearch;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.maria.pastelhub.R;
import com.maria.pastelhub.SingleTon;
import com.maria.pastelhub.alert.AlertClass;
import com.maria.pastelhub.api.RetrofitClient;
import com.maria.pastelhub.api.SaveScore;
import com.maria.pastelhub.emptystatus.EmptyStatus;
import com.maria.pastelhub.networkstatus.NetworkStatusFinder;
import com.maria.pastelhub.prefference.Pref;
import com.google.gson.JsonObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WordSearch extends AppCompatActivity implements View.OnClickListener {

    NetworkStatusFinder networkStatusFinder;

    @BindView(R.id.back_major_header)
    ImageView back_major_header;

    @BindView(R.id.hint)
    Button hint;

    @BindView(R.id.major_title)
    TextView major_title;
    String hints;
    String language = "";


    TextView hint_tv;
    String words;
    int gridsize;
    String selectedClass = "", title = "", actName = "";
    Pref pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_word_search);
            pref = new Pref(WordSearch.this);

            networkStatus();

            ButterKnife.bind(this);

            clickEvent();


            hint_tv = findViewById(R.id.datadisplay);
            language = getIntent().getStringExtra("language");
            title = getIntent().getStringExtra("title");
            selectedClass = getIntent().getStringExtra("selectedClass");
            actName = getIntent().getStringExtra("actName");
            System.out.println("value--------------" + language);
            System.out.println("value--------------" + selectedClass);
            System.out.println("value--------------" + title);
            System.out.println("value--------------" + actName);
            if (language.equals("1")) {
                major_title.setText(R.string.prayer_hunt);
            } else major_title.setText(R.string.word_search_tamil);

            Log.i("===============   ", language);
            Object[] arr = SingleTon.getWordSearchData(this, getIntent().getIntExtra("class", 0), getIntent().getIntExtra("Position", 0), language);
            hints = (String) arr[0];
            if (arr.length == 6 && (arr[5] != null)) {
                SpannableStringBuilder string = new SpannableStringBuilder((String) arr[5]);
                int start = 0, end = 0;
                int max = ((String) arr[5]).split("<>").length;
                while (max > 0) {
                    max--;
                    try {
                        start = string.toString().indexOf("<>");
                        end = string.toString().indexOf("</>");
                        string.setSpan(new ForegroundColorSpan(Color.RED), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        string.delete(start, start + 2);
                        string.delete(end - 2, end + 1);
                    } catch (Exception e) {
                    }
                }
                max = ((String) arr[5]).split("break").length;
                while (max > 0) {
                    max--;
                    try {
                        start = string.toString().indexOf("break");
                        string.replace(start, start + 5, "\n");
                    } catch (Exception e) {
                    }
                }
                hint_tv.setText(string);
            } else
                hint_tv.setText(hints);
            words = (String) arr[1];
            RecyclerView recyclerView = findViewById(R.id.recyclerview);
            recyclerView.setNestedScrollingEnabled(false);
            recyclerView.setLayoutManager(new GridLayoutManager(this, gridsize = (Integer) arr[3]));
            recyclerView.setAdapter(new WordSearchAdapter((String[]) arr[2], (String) arr[4]));
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Something Went wrong", Toast.LENGTH_SHORT).show();
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

    private void clickEvent() {

        back_major_header.setOnClickListener(this);
        hint.setOnClickListener(this);
    }

    private void networkStatus() {

        networkStatusFinder = new NetworkStatusFinder();

        if (!networkStatusFinder.networkStatus(WordSearch.this)) {
            Intent intent = new Intent(WordSearch.this, EmptyStatus.class);
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

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.back_major_header) {
            onBackPressed();
        }
    }


    private class WordSearchAdapter extends RecyclerView.Adapter {
        int currentclicked = -1, currentprogress = 0;
        String[] letters;
        ArrayList<Integer> clickedpositions = new ArrayList<>();
        ArrayList<String> matchletters = new ArrayList<>();
        ArrayList<Integer> breaks;

        public WordSearchAdapter(String[] strings, String a) {
            letters = strings;
            breaks = new ArrayList<Integer>();
            for (String s : a.split(","))
                if (s != null && !s.isEmpty())
                    breaks.add(Integer.parseInt(s));
            try {
                Pattern r = Pattern.compile("\\p{L}\\p{M}*");
                Matcher m = r.matcher(words);
                while (m.find()) {
                    matchletters.add(m.group());
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }


        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new RecyclerView.ViewHolder(getLayoutInflater().inflate(R.layout.item_wordsearch_letter, parent, false)) {
                @Override
                public String toString() {
                    return super.toString();
                }
            };


        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
            ((TextView) holder.itemView).setText(letters[position]);
            holder.itemView.setBackgroundResource(clickedpositions.contains(position) ? R.drawable.wordsearch_selected : R.drawable.wordsearch_default);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    System.out.println("value " + matchletters.get(currentprogress) + " and " + letters[position]);
                    if (currentclicked == position) {
                        currentprogress--;
                        clickedpositions.remove((Integer) position);
                        if (clickedpositions.size() > 0)
                            currentclicked = clickedpositions.get(clickedpositions.size() - 1);
                        else
                            currentclicked = -1;
                        notifyDataSetChanged();
                    } else if (matchletters.get(currentprogress).trim().equals(letters[position].trim()) && (
                            currentclicked == -1 ||
                                    breaks.contains(currentprogress) ||
                                    (currentclicked - gridsize >= 0 && position == currentclicked - gridsize) ||
                                    (currentclicked + gridsize < getItemCount() && position == currentclicked + gridsize) ||
                                    (((currentclicked % gridsize) != 0) && position == currentclicked - 1) ||
                                    (((currentclicked % gridsize) != gridsize - 1) && position == currentclicked + 1)

                    )) {
                        clickedpositions.add(position);
                        currentclicked = position;
                        currentprogress++;
                        Log.d("size", currentprogress + " " + matchletters.size());
                        if (currentprogress == matchletters.size()) {
                            if (selectedClass.equalsIgnoreCase("LKG & UKG")) {
                                selectedClass = pref.getPreference("regClass");
                            }
                            if (pref.getPreference("regClass").equalsIgnoreCase(selectedClass)) {
                                saveScore();
                            } else {
                                if (language.equals("1"))
                                    new AlertClass(WordSearch.this, "Two", WordSearch.this, "Fantastic!",
                                            "You have successfully completed this activity.", 4);
                                else
                                    new AlertClass(WordSearch.this, "Two Start", WordSearch.this, "மிக அருமை!",
                                            "நீங்கள் இந்த செயல்பாட்டினை வெற்றிகரமாக முடித்து விட்டீர்கள்.", 4);
                            }
                        }

                        notifyDataSetChanged();

                    } else if (clickedpositions.contains(position))
                        return;
                    else {
                        System.out.println(breaks);
                        Log.d("click", "" + matchletters.get(currentprogress) + " " + letters[position] + " " + breaks.contains(currentprogress)
                        );
                        if (language.equals("1"))
                            new AlertClass(WordSearch.this, "Login", WordSearch.this, "Incorrect!", "Choose the correct answer.", 3);
                        else
                            new AlertClass(WordSearch.this, "", WordSearch.this, "தவறு!", "சரியான விடையைத் தேர்ந்தெடுக்கவும்.", 3);

                    }

                }
            });
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
                            new AlertClass(WordSearch.this, "Two", WordSearch.this, "Fantastic!",
                                    "You have successfully completed this activity.", 4);
                        else
                            new AlertClass(WordSearch.this, "Two Start", WordSearch.this, "மிக அருமை!",
                                    "நீங்கள் இந்த செயல்பாட்டினை வெற்றிகரமாக முடித்து விட்டீர்கள்.", 4);
                    } else
                        new AlertClass(WordSearch.this, "Login", WordSearch.this, "Failed", r.getMessage(), 1);
                }

                @Override
                public void onFailure(Call<SaveScore> call, Throwable t) {
                    Log.i(getClass().getName(), "=================error   " + t.getMessage());
                    new AlertClass(WordSearch.this, "Login", WordSearch.this, "Failed", t.getMessage(), 1);
                }
            });
        }

        @Override
        public int getItemCount() {
            return letters.length;
        }
    }


}