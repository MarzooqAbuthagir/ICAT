package com.maria.pastelhub.register.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.maria.pastelhub.register.interfaces.RegisterResultCallbacks;

public class RegistrationViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private RegisterResultCallbacks registerResultCallbacks;

    public RegistrationViewModelFactory(RegisterResultCallbacks registerResultCallbacks) {
        this.registerResultCallbacks = registerResultCallbacks;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T)new RegistrationViewModel(registerResultCallbacks);
    }

}
