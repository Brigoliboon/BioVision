package com.example.biovision.ui.camera.fragments;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.biovision.R;
import com.example.biovision.viewmodel.PlantViewModel;

public class PlantDetailFragment extends Fragment {

    private PlantViewModel viewModel;

    public static PlantDetailFragment newInstance() {
        return new PlantDetailFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.plant_detail_layout, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = new ViewModelProvider(requireActivity()).get(PlantViewModel.class);
        // TODO: Use the ViewModel
        viewModel.getChosenPlant();
    }
}