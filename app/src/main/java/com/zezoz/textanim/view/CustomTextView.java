package com.zezoz.textanim.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;

/**
 * Created by sebad on 6/14/16.
 */
public class CustomTextView extends TextView {

    private static String TAG = "CustomTextView";

    Paint mTextPaint;

    String tnew = "0";
    String tant = "1";

    private float ty;
    private float textSize;

    public CustomTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
        invalidate();
        ty = 0;
        textSize = getTextSize();
    }


    private void init() {
        mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setColor(Color.BLACK);
        mTextPaint.setTextSize(textSize);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        ty = ty - 5;
        if (ty <= - textSize) {
            Log.i(TAG,"TY "+ ty);
            stopAnimation();
        }
        canvas.clipRect(0, 0, textSize, textSize);
        canvas.drawText(tnew, getX(), ty + 100, mTextPaint);
        canvas.drawText(tant, getX(), ty + 200, mTextPaint);

    }

    Handler mHandler = new Handler();
    Runnable mTick = new Runnable() {
        public void run() {
            invalidate();
            mHandler.postDelayed(this, 30); // 20ms == 60fps
        }
    };

    long mAnimStartTime;

    void startAnimation() {
        mAnimStartTime = SystemClock.uptimeMillis();
        mHandler.removeCallbacks(mTick);
        mHandler.post(mTick);
    }

    void stopAnimation() {
        mHandler.removeCallbacks(mTick);
    }

    public void anim() {
        ty = 0;
        startAnimation();
    }

}
