package com.maria.pastelhub.forgetpassword.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.maria.pastelhub.forgetpassword.interfaces.ForgetResultCallbacks;

public class ForgetViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private ForgetResultCallbacks forgetResultCallbacks;

    public ForgetViewModelFactory(ForgetResultCallbacks forgetResultCallbacks) {
        this.forgetResultCallbacks = forgetResultCallbacks;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T)new ForgetViewModel(forgetResultCallbacks);
    }
}
