package com.alpay.wesapiens.view;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;

import org.zakariya.flyoutmenu.FlyoutMenuView;

import androidx.annotation.ColorInt;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class PhysicsFlyoutMenu {

    public static class MenuItem extends FlyoutMenuView.MenuItem {

        int drawableCode;
        AppCompatActivity appCompatActivity;
        TextPaint textPaint;

        public MenuItem(AppCompatActivity appCompatActivity, int id, int drawableCode, float size, @ColorInt int color) {
            super(id);
            this.drawableCode = drawableCode;
            this.appCompatActivity = appCompatActivity;
            textPaint = new TextPaint();
            textPaint.setTextSize(size);
            textPaint.setTextAlign(Paint.Align.CENTER);
            textPaint.setStyle(Paint.Style.FILL);
            textPaint.setColor(color);
        }

        public int getDrawableCode() {
            return drawableCode;
        }

        public void setDrawableCode(int drawableCode) {
            this.drawableCode = drawableCode;
        }

        @Override
        public void onDraw(Canvas canvas, RectF bounds, float degreeSelected) {
            Drawable drawable = ContextCompat.getDrawable(appCompatActivity, drawableCode);
            Bitmap bitmap = convertToBitmap(drawable, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
            canvas.drawBitmap(bitmap, bounds.centerX(), bounds.centerY() - ((textPaint.descent() + textPaint.ascent()) / 2), textPaint);
            canvas.drawText("Hello", bounds.centerX(), bounds.centerY() - (textPaint.descent() + textPaint.ascent() * 2), textPaint);
        }

        public Bitmap convertToBitmap(Drawable drawable, int widthPixels, int heightPixels) {
            Bitmap mutableBitmap = Bitmap.createBitmap(widthPixels, heightPixels, Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(mutableBitmap);
            drawable.setBounds(0, 0, widthPixels, heightPixels);
            drawable.draw(canvas);

            return mutableBitmap;
        }

    }
}