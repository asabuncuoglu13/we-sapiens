package com.alpay.wesapiens.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alpay.wesapiens.R;
import com.alpay.wesapiens.view.PhysicsFlyoutMenu;

import org.zakariya.flyoutmenu.FlyoutMenuView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.ColorInt;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MapFragment extends Fragment {

    public View view;
    private Unbinder unbinder;

    @BindView(R.id.physics)
    FlyoutMenuView physicsMenu;

    @BindView(R.id.chemistry)
    FlyoutMenuView chemistryMenu;

    @BindView(R.id.biology)
    FlyoutMenuView biologyMenu;


    public MapFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_map, container, false);
        unbinder = ButterKnife.bind(this, view);
        return  view;
    }

    @Override
    public void onStart() {
        preparePhysicsView();
        //prepareChemistryView();
        //prepareBiologyView();
        super.onStart();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }


    private void preparePhysicsView(){
        int[] menuDrawableCodes = {
                R.drawable.play,
                R.drawable.ic_mitosis,
                R.drawable.ic_mayosis,
                R.drawable.ic_generation
        };

        @ColorInt int color = ContextCompat.getColor(getContext(), R.color.colorPrimary);
        float fontSizeInMenu = getResources().getDimension(R.dimen.unit16);

        List<PhysicsFlyoutMenu.MenuItem> menuItems = new ArrayList<>();
        for (int code : menuDrawableCodes) {
            menuItems.add(new PhysicsFlyoutMenu.MenuItem((AppCompatActivity) getActivity(), menuItems.size(), code, fontSizeInMenu, color));
        }

        physicsMenu.setLayout(new FlyoutMenuView.GridLayout(2, FlyoutMenuView.GridLayout.UNSPECIFIED));
        physicsMenu.setAdapter(new FlyoutMenuView.ArrayAdapter<>(menuItems));

        physicsMenu.setSelectionListener(new FlyoutMenuView.SelectionListener() {
            @Override
            public void onItemSelected(FlyoutMenuView flyoutMenuView, FlyoutMenuView.MenuItem item) {

            }
            @Override
            public void onDismissWithoutSelection(FlyoutMenuView flyoutMenuView) {
            }
        });
    }

}
