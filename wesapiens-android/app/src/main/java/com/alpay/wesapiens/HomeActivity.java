package com.alpay.wesapiens;

import android.os.Bundle;
import android.widget.ImageView;

import com.alpay.wesapiens.controller.FirebaseAPIController;
import com.alpay.wesapiens.fragments.QuestionDialogFragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends AppCompatActivity {

    @BindView(R.id.chapter_imageview)
    ImageView chapterImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        showEditDialog();
    }

    @Override
    protected void onStart() {
        FirebaseAPIController firebaseAPIController = new FirebaseAPIController();
        firebaseAPIController.start();
        super.onStart();
    }

    private void showEditDialog() {
        FragmentManager fm = getSupportFragmentManager();
        QuestionDialogFragment editNameDialogFragment = QuestionDialogFragment.newInstance("Some Title");
        editNameDialogFragment.show(fm, "fragment_edit_name");
    }

}
