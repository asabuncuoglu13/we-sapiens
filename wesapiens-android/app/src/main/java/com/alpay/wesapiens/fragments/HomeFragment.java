package com.alpay.wesapiens.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alpay.wesapiens.GameActivity;
import com.alpay.wesapiens.R;
import com.alpay.wesapiens.base.FragmentManager;
import com.alpay.wesapiens.utils.Utils;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class HomeFragment extends Fragment {

    public View view;
    private Unbinder unbinder;

    @OnClick(R.id.play_button)
    public void playGame(){
        FragmentManager.openFragment((AppCompatActivity)getActivity(), FragmentManager.MAP);
    }

    @OnClick(R.id.create_game_button)
    public void createGame(){
        FragmentManager.openFragment((AppCompatActivity) getActivity(), FragmentManager.CREATE_GAME);
    }

    @OnClick(R.id.mute_button)
    public void mute(){
        Utils.muteMedia();
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
        getActivity().findViewById(R.id.back_button).setVisibility(View.GONE);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        getActivity().findViewById(R.id.back_button).setVisibility(View.VISIBLE);
        unbinder.unbind();
    }

}
