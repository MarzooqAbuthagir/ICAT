package com.maria.pastelhub.login;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import com.maria.pastelhub.alert.AlertClassExit;
import com.maria.pastelhub.dashboard.Dashboard;
import com.maria.pastelhub.forgetpassword.ForgetPassword;
import com.maria.pastelhub.R;
import com.maria.pastelhub.prefference.AnthemPref;
import com.maria.pastelhub.prefference.Pref;
import com.maria.pastelhub.register.Registration;
import com.maria.pastelhub.alert.AlertClass;
import com.maria.pastelhub.databinding.ActivityLoginBinding;
import com.maria.pastelhub.login.interfaces.LoginResultCallbacks;
import com.maria.pastelhub.login.viewmodel.LoginViewModel;
import com.maria.pastelhub.login.viewmodel.LoginViewModelFactory;

public class Login extends AppCompatActivity implements LoginResultCallbacks {

    Pref pref;
    AnthemPref anthemPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);

        ActivityLoginBinding activityLoginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        activityLoginBinding.setViewModel(ViewModelProviders.of(
                        this,
                        new LoginViewModelFactory(this))
                .get(LoginViewModel.class));

        pref = new Pref(Login.this);
        anthemPref = new AnthemPref(Login.this);

    }

    @Override
    public void onSuccess(String message) {
        pref.setLogin("", "", 1);
        pref.setPreference("id", LoginViewModel.id);
        pref.setPreference("email", LoginViewModel.email);
        pref.setPreference("state", LoginViewModel.state);
        pref.setPreference("is_subscription", LoginViewModel.isSub);
        pref.setPreference("name", LoginViewModel.name);
        pref.setPreference("contact", LoginViewModel.contact);
        pref.setPreference("regClass", LoginViewModel.regClass);
        pref.setPreference("country", LoginViewModel.country);
        Log.i(getClass().getName(), "-==================" + LoginViewModel.state);
        anthemPref.setTamilAnthemView(true);
        anthemPref.setEnglishAnthemView(true);
        System.out.println("dash login "+anthemPref.getTamilAnthemView());
        System.out.println("dash login "+anthemPref.getEnglishAnthemView());
        startActivity(new Intent(Login.this, Dashboard.class));
        finish();
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        LoginViewModel.email = "";
        LoginViewModel.state = "";
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onError(String message) {
        new AlertClass(Login.this, "Login", Login.this, "Failed", message, 1);
    }

    public void forgetClicked(View view) {
        startActivity(new Intent(Login.this, ForgetPassword.class));
        finish();
        overridePendingTransition(R.anim.slide_in_right1, R.anim.slide_in_right);
    }

    public void registerClicked(View view) {
        startActivity(new Intent(Login.this, Registration.class));
        finish();
        overridePendingTransition(R.anim.slide_in_right1, R.anim.slide_in_right);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBackPressed() {
        new AlertClassExit(Login.this, "Sure?", "Are you sure you want to exit?", 1);
    }
}