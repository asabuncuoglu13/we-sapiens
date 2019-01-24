package com.alpay.wesapiens.models;

import java.util.ArrayList;
import java.util.List;

public class Frame {

    static List<Frame> frameList = new ArrayList<>();

    int frameID;
    String frameName;
    String imageName;
    String frameQuestion;
    String frameAnswer;

    public Frame() {
    }

    public static List<Frame> listAll(){
        if(frameList.isEmpty()){
            frameList.add(new Frame(1,"Frame Name 1", "Image Name 1", "Question 1", "Answer 1"));
            frameList.add(new Frame(2,"Frame Name 1", "Image Name 1", "Question 1", "Answer 1"));
            frameList.add(new Frame(3,"Frame Name 1", "Image Name 1", "Question 1", "Answer 1"));
            frameList.add(new Frame(4,"Frame Name 1", "Image Name 1", "Question 1", "Answer 1"));
            frameList.add(new Frame(5,"Frame Name 1", "Image Name 1", "Question 1", "Answer 1"));
        }
        return frameList;
    }

    public Frame(int frameID, String frameName, String imageName, String question, String answer) {
        this.frameID = frameID;
        this.frameName = frameName;
        this.imageName = imageName;
        this.frameQuestion = question;
        this.frameAnswer = answer;
    }

    public String getImageName() {
        return imageName;
    }

    public int getFrameID() {
        return frameID;
    }

    public String getFrameName() {
        return frameName;
    }

    public String getFrameQuestion() {
        return frameQuestion;
    }

    public void setFrameQuestion(String frameQuestion) {
        this.frameQuestion = frameQuestion;
    }

    public String getFrameAnswer() {
        return frameAnswer;
    }

    public void setFrameAnswer(String frameAnswer) {
        this.frameAnswer = frameAnswer;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public void setFrameID(int frameID) {
        this.frameID = frameID;
    }

    public void setFrameName(String frameName) {
        this.frameName = frameName;
    }
}
