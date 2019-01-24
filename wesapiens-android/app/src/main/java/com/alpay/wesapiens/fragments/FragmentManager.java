package com.alpay.wesapiens.fragments;

import com.alpay.wesapiens.R;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

public class FragmentManager {

    public static final String BUNDLE_KEY = "bundlekey";
    public static final String  CREATE_GAME = "creategame";
    public static final String HOME = "home";

    public static void openFragment(AppCompatActivity appCompatActivity, String fragmentID) {
        FragmentTransaction ft = appCompatActivity.getSupportFragmentManager().beginTransaction();
        if (fragmentID.contentEquals(CREATE_GAME)) {
            CreateGameFragment createGameFragment = CreateGameFragment.newInstance();
            ft.replace(R.id.fragment_container, createGameFragment);
        } else if (fragmentID.contentEquals(HOME)) {
            HomeFragment homeFragment = HomeFragment.newInstance();
            ft.replace(R.id.fragment_container, homeFragment);
        }
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.commit();
    }

}
