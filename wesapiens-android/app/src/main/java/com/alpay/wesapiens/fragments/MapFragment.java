package com.alpay.wesapiens.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ImageView;

import com.alpay.wesapiens.R;
import com.alpay.wesapiens.adapter.MenuListAdapter;
import com.alpay.wesapiens.models.MenuItem;

import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class MapFragment extends Fragment {

    public View view;
    private Unbinder unbinder;

    @BindView(R.id.select_menu)
    RecyclerView recyclerView;

    @OnClick(R.id.physics)
    public void selectPhysicsTopic(){
        preparePhysicsView();
    }

    @OnClick(R.id.chemistry)
    public void selectChemistryTopic(){
        prepareChemistryView();
    }

    @OnClick(R.id.biology)
    public void selectBiologyTopic(){
        prepareBiologyView();
    }

    ArrayList<MenuItem> menuDrawableCodes = new ArrayList<>();
    MenuListAdapter menuListAdapter = new MenuListAdapter(menuDrawableCodes);

    public MapFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_map, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    private void setupRecylerView(MenuListAdapter adapter) {
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }


    private void preparePhysicsView() {
        menuDrawableCodes.clear();
        menuDrawableCodes.add(new MenuItem("Force", getResources().getDrawable(R.drawable.ic_cell)));
        menuDrawableCodes.add(new MenuItem("Energy", getResources().getDrawable(R.drawable.ic_mitosis)));
        menuDrawableCodes.add(new MenuItem("Machine", getResources().getDrawable(R.drawable.ic_mayosis)));
        menuDrawableCodes.add(new MenuItem("Energy", getResources().getDrawable(R.drawable.ic_generation)));
        menuListAdapter.notifyDataSetChanged();
        setupRecylerView(menuListAdapter);
    }

    private void prepareChemistryView() {
        menuDrawableCodes.clear();
        menuDrawableCodes.add(new MenuItem("Matter", getResources().getDrawable(R.drawable.ic_cell)));
        menuDrawableCodes.add(new MenuItem("Elements", getResources().getDrawable(R.drawable.ic_mitosis)));
        menuDrawableCodes.add(new MenuItem("Solutions", getResources().getDrawable(R.drawable.ic_mayosis)));
        menuDrawableCodes.add(new MenuItem("Energy", getResources().getDrawable(R.drawable.ic_generation)));
        menuListAdapter.notifyDataSetChanged();
        setupRecylerView(menuListAdapter);
    }

    private void prepareBiologyView() {
        menuDrawableCodes.clear();
        menuDrawableCodes.add(new MenuItem("Cell", getResources().getDrawable(R.drawable.ic_cell)));
        menuDrawableCodes.add(new MenuItem("Mitosis", getResources().getDrawable(R.drawable.ic_mitosis)));
        menuDrawableCodes.add(new MenuItem("Mayosis", getResources().getDrawable(R.drawable.ic_mayosis)));
        menuDrawableCodes.add(new MenuItem("Generation", getResources().getDrawable(R.drawable.ic_generation)));
        menuListAdapter.notifyDataSetChanged();
        setupRecylerView(menuListAdapter);
    }
}
