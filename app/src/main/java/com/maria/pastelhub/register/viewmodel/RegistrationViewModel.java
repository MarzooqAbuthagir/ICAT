package com.maria.pastelhub.register.viewmodel;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;

import androidx.lifecycle.ViewModel;

import com.maria.pastelhub.api.Login;
import com.maria.pastelhub.api.RetrofitClient;
import com.maria.pastelhub.register.Registration;
import com.maria.pastelhub.register.interfaces.RegisterResultCallbacks;
import com.maria.pastelhub.register.model.RegisterUser;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegistrationViewModel extends ViewModel {

    private RegisterUser registerUser;
    private RegisterResultCallbacks registerResultCallbacks;

    public RegistrationViewModel(RegisterResultCallbacks registerResultCallbacks) {
        this.registerUser = new RegisterUser();
        this.registerResultCallbacks = registerResultCallbacks;
    }

    public TextWatcher getNameTextWatcher() {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                registerUser.setName(editable.toString());
            }
        };
    }

    public TextWatcher getEmailTextWatcher() {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                registerUser.setEmail(editable.toString());
            }
        };
    }

    public TextWatcher getMobileTextWatcher() {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                registerUser.setMobile(editable.toString());
            }
        };
    }

    public TextWatcher getUsernameTextWatcher() {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
//                registerUser.setUsername(editable.toString());
            }
        };
    }

    public void onSelectItem(AdapterView<?> parent, View view, int pos, long id) {
        registerUser.setRegClass(parent.getAdapter().getItem(pos).toString());
    }

//    public TextWatcher getPasswordTextWatcher() {
//        return new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable editable) {
//                registerUser.setPassword(editable.toString());
//            }
//        };
//    }

    public void onRegisterClicked(View view) {
//        if (Registration.latitude.equalsIgnoreCase("0.0")) {
//            Registration.getLocation(Registration.context);
//            return;
//        }
        int errorCode = registerUser.isValidRegister();
        if (errorCode == 1) {
            registerResultCallbacks.onError("Failed", "Enter the name");
        } else if (errorCode == 2) {
            registerResultCallbacks.onError("Failed", "Enter the valid name");
        } else if (errorCode == 3) {
            registerResultCallbacks.onError("Failed", "Enter email address");
        } else if (errorCode == 4) {
            registerResultCallbacks.onError("Failed", "Enter valid email address");
        } else if (errorCode == 5) {
            registerResultCallbacks.onError("Failed", "Enter the mobile number");
        } else if (errorCode == 6) {
            registerResultCallbacks.onError("Failed", "Enter valid mobile number");
        } else if (errorCode == 11) {
            registerResultCallbacks.onError("Failed", "Select the class");
        } else if (Registration.latitude.equalsIgnoreCase("0.0") || Registration.longitude.equalsIgnoreCase("0.0")) {
            registerResultCallbacks.onLocReq();
        } else {
            JsonObject params = new JsonObject();
            params.addProperty("mobile", registerUser.getMobile());
            params.addProperty("name", registerUser.getName());
            params.addProperty("email", registerUser.getEmail());
            params.addProperty("country", registerUser.getCountry());
            params.addProperty("state", Registration.state);
            params.addProperty("class", registerUser.getRegClass());
            register(params);
        }
    }

    private void register(JsonObject jsonObject) {
        Call<Login> call = RetrofitClient.getInstance().getMyApi().register(jsonObject);
        call.enqueue(new Callback<Login>() {
            @Override
            public void onResponse(Call<com.maria.pastelhub.api.Login> call, Response<Login> response) {
                com.maria.pastelhub.api.Login r = response.body();
                if (r.getStatus() == 200)
                    registerResultCallbacks.onSuccess("Success", r.getMessage());
                else registerResultCallbacks.onError("Failed", r.getMessage());
            }

            @Override
            public void onFailure(Call<com.maria.pastelhub.api.Login> call, Throwable t) {
                Log.i(getClass().getName(), "=================error   " + t.getMessage());
                registerResultCallbacks.onError("Failed", t.getMessage());

            }

        });
    }

}
