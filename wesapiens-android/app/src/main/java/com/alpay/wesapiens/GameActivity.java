package com.alpay.wesapiens;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.alpay.wesapiens.fragments.QuestionDialogFragment;
import com.alpay.wesapiens.models.Frame;
import com.alpay.wesapiens.models.FrameHelper;
import com.alpay.wesapiens.utils.Utils;

import java.util.List;

public class GameActivity extends AppCompatActivity  implements QuestionDialogFragment.QuestionDialogListener {

    QuestionDialogFragment questionDialogFragment;
    List<Frame> frameList;
    int currentFramePosition;

    @BindView(R.id.chapter_imageview)
    ImageView chapterImage;

    @OnClick(R.id.back_button)
    public void backButtonAction(){
        super.onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        ButterKnife.bind(this);
        frameList = FrameHelper.listAll();
        questionDialogFragment = QuestionDialogFragment.newInstance();
        prepareView(frameList.get(currentFramePosition));
    }

    /*@Override
    protected void onStart() {
        QuestionAPIController questionAPIController = new QuestionAPIController(this);
        questionAPIController.start();
        super.onStart();
    }*/

    public void prepareView(Frame frame) {
        chapterImage.setImageDrawable(Utils.getDrawableWithName(this, frame.getFrameStartImage()));
        questionDialogFragment.setQuestionDialogTitle(frame.getFrameName());
        questionDialogFragment.setQuestionDialogBody(frame.getFrameQuestionArray());
        questionDialogFragment.setQuestionAnswer(frame.getFrameAnswer());
        questionDialogFragment.setCancelable(false);
        questionDialogFragment.show(getSupportFragmentManager(), "questionDialogFragment");
    }

    @Override
    public void onFinishDialog(String inputText) {
        Utils.showWarningToast(this, inputText, Toast.LENGTH_SHORT);
        chapterImage.setImageDrawable(Utils.getDrawableWithName(this, frameList.get(currentFramePosition).getFrameEndImage()));
        nextFrame();
    }

    private void nextFrame(){
        currentFramePosition++;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(frameList.get(currentFramePosition) != null){
                    prepareView(frameList.get(currentFramePosition));
                }
            }
        }, 3000);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            hideSystemUI();
        }
    }

    private void hideSystemUI() {
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN);
    }
}
