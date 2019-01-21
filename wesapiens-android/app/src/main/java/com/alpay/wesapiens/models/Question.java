package com.alpay.wesapiens.models;

import java.util.ArrayList;
import java.util.List;

public class Question {

    public static List<Question> questionList = new ArrayList<>();

    String title;
    String[] bodyTextArray;
    String answer;

    public Question(String title, String[] bodyTextArray, String answer) {
        this.title = title;
        this.bodyTextArray = bodyTextArray;
        this.answer = answer;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String[] getBodyTextArray() {
        return bodyTextArray;
    }

    public void setBodyTextArray(String[] bodyTextArray) {
        this.bodyTextArray = bodyTextArray;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
