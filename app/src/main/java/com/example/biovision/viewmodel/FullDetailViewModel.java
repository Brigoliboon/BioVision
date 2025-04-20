package com.example.biovision.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.biovision.ui.camera.fragments.fullDatail.Focus;

public class FullDetailViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    private MutableLiveData<Focus> target = new MutableLiveData<>();

    public LiveData<Focus> getFocusTarget() {
        return target;
    }

    public void setFocus(Focus area) {
        target.setValue(area);
    }

}
