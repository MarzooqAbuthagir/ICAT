package com.maria.pastelhub.ebook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.maria.pastelhub.R;
import com.maria.pastelhub.book_landing.BookLanding;
import com.maria.pastelhub.dashboard.Dashboard;
import com.maria.pastelhub.emptystatus.EmptyStatus;
import com.maria.pastelhub.networkstatus.NetworkStatusFinder;
import com.maria.pastelhub.prefference.Pref;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EBook extends AppCompatActivity implements View.OnClickListener {

    NetworkStatusFinder networkStatusFinder;

    @BindView(R.id.back_major_header)
    ImageView back_major_header;

    @BindView(R.id.sound_icon)
    ImageView sound_icon;

    @BindView(R.id.load_book)
    WebView load_book;

    @BindView(R.id.major_title)
    TextView major_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_e_book);

        networkStatus();

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED,
                WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED);

        ButterKnife.bind(this);
        WebSettings webSettings = load_book.getSettings();
        webSettings.setRenderPriority(WebSettings.RenderPriority.HIGH);
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        if (Build.VERSION.SDK_INT >= 19) {
            load_book.setLayerType(View.LAYER_TYPE_HARDWARE, null);
        } else {
            load_book.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        }
        webSettings.setJavaScriptEnabled(true);
        //load_book.loadUrl("http://digimaria.com/flipbook/maraikkalvi_class1/mobile/index.html");
        int classn = getIntent().getIntExtra("ID", 0);
        Pref pref = new Pref(this);
        if (getIntent() != null)
            if (getIntent().getStringExtra("language").equals("1") && classn == 0) {
                load_book.loadUrl("https://icatapp-tnbclc.in/icat/catechism_lkg_flipbook/mobile/index.html");
                major_title.setText("Ebook");
            } else if (getIntent().getStringExtra("language").equals("1") && getIntent().getIntExtra("ID", 0) == 1) {
                load_book.loadUrl("https://icatapp-tnbclc.in/icat/catechism_ukg_flipbook/mobile/index.html");
                major_title.setText("Ebook");

            } else {
                major_title.setText(R.string.e_book_tamil);
                if (classn == 2) {
                    load_book.loadUrl("https://icatapp-tnbclc.in/icat/Maraikkalvi_Tamil/maraikkalvi_class2_flipbook/mobile/index.html");
                } else if (classn == 3) {
                    load_book.loadUrl("https://icatapp-tnbclc.in/icat/Maraikkalvi_Tamil/maraikkalvi_class3_flipbook/mobile/index.html");
                } else if (classn == 4) {
                    load_book.loadUrl("https://icatapp-tnbclc.in/icat/Maraikkalvi_Tamil/maraikkalvi_class4_flipbook/mobile/index.html");
                } else if (classn == 5) {
                    load_book.loadUrl("https://icatapp-tnbclc.in/icat/Maraikkalvi_Tamil/maraikkalvi_class5_flipbook/mobile/index.html");
                } else if (classn == 1) {
                    load_book.loadUrl("https://icatapp-tnbclc.in/icat/Maraikkalvi_Tamil/maraikkalvi_class1_flipbook/mobile/index.html");
                } else if (classn == 0) {
                    load_book.loadUrl("https://icatapp-tnbclc.in/icat/mazhalaiyar_maraikkalvi_tamil_flipbook/mobile/index.html");
                }
            }
        back_major_header.setOnClickListener(this);
        sound_icon.setVisibility(View.INVISIBLE);
    }


    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.back_major_header) {
            backClicked();
        }
    }

    private void networkStatus() {

        networkStatusFinder = new NetworkStatusFinder();

        if (!networkStatusFinder.networkStatus(EBook.this)) {
            Intent intent = new Intent(EBook.this, EmptyStatus.class);
            intent.putExtra("status", "no_net");
            startActivity(intent);
        }

    }

    @Override
    public void onBackPressed() {
        backClicked();
    }

    private void backClicked() {
        finish();
        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
    }
}