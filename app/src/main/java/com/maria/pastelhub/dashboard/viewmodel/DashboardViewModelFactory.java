package com.maria.pastelhub.dashboard.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.maria.pastelhub.dashboard.interfaces.DashBoardResultCallbacks;
import com.maria.pastelhub.forgetpassword.interfaces.ForgetResultCallbacks;
import com.maria.pastelhub.forgetpassword.viewmodel.ForgetViewModel;

public class DashboardViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private DashBoardResultCallbacks forgetResultCallbacks;

    public DashboardViewModelFactory(DashBoardResultCallbacks forgetResultCallbacks) {
        this.forgetResultCallbacks = forgetResultCallbacks;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T)new DashboardViewModel(forgetResultCallbacks);
    }
}
