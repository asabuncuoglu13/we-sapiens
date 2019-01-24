package com.alpay.wesapiens.models;

import java.util.ArrayList;
import java.util.List;

public class Frame {

    int frameID;
    String frameName;
    String frameImageName;
    String[] frameQuestion;
    String[] frameAnswer;

    public Frame() {

    }

    public Frame(int frameID, String frameName, String frameImageName, String[] questions, String[] answers) {
        this.frameID = frameID;
        this.frameName = frameName;
        this.frameImageName = frameImageName;
        this.frameQuestion = questions;
        this.frameAnswer = answers;
    }

    public int getFrameID() {
        return frameID;
    }

    public void setFrameID(int frameID) {
        this.frameID = frameID;
    }

    public String getFrameName() {
        return frameName;
    }

    public void setFrameName(String frameName) {
        this.frameName = frameName;
    }

    public String getFrameImageName() {
        return frameImageName;
    }

    public void setFrameImageName(String frameImageName) {
        this.frameImageName = frameImageName;
    }

    public String[] getFrameQuestion() {
        return frameQuestion;
    }

    public void setFrameQuestion(String[] frameQuestion) {
        this.frameQuestion = frameQuestion;
    }

    public String[] getFrameAnswer() {
        return frameAnswer;
    }

    public void setFrameAnswer(String[] frameAnswer) {
        this.frameAnswer = frameAnswer;
    }
}
