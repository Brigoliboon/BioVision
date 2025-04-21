package com.example.biovision.ui.components;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import com.example.biovision.R;
import com.example.biovision.data.API.Plant.model.Plant;
import com.example.biovision.data.API.Plant.model.PlantResult;
import com.example.biovision.ui.camera.fragments.fullDatail.FullDetailFragment;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BottomSheetFragmentv2 extends BottomSheetDialogFragment {
    PlantResult result;
    public BottomSheetFragmentv2(PlantResult result){
        super();
        this.result = result;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the existing XML file
        View sheetView = inflater.inflate(R.layout.bottomsheetlayoutv2, container, false);
        return sheetView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ViewPager2 viewPager = view.findViewById(R.id.viewPagerCarousel);
        Plant closestMatch = result.getClosestMatch();

// Create slices for the donut chart
        ArrayList<PieEntry> entries = new ArrayList<>();
        entries.add(new PieEntry(75f, ""));
        entries.add(new PieEntry(25f, ""));
        PieDataSet dataSet = new PieDataSet(entries, "");
        dataSet.setColors(Color.parseColor(result.health().getHealthStats().getColor()), android.R.color.secondary_text_light); // Set slice colors
        dataSet.setSliceSpace(3f);
        dataSet.setDrawValues(false);
        PieData healthData = new PieData(dataSet);
        loadHealthGraph(view, result, healthData);

        ArrayList<PieEntry> diseaseEntries = new ArrayList<>();
        diseaseEntries.add(new PieEntry(100f, ""));
        diseaseEntries.add(new PieEntry(0f, ""));
        PieDataSet diseaseDataSet = new PieDataSet(diseaseEntries, "");
        diseaseDataSet.setColors(Color.parseColor("#FFACAC"), android.R.color.secondary_text_light); // Set slice colors
        diseaseDataSet.setSliceSpace(3f);
        diseaseDataSet.setDrawValues(false);
        PieData diseaseData = new PieData(diseaseDataSet);
        loadDiseaseGraph(view, result, diseaseData);

        loadViewPager(viewPager, new Integer[] {R.layout.plant_detail_desc_layout, R.layout.plant_detail_kind_layout}, closestMatch);
        initializeBottomSheet(view, result);

        view.findViewById(R.id.health_btn).setOnClickListener(v -> {

            FragmentActivity activity = getActivity();
            if (activity != null) {
                activity.getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, new FullDetailFragment())
                        .addToBackStack(null)
                        .commit();

                View bottomSheet = getDialog().findViewById(R.id.design_bottom_sheet);
                BottomSheetBehavior<View> behavior = BottomSheetBehavior.from(bottomSheet);
                behavior.setState(BottomSheetBehavior.STATE_HIDDEN);
            }
        });

        view.findViewById(R.id.detail_btn).setOnClickListener(v -> {

            FragmentActivity activity = getActivity();
            if (activity != null) {
                activity.getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, new FullDetailFragment())
                        .addToBackStack(null)
                        .commit();

                View bottomSheet = getDialog().findViewById(R.id.design_bottom_sheet);
                BottomSheetBehavior<View> behavior = BottomSheetBehavior.from(bottomSheet);
                behavior.setState(BottomSheetBehavior.STATE_HIDDEN);
            }
        });

    }

    @Override
    public void onStart() {
        super.onStart();
        View bottomSheet = getDialog().findViewById(R.id.design_bottom_sheet);
        BottomSheetBehavior<View> behavior = BottomSheetBehavior.from(bottomSheet);

        behavior.setState(BottomSheetBehavior.STATE_COLLAPSED);  // Start in collapsed mode
        behavior.setPeekHeight(550);  // Default collapsed height
        behavior.setHideable(false);  // Prevent dismissing by swipe
        behavior.setFitToContents(true);
    }

    /**
     * Assign values from a dynamic data to the bottom sheet
     * @param view
     * @param result
     */
    private void initializeBottomSheet(View view, PlantResult result){
        TextView common_name = view.findViewById(R.id.common_name);
        TextView scientific_name = view.findViewById(R.id.scientific_name);
        TextView common_names = view.findViewById(R.id.common_names);
        TextView match_percent = view.findViewById(R.id.match_percent);
        ImageView img = view.findViewById(R.id.sample_img);
        Plant match = result.getClosestMatch();

        Picasso.get()
                .load(match.similarImages().get(0).url()) // or use a local file or resource
                .into(img); // your ImageView

        match_percent.setText("Match Percent: "+match.getMatchPercent());
        // TODO: Implement getting first of the common names
        common_name.setText(match.getCommonName());
        scientific_name.setText(match.scientificName());
        // TODO: Implement string builder for concatenating all common names
        common_names.setText(match.getConcatNames());
    }

    // TODO
    private void loadImageGallery(View view, PlantResult result){

    }

    private void loadDiseaseGraph(View view, PlantResult result, PieData pieData){
        PieChart pieChart = view.findViewById(R.id.diseaseCount);
        pieChart.setData(pieData);
// Enable the donut effect (center hole)
        pieChart.setHoleRadius(70f); // Percentage of the chart's width
        pieChart.setTransparentCircleAlpha(2);
        pieChart.setDrawHoleEnabled(true);
        pieChart.getDescription().setEnabled(false);
        pieChart.getLegend().setEnabled(false);
        pieChart.setDrawEntryLabels(false);

// Set center text
        pieChart.setCenterText(result.disease().size()+"\nPotential\nDiseases");
        pieChart.setCenterTextSize(14f);
        pieChart.animateY(1000); // Animation
    }
    private void loadHealthGraph(View view, PlantResult result, PieData pieData){
        PieChart pieChart = view.findViewById(R.id.healthGraph);
        pieChart.setData(pieData);
// Enable the donut effect (center hole)
        pieChart.setHoleRadius(70f); // Percentage of the chart's width
        pieChart.setTransparentCircleAlpha(2);
        pieChart.setDrawHoleEnabled(true);
        pieChart.getDescription().setEnabled(false);
        pieChart.getLegend().setEnabled(false);
        pieChart.setDrawEntryLabels(false);

// Set center text
        pieChart.setCenterText(result.health().getPercentage()+"\n"+result.health().getHealthStats());
        pieChart.setCenterTextSize(20f);
        pieChart.animateY(1000); // Animation
    }
    private void loadViewPager(ViewPager2 viewPager, Integer[] layoutArr, Plant plant){
        List<Integer> layoutIds = Arrays.asList(
                layoutArr
        );
        CarouselLayoutAdapter adapter = new CarouselLayoutAdapter(layoutIds, plant);
        viewPager.setAdapter(adapter);
// Carousel effect
        viewPager.setOffscreenPageLimit(3);
        viewPager.setPageTransformer(new MarginPageTransformer(40));
        viewPager.setPageTransformer((page, position) -> {
            float scale = 0.9f + (1 - Math.abs(position)) * 0.1f;
            page.setScaleY(scale);
        });
        viewPager.setPageTransformer((page, position) -> {
            float scale = 0.85f + (1 - Math.abs(position)) * 0.15f;
            page.setScaleY(scale);
        });
    }

    public void loadDetailPage(View view){
    }
}