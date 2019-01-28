package com.alpay.wesapiens.fragments;

import android.os.Bundle;

import androidx.annotation.ColorInt;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alpay.wesapiens.R;
import com.alpay.wesapiens.view.PhysicsFlyoutMenu;

import org.zakariya.flyoutmenu.FlyoutMenuView;

import java.util.ArrayList;
import java.util.List;

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
        prepareChemistryView();
        prepareBiologyView();
        super.onStart();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }


    private void preparePhysicsView(){
        int[] emojiCodes = {
                0x1F60D, //smiling face heart shaped eyes
                0x1F605, // smiling face with open mouth and cold sweat
                0x1F60A, // smiling face
                0x1F613, // face with cold sweat
                0x1F61E, // disappointed face
                0x1F620, // angry face
                0x1F62D, // loudly crying face
                0x1F4A9, // pile of poo
        };

        @ColorInt int color = ContextCompat.getColor(getContext(), R.color.md_pink_400);
        float fontSizeInMenu = getResources().getDimension(R.dimen.unit16);
        float fontSizeInButton = getResources().getDimension(R.dimen.unit30);

        List<PhysicsFlyoutMenu.MenuItem> menuItems = new ArrayList<>();
        for (int code : emojiCodes) {
            menuItems.add(new PhysicsFlyoutMenu.MenuItem(menuItems.size(), code, fontSizeInMenu, color));
        }

        physicsMenu.setLayout(new FlyoutMenuView.GridLayout(2, FlyoutMenuView.GridLayout.UNSPECIFIED));

        physicsMenu.setAdapter(new FlyoutMenuView.ArrayAdapter<>(menuItems));

        final PhysicsFlyoutMenu.ButtonRenderer renderer = new PhysicsFlyoutMenu.ButtonRenderer(emojiCodes[0], fontSizeInButton, color);
        physicsMenu.setButtonRenderer(renderer);

        physicsMenu.setSelectionListener(new FlyoutMenuView.SelectionListener() {
            @Override
            public void onItemSelected(FlyoutMenuView flyoutMenuView, FlyoutMenuView.MenuItem item) {
                renderer.setEmojiCode(((PhysicsFlyoutMenu.MenuItem) item).getEmojiCode());
            }
            @Override
            public void onDismissWithoutSelection(FlyoutMenuView flyoutMenuView) {
            }
        });
    }

    private void prepareChemistryView(){
        int[] emojiCodes = {
                0x1F60D, //smiling face heart shaped eyes
                0x1F605, // smiling face with open mouth and cold sweat
                0x1F60A, // smiling face
                0x1F613, // face with cold sweat
                0x1F61E, // disappointed face
                0x1F620, // angry face
                0x1F62D, // loudly crying face
                0x1F4A9, // pile of poo
        };

        @ColorInt int color = ContextCompat.getColor(getContext(), R.color.md_pink_400);
        float fontSizeInMenu = getResources().getDimension(R.dimen.unit16);
        float fontSizeInButton = getResources().getDimension(R.dimen.unit30);

        List<PhysicsFlyoutMenu.MenuItem> menuItems = new ArrayList<>();
        for (int code : emojiCodes) {
            menuItems.add(new PhysicsFlyoutMenu.MenuItem(menuItems.size(), code, fontSizeInMenu, color));
        }

        chemistryMenu.setLayout(new FlyoutMenuView.GridLayout(2, FlyoutMenuView.GridLayout.UNSPECIFIED));

        chemistryMenu.setAdapter(new FlyoutMenuView.ArrayAdapter<>(menuItems));

        final PhysicsFlyoutMenu.ButtonRenderer renderer = new PhysicsFlyoutMenu.ButtonRenderer(emojiCodes[0], fontSizeInButton, color);
        chemistryMenu.setButtonRenderer(renderer);

        chemistryMenu.setSelectionListener(new FlyoutMenuView.SelectionListener() {
            @Override
            public void onItemSelected(FlyoutMenuView flyoutMenuView, FlyoutMenuView.MenuItem item) {
                renderer.setEmojiCode(((PhysicsFlyoutMenu.MenuItem) item).getEmojiCode());
            }
            @Override
            public void onDismissWithoutSelection(FlyoutMenuView flyoutMenuView) {
            }
        });
    }

    private void prepareBiologyView(){
        int[] emojiCodes = {
                0x1F60D, //smiling face heart shaped eyes
                0x1F605, // smiling face with open mouth and cold sweat
                0x1F60A, // smiling face
                0x1F613, // face with cold sweat
                0x1F61E, // disappointed face
                0x1F620, // angry face
                0x1F62D, // loudly crying face
                0x1F4A9, // pile of poo
        };

        @ColorInt int color = ContextCompat.getColor(getContext(), R.color.md_pink_400);
        float fontSizeInMenu = getResources().getDimension(R.dimen.unit16);
        float fontSizeInButton = getResources().getDimension(R.dimen.unit30);

        List<PhysicsFlyoutMenu.MenuItem> menuItems = new ArrayList<>();
        for (int code : emojiCodes) {
            menuItems.add(new PhysicsFlyoutMenu.MenuItem(menuItems.size(), code, fontSizeInMenu, color));
        }

        biologyMenu.setLayout(new FlyoutMenuView.GridLayout(2, FlyoutMenuView.GridLayout.UNSPECIFIED));

        biologyMenu.setAdapter(new FlyoutMenuView.ArrayAdapter<>(menuItems));

        final PhysicsFlyoutMenu.ButtonRenderer renderer = new PhysicsFlyoutMenu.ButtonRenderer(emojiCodes[0], fontSizeInButton, color);
        biologyMenu.setButtonRenderer(renderer);

        biologyMenu.setSelectionListener(new FlyoutMenuView.SelectionListener() {
            @Override
            public void onItemSelected(FlyoutMenuView flyoutMenuView, FlyoutMenuView.MenuItem item) {
                renderer.setEmojiCode(((PhysicsFlyoutMenu.MenuItem) item).getEmojiCode());
            }
            @Override
            public void onDismissWithoutSelection(FlyoutMenuView flyoutMenuView) {
            }
        });
    }

}
