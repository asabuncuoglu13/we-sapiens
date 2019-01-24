package com.alpay.wesapiens.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alpay.wesapiens.GameActivity;
import com.alpay.wesapiens.R;

public class HomeFragment extends Fragment {

    public View view;
    private Unbinder unbinder;

    @OnClick(R.id.play_button)
    public void playGame(){
        Intent intent = new Intent(getActivity(), GameActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.create_game_button)
    public void createGame(){
        FragmentManager.openFragment((AppCompatActivity) getActivity(), FragmentManager.CREATE_GAME);
    }

    public HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        unbinder.unbind();
    }

}
