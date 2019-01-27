package com.alpay.wesapiens.models;

public class Frame {

    int frameID;
    String frameName;
    String frameStartImage;
    String frameEndImage;
    String[] frameQuestionArray;
    String frameAnswer;

    public Frame() {

    }

    public Frame(int frameID, String frameName, String frameStartImage, String frameEndImage, String[] questions, String answer) {
        this.frameID = frameID;
        this.frameName = frameName;
        this.frameStartImage = frameStartImage;
        this.frameEndImage = frameEndImage;
        this.frameQuestionArray = questions;
        this.frameAnswer = answer;
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

    public String getFrameStartImage() {
        return frameStartImage;
    }

    public String getFrameEndImage() {
        return frameEndImage;
    }

    public void setFrameEndImage(String frameEndImage) {
        this.frameEndImage = frameEndImage;
    }

    public void setFrameStartImage(String frameStartImage) {
        this.frameStartImage = frameStartImage;
    }

    public String[] getFrameQuestionArray() {
        return frameQuestionArray;
    }

    public void setFrameQuestionArray(String[] frameQuestionArray) {
        this.frameQuestionArray = frameQuestionArray;
    }

    public String getFrameAnswer() {
        return frameAnswer;
    }

    public void setFrameAnswer(String frameAnswer) {
        this.frameAnswer = frameAnswer;
    }
}
