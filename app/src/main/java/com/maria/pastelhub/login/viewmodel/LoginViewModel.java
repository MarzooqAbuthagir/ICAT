package com.maria.pastelhub.login.viewmodel;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.lifecycle.ViewModel;

import com.maria.pastelhub.api.Results;
import com.maria.pastelhub.api.RetrofitClient;
import com.maria.pastelhub.login.Login;
import com.maria.pastelhub.login.interfaces.LoginResultCallbacks;
import com.maria.pastelhub.login.model.User;
import com.maria.pastelhub.prefference.Pref;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONArray;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginViewModel extends ViewModel {

    private User user;
    private LoginResultCallbacks loginResultCallbacks;

    public LoginViewModel(LoginResultCallbacks loginResultCallbacks) {
        this.loginResultCallbacks = loginResultCallbacks;
        this.user = new User();
    }

    public TextWatcher getMobileNumberTextWatcher() {

        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                user.setMobile(editable.toString());
            }
        };

    }

    public TextWatcher getPasswordTextWatcher() {

        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                user.setPassword(editable.toString());
            }
        };

    }

    public void onLoginClicked(View view) {

        int errorCode = user.isValidData();

        if (errorCode == 0) {
            loginResultCallbacks.onError("Mobile number and password are required.");
        } else if (errorCode == 1) {
            loginResultCallbacks.onError("Enter the mobile number.");
        } else if (errorCode == 2) {
            loginResultCallbacks.onError("Enter valid mobile number.");
        } else if (errorCode == 3) {
            loginResultCallbacks.onError("Enter the password.");
        } else if (errorCode == 4) {
            loginResultCallbacks.onError("Password must contain minimum be 6 letter.");
        } else {
            JsonObject params = new JsonObject();
            params.addProperty("mobile", user.getMobile());
            params.addProperty("password", user.getPassword());
            login(params);
        }
    }

    public static String id = "";
    public static String email = "";
    public static String state = "";
    public static String isSub = "";
    public static String name = "";
    public static String contact = "";
    public static String regClass = "";
    public static String country = "";

    private void login(JsonObject jsonObject) {
        Call<com.maria.pastelhub.api.Login> call = RetrofitClient.getInstance().getMyApi().login(jsonObject);
        call.enqueue(new Callback<com.maria.pastelhub.api.Login>() {
            @Override
            public void onResponse(Call<com.maria.pastelhub.api.Login> call, Response<com.maria.pastelhub.api.Login> response) {
                com.maria.pastelhub.api.Login r = response.body();
                if (r.getStatus() == 200) {
                    id = r.getData().getId();
                    email = r.getData().getEmail();
                    state = r.getData().getState();
                    isSub = r.getData().getIs_subscription();
                    name = r.getData().getName();
                    contact = r.getData().getMobile();
                    regClass = r.getData().getReg_class();
                    country = r.getData().getCountry();
                    loginResultCallbacks.onSuccess("Login success.");
                } else loginResultCallbacks.onError("Invalid login details.");
            }

            @Override
            public void onFailure(Call<com.maria.pastelhub.api.Login> call, Throwable t) {
                Log.i(getClass().getName(), "=================error   " + t.getMessage());
                loginResultCallbacks.onError("Invalid login details.");

            }

        });
    }
}
