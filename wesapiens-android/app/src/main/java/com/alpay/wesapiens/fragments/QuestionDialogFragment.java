package com.alpay.wesapiens.fragments;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.FrameLayout;

import com.alpay.wesapiens.R;
import com.alpay.wesapiens.listener.OnSwipeTouchListener;

public class QuestionDialogFragment extends DialogFragment {

    public View view;
    private Unbinder unbinder;

    @BindView(R.id.question_edittext)
    EditText questionEditText;

    @BindView(R.id.question_dialog_frame)
    FrameLayout questionDialogFrame;

    @OnClick(R.id.question_next_page_button)
    public void nextButtonAction(){
        nextPage();
    }

    @OnClick(R.id.question_prev_page_button)
    public void prevButtonAction(){
        previousPage();
    }

    public QuestionDialogFragment() {
    }

    public static QuestionDialogFragment newInstance(String title) {
        QuestionDialogFragment questionDialogFragment = new QuestionDialogFragment();
        Bundle args = new Bundle();
        args.putString("title", title);
        questionDialogFragment.setArguments(args);
        return questionDialogFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_question_dialog, container);
        unbinder = ButterKnife.bind(this, view);
        questionDialogFrame.setOnTouchListener(new OnSwipeTouchListener(getActivity()){
            @Override
            public void onSwipeLeft() {
                nextPage();
                super.onSwipeLeft();
            }

            @Override
            public void onSwipeRight() {
                previousPage();
                super.onSwipeRight();
            }
        });
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String title = getArguments().getString("title", "Enter Name");
        getDialog().setTitle(title);
        questionEditText.requestFocus();
        getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    private void nextPage(){

    }

    private void previousPage(){

    }

}
