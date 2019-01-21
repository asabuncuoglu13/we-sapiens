package com.alpay.wesapiens;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import com.alpay.wesapiens.adapter.QuestionDialogAdapter;
import com.alpay.wesapiens.controller.QuestionAPIController;
import com.alpay.wesapiens.fragments.QuestionDialogFragment;
import com.alpay.wesapiens.utils.Utils;

import java.io.IOException;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends AppCompatActivity implements QuestionDialogFragment.QuestionDialogListener {

    QuestionDialogAdapter questionDialogAdapter;

    @BindView(R.id.chapter_imageview)
    ImageView chapterImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        checkInternetConnection();
        showEditDialog();
    }

    @Override
    protected void onStart() {
        QuestionAPIController questionAPIController = new QuestionAPIController(this);
        questionAPIController.start();
        super.onStart();
    }

    private void checkInternetConnection(){
        try {
            if(!Utils.isConnected()) {
                Utils.showErrorToast(this, R.string.internet_connection_error, Toast.LENGTH_LONG);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
}
