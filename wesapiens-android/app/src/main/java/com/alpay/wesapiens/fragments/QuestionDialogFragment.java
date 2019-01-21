package com.alpay.wesapiens.fragments;

import android.content.DialogInterface;
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
import android.widget.TextView;

import com.alpay.wesapiens.HomeActivity;
import com.alpay.wesapiens.R;
import com.alpay.wesapiens.listener.OnSwipeTouchListener;
import com.alpay.wesapiens.models.Question;

import org.w3c.dom.Text;

import java.util.Iterator;

public class QuestionDialogFragment extends DialogFragment {

    public View view;
    private Unbinder unbinder;
    private String mAnswer;
    private String mQuestionTitle;
    private String[] mQuestionBody;
    private int mQuestionListSize;
    private int mCurrentBodyPosition = 0;

    @BindView(R.id.question_dialog_title)
    TextView questionDialogTitle;

    @BindView(R.id.question_dialog_body)
    TextView questionDialogBody;

    @BindView(R.id.question_edittext)
    EditText questionEditText;

    @BindView(R.id.question_dialog_frame)
    FrameLayout questionDialogFrame;

    @OnClick(R.id.question_submit_button)
    public void submitButtonAction(){
        checkAnswer();
    }

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

    public interface QuestionDialogListener {
        void onFinishDialog(String inputText);
    }


    public static QuestionDialogFragment newInstance() {
        QuestionDialogFragment questionDialogFragment = new QuestionDialogFragment();
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
        questionDialogTitle.setText(mQuestionTitle);
        questionDialogBody.setText(mQuestionBody[0]);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        questionEditText.requestFocus();
        getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        QuestionDialogListener listener = (QuestionDialogListener) getActivity();
        listener.onFinishDialog(questionEditText.getText().toString());
        super.onDismiss(dialog);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    public void setQuestionDialogTitle(String title){
        mQuestionTitle = title;
    }

    public void setQuestionDialogBody(String[] body){
        mQuestionBody = body;
    }

    public void setQuestionAnswer(String answer){
        mAnswer = answer;
    }

    public void setQuestionListSize(int size){
        mQuestionListSize = size;
    }

    private void checkAnswer(){
        dismiss();
    }

    private void nextPage(){
        if(mCurrentBodyPosition < mQuestionBody.length -1){
            mCurrentBodyPosition++;
            questionDialogBody.setText(mQuestionBody[mCurrentBodyPosition]);
        }
        if(mCurrentBodyPosition == mQuestionBody.length -1){
            questionEditText.setVisibility(View.VISIBLE);
        }
    }

    private void previousPage(){
        if(mCurrentBodyPosition > 0){
            mCurrentBodyPosition--;
            questionDialogBody.setText(mQuestionBody[mCurrentBodyPosition]);
        }
    }

    public void inflateError(){

    }

}
