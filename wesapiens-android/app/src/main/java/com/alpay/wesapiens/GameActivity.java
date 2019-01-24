package com.alpay.wesapiens;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.alpay.wesapiens.adapter.QuestionDialogAdapter;
import com.alpay.wesapiens.controller.QuestionAPIController;
import com.alpay.wesapiens.fragments.QuestionDialogFragment;
import com.alpay.wesapiens.utils.Utils;

public class GameActivity extends AppCompatActivity  implements QuestionDialogFragment.QuestionDialogListener {

    QuestionDialogAdapter questionDialogAdapter;

    @BindView(R.id.chapter_imageview)
    ImageView chapterImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        ButterKnife.bind(this);
        showEditDialog();
    }

    @Override
    protected void onStart() {
        QuestionAPIController questionAPIController = new QuestionAPIController(this);
        questionAPIController.start();
        super.onStart();
    }

    private void showEditDialog() {
        FragmentManager fm = getSupportFragmentManager();
        questionDialogAdapter = new QuestionDialogAdapter(this);
        QuestionDialogFragment questionDialogFragment = questionDialogAdapter.prepareQuestionFragment(0);
        questionDialogFragment.setCancelable(false);
        questionDialogFragment.show(fm, "questionDialogFragment");
    }

    private void changeImage(){
        chapterImage.setImageDrawable(getResources().getDrawable(R.drawable.caveman02));
    }

    @Override
    public void onFinishDialog(String inputText) {
        Utils.showWarningToast(this, inputText, Toast.LENGTH_SHORT);
        changeImage();
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
