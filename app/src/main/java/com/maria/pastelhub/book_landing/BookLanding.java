package com.maria.pastelhub.book_landing;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.maria.pastelhub.SingleTon;
import com.maria.pastelhub.emptystatus.EmptyView;
import com.maria.pastelhub.lessons.LessonsActivity;
import com.maria.pastelhub.R;
import com.maria.pastelhub.book_landing.adapter.ReviewAdapter;
import com.maria.pastelhub.book_landing.viewmodel.BookLandingViewModel;
import com.maria.pastelhub.ebook.EBook;
import com.maria.pastelhub.emptystatus.EmptyStatus;
import com.maria.pastelhub.lessons.TamilNumberChoose;
import com.maria.pastelhub.networkstatus.NetworkStatusFinder;
import com.maria.pastelhub.prefference.Pref;
import com.maria.pastelhub.review.ReviewActivity;
import com.maria.pastelhub.videos.livevideos.LiveVideo1;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BookLanding extends AppCompatActivity implements View.OnClickListener {

    NetworkStatusFinder networkStatusFinder;

    @BindView(R.id.navigation_icon)
    ImageView navigation_icon;

    @BindView(R.id.book_landing_card)
    CardView book_landing_card;

    @BindView(R.id.toggle_details)
    CardView toggle_details;

    @BindView(R.id.toggle_details1)
    CardView toggle_details1;

    @BindView(R.id.layout_e_book)
    LinearLayout layout_e_book;

    @BindView(R.id.write_my_review)
    LinearLayout write_my_review;

    @BindView(R.id.cv_review)
    CardView cv_review;

    @BindView(R.id.layout_activity)
    LinearLayout layout_activity;

    @BindView(R.id.layout_video)
    LinearLayout layout_video;

    @BindView(R.id.layout_song)
    LinearLayout layout_song;

    @BindView(R.id.layout_web_link)
    LinearLayout layout_web_link;

    @BindView(R.id.review_content)
    LinearLayout review_content;

    @BindView(R.id.preface_content)
    LinearLayout preface_content;

    @BindView(R.id.user_review)
    TextView user_review;

    @BindView(R.id.preface)
    TextView preface;

    @BindView(R.id.review_list)
    RecyclerView review_list;

    @BindView(R.id.book_author_name)
    TextView book_author_name;

    Animation from_small, from_nothing;

    private ReviewAdapter reviewAdapter;
    private List<BookLandingViewModel> bookLandingUserList;
    Pref pref;

    int classn;
    String language = "", selectedClass = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_book_landing);

            ButterKnife.bind(this);

            review_list.setLayoutManager(new LinearLayoutManager(this));
            book_author_name.setSelected(true);

            bookLandingUserList = new ArrayList<>();
            pref = new Pref(BookLanding.this);
            classn = getIntent().getIntExtra("ID", 0);
            language = getIntent().getStringExtra("language");
            selectedClass = getIntent().getStringExtra("class");
            if (getIntent().getStringExtra("language").equals("1")) {
                ((TextView) findViewById(R.id.preface)).setText("Overview");
                ((TextView) findViewById(R.id.user_review)).setText("Feedback");
                ((TextView) findViewById(R.id.heading_text)).setText("Overview");
                ((TextView) findViewById(R.id.tvEBook)).setText("Ebook");
                ((TextView) findViewById(R.id.tvActivity)).setText("Activities");
                ((TextView) findViewById(R.id.tvVideo)).setText("Videos");
                ((TextView) findViewById(R.id.tvSongs)).setText("Songs");
                ((TextView) findViewById(R.id.tvWebLink)).setText("Web link");
                ((TextView) findViewById(R.id.tvRating)).setText(getResources().getString(R.string.user_rating_english));
                ((TextView) findViewById(R.id.tvPrice)).setText(getResources().getString(R.string.price_price));
                ((TextView) findViewById(R.id.classname)).setText(getIntent().getStringExtra("BookName"));
                ((TextView) findViewById(R.id.rating)).setText(String.format("%s/5.0", getIntent().getStringExtra("Rating")));
                ((TextView) findViewById(R.id.book_author_name)).setText(String.format("Author Name: %s", getIntent().getStringExtra("Teacher")));
                ((TextView) findViewById(R.id.price)).setText(String.format("₹ %s", new DecimalFormat("0.00").format(getIntent().getFloatExtra("Price", 0))));
            } else {
                ((TextView) findViewById(R.id.classname)).setText(getIntent().getStringExtra("BookName"));
                ((TextView) findViewById(R.id.rating)).setText(String.format("%s/5.0", getIntent().getStringExtra("Rating")));
                ((TextView) findViewById(R.id.book_author_name)).setText(String.format("நூலாசிரியர்: %s", getIntent().getStringExtra("Teacher")));
                ((TextView) findViewById(R.id.price)).setText(String.format("₹ %s", new DecimalFormat("0.00").format(getIntent().getFloatExtra("Price", 0))));
            }

            if (language.equals("1")) {
                int id = R.string.class1_eng_info;
                switch (classn) {
                    case 0:
                        id = R.string.class_lkg_eng_info;
                        break;
                    case 1:
                        id = R.string.class_ukg_eng_info;
                         break;
                    case 3:
                        id = R.string.class2_eng_info;
                        break;
                    case 4:
                        id = R.string.class3_eng_info;
                        break;
                    case 5:
                        id = R.string.class4_eng_info;
                        break;
                    case 6:
                        id = R.string.class5_eng_info;
                }
                ((TextView) findViewById(R.id.noolvivaram)).setText(getString(id));
            } else {
                int id = R.string.class1_info;
                if (getIntent().getStringExtra("BookName").equalsIgnoreCase("மழலையர் மறைக்கல்வி")) {
                    id = R.string.class_lkg_ukg_info;
                }
                switch (classn) {
                    case 2:
                        id = R.string.class2_info;
                        break;
                    case 3:
                        id = R.string.class3_info;
                        break;
                    case 4:
                        id = R.string.class4_info;
                        break;
                    case 5:
                        id = R.string.class5_info;
                }
                ((TextView) findViewById(R.id.noolvivaram)).setText(getString(id));
            }


            ((ImageView) findViewById(R.id.classimage)).setImageResource(getIntent().getIntExtra("Image", 0));
            networkStatus();
            animationWork();
            buttonClick();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //
    @Override
    protected void onResume() {
        super.onResume();
        SingleTon.setListener(this, findViewById(R.id.sound_icon_book));
    }
//    @Override
//    protected void onPause() {
//        super.onPause();
//        stopService(new Intent(this,MusicService.class));
//    }

    private void networkStatus() {

        networkStatusFinder = new NetworkStatusFinder();
        Log.i("netwwork status     ", String.valueOf(networkStatusFinder.networkStatus(BookLanding.this)));
        if (!networkStatusFinder.networkStatus(BookLanding.this)) {
            Intent intent = new Intent(BookLanding.this, EmptyStatus.class);
            intent.putExtra("status", "no_net");
            startActivity(intent);
            finish();
        }

    }

    private void animationWork() {

        from_small = AnimationUtils.loadAnimation(BookLanding.this, R.anim.from_small);
        from_nothing = AnimationUtils.loadAnimation(BookLanding.this, R.anim.from_nothing);

        // Animation part Book details alpha set
        book_landing_card.setAlpha(1);
        layout_e_book.setAlpha(1);
        layout_activity.setAlpha(1);
        layout_video.setAlpha(1);
        layout_song.setAlpha(1);
        layout_web_link.setAlpha(1);

        // Animation part Book details animation set
        book_landing_card.startAnimation(from_small);
        layout_e_book.startAnimation(from_nothing);
        layout_activity.startAnimation(from_nothing);
        layout_video.startAnimation(from_nothing);
        layout_song.startAnimation(from_nothing);
        layout_web_link.startAnimation(from_nothing);

    }

    private void buttonClick() {

        // Toggle button
        toggle_details.setVisibility(View.VISIBLE);
        toggle_details1.setVisibility(View.INVISIBLE);
        preface.setTypeface(null, Typeface.BOLD);
        preface_content.setVisibility(View.VISIBLE);

        user_review.setOnClickListener(this);
        preface.setOnClickListener(this);
        layout_e_book.setOnClickListener(this);
        write_my_review.setOnClickListener(this);
        layout_activity.setOnClickListener(this);
        layout_video.setOnClickListener(this);
        layout_song.setOnClickListener(this);
        layout_web_link.setOnClickListener(this);
        navigation_icon.setOnClickListener(this);

        if (pref.getPreferenceFloat(Pref.REVIEW_RATING) != (float) 0) {
            cv_review.setVisibility(View.GONE);
        }

    }

    private void visibilityDataRating() {
        preface.setTypeface(null, Typeface.NORMAL);
        user_review.setTypeface(null, Typeface.BOLD);
        toggle_details1.setVisibility(View.VISIBLE);
        toggle_details1.setAlpha(1);
        toggle_details1.startAnimation(from_small);
        toggle_details.setVisibility(View.INVISIBLE);
        review_content.setVisibility(View.VISIBLE);
        preface_content.setVisibility(View.GONE);
    }

    private void visibilityDataPreface() {
        user_review.setTypeface(null, Typeface.NORMAL);
        preface.setTypeface(null, Typeface.BOLD);
        toggle_details.setVisibility(View.VISIBLE);
        toggle_details.setAlpha(1);
        toggle_details.startAnimation(from_small);
        toggle_details1.setVisibility(View.INVISIBLE);
        preface_content.setVisibility(View.VISIBLE);
        review_content.setVisibility(View.GONE);
    }

    private void setReviewAdapter() {
        reviewAdapter = new ReviewAdapter(bookLandingUserList);
        review_list.setAdapter(reviewAdapter);
        setData();
    }

    @Override
    public void onClick(View view) {
        Log.i(getClass().getName(), "====================" + language);
        if (view.getId() == R.id.user_review) {
            visibilityDataRating();
            setReviewAdapter();
        } else if (view.getId() == R.id.preface) {
            visibilityDataPreface();
        } else if (view.getId() == R.id.layout_e_book) {
            if (language.equals("0"))
                startActivity(new Intent(BookLanding.this, EBook.class)
                        .putExtra("language", "" + language)
                        .putExtra("ID", classn));
            else if (language.equals("1") && (classn == 0) || classn == 1)
                startActivity(new Intent(BookLanding.this, EBook.class)
                        .putExtra("language", "" + language)
                        .putExtra("ID", classn));
            else {
                startActivity(new Intent(BookLanding.this, EmptyView.class));
            }
            overridePendingTransition(R.anim.activity_slideright, R.anim.activity_slideleft);

        } else if (view.getId() == R.id.write_my_review) {
            startActivity(new Intent(BookLanding.this, ReviewActivity.class));
            overridePendingTransition(R.anim.activity_slideright, R.anim.activity_slideleft);

        } else if (view.getId() == R.id.layout_activity) {
            if (language.equals("0") && classn == 0) {
                startActivity(new Intent(BookLanding.this, TamilNumberChoose.class)
                        .putExtra("language", "" + language)
                        .putExtra("class", selectedClass)
                        .putExtra("ID", classn).putExtra("Views", getString(R.string.activity_tamil)));
                overridePendingTransition(R.anim.activity_slideright, R.anim.activity_slideleft);
            } else {
                startActivity(new Intent(BookLanding.this, LessonsActivity.class)
                        .putExtra("language", "" + language)
                        .putExtra("class", selectedClass)
                        .putExtra("ID", classn).putExtra("Views", getString(R.string.activity_tamil)));
                overridePendingTransition(R.anim.activity_slideright, R.anim.activity_slideleft);
            }
        } else if (view.getId() == R.id.layout_video) {
            if (language.equals("0") && classn == 0)
                startActivity(new Intent(BookLanding.this, TamilNumberChoose.class)
                        .putExtra("language", "" + language)
                        .putExtra("class", selectedClass)
                        .putExtra("ID", classn)
                        .putExtra("ID", classn).putExtra("Views", getString(R.string.videos_tamil)));
            else if (language.equals("0"))
                startActivity(new Intent(BookLanding.this, LessonsActivity.class)
                        .putExtra("language", "" + language)
                        .putExtra("class", selectedClass)
                        .putExtra("ID", classn)
                        .putExtra("ID", classn).putExtra("Views", getString(R.string.videos_tamil)));
            else {
                startActivity(new Intent(BookLanding.this, EmptyView.class));
            }
            overridePendingTransition(R.anim.activity_slideright, R.anim.activity_slideleft);
//            Intent intent = new Intent(BookLanding.this, LiveVideo.class);
//            intent.putExtra("from", "live");
//            startActivity(intent);
//            overridePendingTransition(R.anim.activity_slideright, R.anim.activity_slideleft);

        } else if (view.getId() == R.id.layout_song) {
            if (language.equals("0") && classn == 0)
                startActivity(new Intent(BookLanding.this, TamilNumberChoose.class)
                        .putExtra("language", "" + language)
                        .putExtra("class", selectedClass)
                        .putExtra("ID", classn).putExtra("Views", getString(R.string.songs_tamil)));
            else if (language.equals("0"))
                startActivity(new Intent(BookLanding.this, LessonsActivity.class)
                        .putExtra("language", "" + language)
                        .putExtra("class", selectedClass)
                        .putExtra("ID", classn).putExtra("Views", getString(R.string.songs_tamil)));
            else {
                startActivity(new Intent(BookLanding.this, EmptyView.class));
            }
            overridePendingTransition(R.anim.activity_slideright, R.anim.activity_slideleft);

//            Intent intent = new Intent(BookLanding.this, LiveVideo.class);
//            intent.putExtra("from", "songs1");
//            startActivity(intent);
//            overridePendingTransition(R.anim.activity_slideright, R.anim.activity_slideleft);

        } else if (view.getId() == R.id.layout_web_link) {
            if (language.equals("0")) {
                Intent intent = new Intent(BookLanding.this, LessonsActivity.class);
                intent.putExtra("language", "" + language);
                intent.putExtra("class", selectedClass);
                intent.putExtra("ID", classn);
                intent.putExtra("pos", 1);
                intent.putExtra("Views", "");
                startActivity(intent);
                overridePendingTransition(R.anim.activity_slideright, R.anim.activity_slideleft);
            } else {
                startActivity(new Intent(BookLanding.this, LessonsActivity.class)
                        .putExtra("language", "" + language)
                        .putExtra("class", selectedClass)
                        .putExtra("ID", classn).putExtra("Views", ""));
            }

        } else if (view.getId() == R.id.navigation_icon) {
            onBackPressed();
        }
    }

    private void setData() {

        bookLandingUserList.clear();

        if (pref.getPreferenceFloat(Pref.REVIEW_RATING) != (float) 0) {

            BookLandingViewModel bookLandingViewModel = new BookLandingViewModel();
            bookLandingViewModel.id = "1";
            bookLandingViewModel.name = "User one";
            bookLandingViewModel.rating = String.valueOf(pref.getPreferenceFloat(Pref.REVIEW_RATING));
            String date = String.valueOf(android.text.format.DateFormat.format("dd-MM-yyyy", new java.util.Date()));
            bookLandingViewModel.date_time = date;
            bookLandingViewModel.first_letter_review = "U";
            if (pref.getPreference(Pref.REVIEW_DESC) != null) {
                bookLandingViewModel.review = pref.getPreference(Pref.REVIEW_DESC);
            } else {
                bookLandingViewModel.review = "";
            }
            bookLandingUserList.add(bookLandingViewModel);
        }

    }

    @Override
    public void onBackPressed() {
        finish();
    }
}