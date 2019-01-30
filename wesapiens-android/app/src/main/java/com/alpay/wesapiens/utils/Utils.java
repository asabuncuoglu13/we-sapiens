package com.alpay.wesapiens.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Environment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.alpay.wesapiens.R;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.util.Arrays;

import androidx.appcompat.app.AppCompatActivity;

public class Utils {

    public static String SP_START_KEY_IS_PRESSED = "startKeyIsPressed";
    private static MediaPlayer mp;
    private static boolean mp_active = true;
    public static final int PICK_PHOTO = 1;

    public static boolean isConnected() throws InterruptedException, IOException {
        String command = "ping -c 1 google.com";
        return (Runtime.getRuntime().exec(command).waitFor() == 0);
    }

    public static void playSound(AppCompatActivity appCompatActivity, int soundID){
        if(mp_active){
            mp = MediaPlayer.create(appCompatActivity, soundID);
            mp.setLooping(true);
            mp.start();
        }
    }

    public static void stopMediaPlayer(){
        mp.stop();
        mp.release();
    }

    public static void muteMedia(){
        mp_active = false;
        mp.setVolume(0.0f,0.0f);
    }

    public static void openSoundMedia(){
        mp_active = true;
        mp.setVolume(1.0f,1.0f);
    }

    public static void showOKDialog(AppCompatActivity activity, int stringID) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setMessage(stringID)
                .setCancelable(true)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //do things
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }

    public static void showOKDialog(final AppCompatActivity activity, int stringID, final Intent intent) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setMessage(stringID)
                .setCancelable(true)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        if (intent != null) {
                            activity.startActivity(intent);
                        }
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }

    public static boolean isCameraAvailable(AppCompatActivity appCompatActivity) {
        PackageManager pm = appCompatActivity.getPackageManager();
        if (pm.hasSystemFeature(PackageManager.FEATURE_CAMERA)) {
            return true;
        }
        return false;
    }

    public static void addStringToSharedPreferences(AppCompatActivity appCompatActivity, String key, String value) {
        SharedPreferences settings = appCompatActivity.getSharedPreferences("wesapiens", 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(key, value);
        editor.commit();
    }

    public static void addIntegerToSharedPreferences(AppCompatActivity appCompatActivity, String key, int value) {
        SharedPreferences settings = appCompatActivity.getSharedPreferences("wesapiens", 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt(key, value);
        editor.commit();
    }

    public static void addBooleanToSharedPreferences(AppCompatActivity appCompatActivity, String key, boolean value) {
        SharedPreferences settings = appCompatActivity.getSharedPreferences("wesapiens", 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }

    public static String getStringFromSharedPreferences(AppCompatActivity appCompatActivity, String key) {
        SharedPreferences settings = appCompatActivity.getSharedPreferences("wesapiens", 0);
        return settings.getString(key, "").toString();
    }

    public static int getIntegerFromSharedPreferences(AppCompatActivity appCompatActivity, String key) {
        SharedPreferences settings = appCompatActivity.getSharedPreferences("wesapiens", 0);
        return settings.getInt(key, 0);
    }

    public static boolean getBooleanFromSharedPreferences(AppCompatActivity appCompatActivity, String key) {
        SharedPreferences settings = appCompatActivity.getSharedPreferences("wesapiens", 0);
        return settings.getBoolean(key, false);
    }

    public static byte[] serialize(Object obj) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ObjectOutputStream os = new ObjectOutputStream(out);
        os.writeObject(obj);
        return out.toByteArray();
    }

    public static void showErrorToast(AppCompatActivity activityCompat, int stringID, int duration) {
        LayoutInflater inflater = activityCompat.getLayoutInflater();
        View layout = inflater.inflate(R.layout.toast_error, (ViewGroup) activityCompat.findViewById(R.id.error_toast_container));
        TextView text = (TextView) layout.findViewById(R.id.error_toast_text);
        text.setText(activityCompat.getResources().getString(stringID));
        Toast toast = new Toast(activityCompat.getApplicationContext());
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        toast.setDuration(duration);
        toast.setView(layout);
        toast.show();
    }

    public static void showWarningToast(AppCompatActivity activityCompat, int stringID, int duration) {
        LayoutInflater inflater = activityCompat.getLayoutInflater();
        View layout = inflater.inflate(R.layout.toast_warning, (ViewGroup) activityCompat.findViewById(R.id.warning_toast_container));
        TextView text = (TextView) layout.findViewById(R.id.warning_toast_text);
        text.setText(activityCompat.getResources().getString(stringID));
        Toast toast = new Toast(activityCompat.getApplicationContext());
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        toast.setDuration(duration);
        toast.setView(layout);
        toast.show();
    }

    public static void showWarningToast(AppCompatActivity activityCompat, String text, int duration) {
        LayoutInflater inflater = activityCompat.getLayoutInflater();
        View layout = inflater.inflate(R.layout.toast_warning, (ViewGroup) activityCompat.findViewById(R.id.warning_toast_container));
        TextView textview = (TextView) layout.findViewById(R.id.warning_toast_text);
        textview.setText(text);
        Toast toast = new Toast(activityCompat.getApplicationContext());
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        toast.setDuration(duration);
        toast.setView(layout);
        toast.show();
    }


    public static Drawable getDrawableWithName(Context context, String fileName) {
        Drawable drawable;
        try {
            String[] assetFileNames = context.getAssets().list("frame");
            if (Arrays.asList(assetFileNames).contains(fileName)) {
                InputStream inputStream = context.getAssets().open("frame/" + fileName);
                drawable = Drawable.createFromStream(inputStream, null);
                inputStream.close();
            } else {
                String directoryPath = Environment.getExternalStorageDirectory().getAbsolutePath();
                if (!directoryPath.isEmpty()) {
                    directoryPath += "/wesapiens/drawable";
                } else {
                    directoryPath = "storage/self/primary/wesapiens/drawable";
                }
                File file = new File(directoryPath + "/" + fileName);
                drawable = Drawable.createFromPath(file.getAbsolutePath());
            }
            return drawable;
        } catch (IOException ex) {
            return null;
        }
    }

    public static void pickImage(AppCompatActivity appCompatActivity) {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        appCompatActivity.startActivityForResult(intent, PICK_PHOTO);
    }

    public static void saveImageDrawable(Drawable drawable){

    }

    public static Drawable drawableFromInputStream(InputStream inputStream){
        return Drawable.createFromStream(inputStream, null);
    }

}
