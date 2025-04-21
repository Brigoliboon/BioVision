package com.example.biovision.ui.camera.fragments.fullDatail;

import androidx.lifecycle.ViewModelProvider;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.biovision.R;
import com.example.biovision.viewmodel.FullDetailViewModel;
import com.example.biovision.viewmodel.PlantViewModel;

public class FullDetailFragment extends Fragment {

    private FullDetailViewModel mViewModel;
    private PlantViewModel plantViewModel;

    public static FullDetailFragment newInstance() {
        return new FullDetailFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_full_detail, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.setBackgroundColor(Color.WHITE);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(FullDetailViewModel.class);
        // TODO: Use the ViewModel
    }

}