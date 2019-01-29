package com.alpay.wesapiens;

import android.content.DialogInterface;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.alpay.wesapiens.fragments.QuestionDialogFragment;
import com.alpay.wesapiens.models.Frame;
import com.alpay.wesapiens.models.FrameHelper;
import com.alpay.wesapiens.utils.Utils;

import java.util.List;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GameActivity extends AppCompatActivity implements QuestionDialogFragment.QuestionDialogListener {

    QuestionDialogFragment questionDialogFragment;
    List<Frame> frameList;
    int currentFramePosition;
    boolean cancelPreparation = false;

    @BindView(R.id.chapter_imageview)
    ImageView chapterImage;

    @BindView(R.id.timeplace_layout)
    LinearLayout timePlaceLayout;

    @OnClick(R.id.back_button)
    public void backButtonAction() {
        quitGame();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        ButterKnife.bind(this);
        timePlaceLayout.bringToFront();
        frameList = FrameHelper.listAll();
        questionDialogFragment = QuestionDialogFragment.newInstance();
        prepareView(frameList.get(currentFramePosition));
    }

    // To sync with FirebaseDB
    /*@Override
    protected void onStart() {
        QuestionAPIController questionAPIController = new QuestionAPIController(this);
        questionAPIController.start();
        super.onStart();
    }*/

    private void quitGame() {
        Resources resources = getResources();
        cancelPreparation = true;
        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case DialogInterface.BUTTON_POSITIVE:
                        GameActivity.super.onBackPressed();
                        break;
                    case DialogInterface.BUTTON_NEGATIVE:
                        cancelPreparation = false;
                        break;
                    default:
                        break;
                }
            }
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(resources.getString(R.string.are_you_sure))
                .setPositiveButton(resources.getString(R.string.yes), dialogClickListener)
                .setNegativeButton(resources.getString(R.string.no), dialogClickListener).show();
    }

    public void prepareView(Frame frame) {
        Utils.playSound(this, R.raw.strongwind);
        chapterImage.setImageDrawable(Utils.getDrawableWithName(this, frame.getFrameStartImage()));
        new Handler().postDelayed(() -> {
            if (!cancelPreparation) {
                hideTimePlaceLayout();
            }
        }, 4500);
        new Handler().postDelayed(() -> {
            if (!cancelPreparation) {
                questionDialogFragment.setQuestionDialogTitle(frame.getFrameName());
                questionDialogFragment.setQuestionDialogBody(frame.getFrameQuestionArray());
                questionDialogFragment.setQuestionAnswer(frame.getFrameAnswer());
                questionDialogFragment.setCancelable(false);
                questionDialogFragment.show(getSupportFragmentManager(), "questionDialogFragment");
            }
        }, 5000);

    }

    @Override
    public void onFinishDialog(String inputText) {
        Utils.showWarningToast(this, inputText, Toast.LENGTH_SHORT);
        chapterImage.setImageDrawable(Utils.getDrawableWithName(this, frameList.get(currentFramePosition).getFrameEndImage()));
        nextFrame();
    }

    private void nextFrame() {
        currentFramePosition++;
        new Handler().postDelayed(() -> {
            if (frameList.get(currentFramePosition) != null) {
                Utils.stopMediaPlayer();
                showTimePlaceLayout();
                prepareView(frameList.get(currentFramePosition));
            }
        }, 3000);
    }

    private void showTimePlaceLayout() {
        timePlaceLayout.animate().alpha(1.0f).setDuration(500);
    }

    private void hideTimePlaceLayout() {
        timePlaceLayout.animate().alpha(0.0f).setDuration(1000);
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
