package com.alpay.wesapiens.base;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.alpay.wesapiens.R;
import com.alpay.wesapiens.base.FragmentManager;
import com.alpay.wesapiens.utils.Utils;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.IOException;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FragmentHolderActivity extends AppCompatActivity{

    @OnClick(R.id.back_button)
    public void backButtonAction(){
        FragmentManager.openFragment(this, FragmentManager.HOME);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        checkInternetConnection();
        FragmentManager.openFragment(this, FragmentManager.HOME);
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