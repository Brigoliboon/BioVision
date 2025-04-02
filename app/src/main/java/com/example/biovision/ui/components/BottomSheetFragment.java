package com.example.biovision.ui.components;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.biovision.R;
import com.example.biovision.data.API.Plant.model.Plant;
import com.example.biovision.data.API.Plant.model.PlantResult;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.squareup.picasso.Picasso;

public class BottomSheetFragment extends BottomSheetDialogFragment {
    PlantResult result;
    public BottomSheetFragment(PlantResult result){
        super();
        this.result = result;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the existing XML file
        View sheetView = inflater.inflate(R.layout.bottomsheetlayout, container, false);
        return sheetView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView short_desc = view.findViewById(R.id.short_desc);
        ImageView target_profile = view.findViewById(R.id.target_profile);
        TextView target_title = view.findViewById(R.id.target_title);
        TextView match_percent = view.findViewById(R.id.match_percent);
        ImageView sample_img = view.findViewById(R.id.sample_img);

        Plant closestMatch = result.getClosestMatch();
        short_desc.setText(closestMatch.detail().description());

        Picasso.get().load(closestMatch.similarImages().get(0).imgSmallLink())
                .into(target_profile);
        Picasso.get().load(closestMatch.similarImages().get(1).url())
                .into(sample_img);
        target_title.setText(closestMatch.name());
        match_percent.setText(closestMatch.getMatchPercent());
    }
    @Override
    public void onStart() {
        super.onStart();
        View bottomSheet = getDialog().findViewById(com.google.android.material.R.id.design_bottom_sheet);
        BottomSheetBehavior<View> behavior = BottomSheetBehavior.from(bottomSheet);

        behavior.setState(BottomSheetBehavior.STATE_COLLAPSED);  // Start in collapsed mode
        behavior.setPeekHeight(100);  // Default collapsed height
        behavior.setHideable(false);  // Prevent dismissing by swipe
        behavior.setFitToContents(true);
    }
}