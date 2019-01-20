package com.alpay.wesapiens.models;

public class Question {

    String title;
    String text;
    String answer;

    public Question(String title, String text, String answer) {
        this.title = title;
        this.text = text;
        this.answer = answer;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
