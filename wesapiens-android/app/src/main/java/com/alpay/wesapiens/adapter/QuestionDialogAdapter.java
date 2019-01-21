package com.alpay.wesapiens.adapter;

import com.alpay.wesapiens.R;
import com.alpay.wesapiens.fragments.QuestionDialogFragment;
import com.alpay.wesapiens.models.Question;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;

public class QuestionDialogAdapter {

    AppCompatActivity appCompatActivity;
    List<Question> questionList;
    QuestionDialogFragment questionDialogFragment;

    public QuestionDialogAdapter(AppCompatActivity activity) {
        appCompatActivity = activity;
        populateQuestionList();
        questionDialogFragment = QuestionDialogFragment.newInstance();
        questionList = Question.questionList;
    }

    public void showResponseError() {
        questionDialogFragment.inflateError();
    }

    public QuestionDialogFragment prepareQuestionFragment(int position) {
        questionDialogFragment.setQuestionDialogTitle(questionList.get(position).getTitle());
        questionDialogFragment.setQuestionDialogBody(questionList.get(position).getBodyTextArray());
        questionDialogFragment.setQuestionAnswer(questionList.get(position).getAnswer());
        return questionDialogFragment;
    }

    public void populateQuestionList(List<Question> questions) {
        questionList = questions;
        questionDialogFragment.setQuestionListSize(questionList.size());
    }

    private void populateQuestionList(){
        Question question = new Question(
                        appCompatActivity.getResources().getString(R.string.question1_title),
                        appCompatActivity.getResources().getStringArray(R.array.question1_body),
                        appCompatActivity.getResources().getString(R.string.question1_answer));
        Question.questionList.add(question);
    }
}
