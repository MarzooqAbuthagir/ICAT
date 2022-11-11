package com.maria.pastelhub.forgetpassword;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import com.maria.pastelhub.R;
import com.maria.pastelhub.register.Registration;
import com.maria.pastelhub.alert.AlertClass;
import com.maria.pastelhub.databinding.ActivityForgetPasswordBinding;
import com.maria.pastelhub.forgetpassword.interfaces.ForgetResultCallbacks;
import com.maria.pastelhub.forgetpassword.viewmodel.ForgetViewModel;
import com.maria.pastelhub.forgetpassword.viewmodel.ForgetViewModelFactory;
import com.maria.pastelhub.login.Login;

public class ForgetPassword extends AppCompatActivity implements ForgetResultCallbacks {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);

        ActivityForgetPasswordBinding activityForgetPasswordBinding = DataBindingUtil.setContentView(this, R.layout.activity_forget_password);
        activityForgetPasswordBinding.setViewModel(ViewModelProviders.of(this,
                new ForgetViewModelFactory(this))
        .get(ForgetViewModel.class));
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onSuccess(String title, String message) {
        new AlertClass(ForgetPassword.this, "", ForgetPassword.this, title, message, 5);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onError(String title, String message) {
        new AlertClass(ForgetPassword.this, "", ForgetPassword.this, title, message, 1);
    }

    public void loginClicked(View view) {
        toLogin();
    }

    public void registerClicked(View view) {
        toRegister();
    }

    @Override
    public void onBackPressed() {
        toLogin();
    }

    public void toLogin() {
        startActivity(new Intent(ForgetPassword.this, Login.class));
        finish();
        overridePendingTransition(R.anim.slide_in_left1, R.anim.slide_in_left);
    }

    public void toRegister() {
        startActivity(new Intent(ForgetPassword.this, Registration.class));
        finish();
        overridePendingTransition(R.anim.slide_in_right1, R.anim.slide_in_right);
    }

}