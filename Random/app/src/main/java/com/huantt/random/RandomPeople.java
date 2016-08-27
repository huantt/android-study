package com.huantt.random;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;

/**
 * Created by Huan on 8/14/2016.
 */

public class RandomPeople extends View implements Runnable {
    private Boolean play;
    private int borderColor;
    private int bordersize;
    private int backgroundColor;
    private int textColor;
    private int textSize;
    public static final int BORDER_COLOR_DEFAULT = 0xffB0BEC5;
    public static final int BORDER_SIZE_DEFAULT = 32;
    public static final int BACKGROUND_COLOR_DEFAULT = 0xff37474F;
    public static final int TEXT_COLOR_DEFAULT = 0xffE0E0E0;
    public static final int TEXT_SIZE_DEFAULT = 36;
    private Paint paint;
    private String second;
    private String minute;
    private String hours;
    private String day;
    private SimpleDateFormat secondFormat;
    private SimpleDateFormat minuteFormat;
    private SimpleDateFormat hoursFormat;
    private SimpleDateFormat dayFormat;
    private Boolean isRun = false;
    private Handler handler;
    private int do1;
    private int speed = 2;
    private int width;
    private int height;
    private OnClickListener onClickListener;
    private Random ra = new Random();
    private int time ;
    private int k;

    public int getBorderColor() {
        return borderColor;
    }

    public void setBorderColor(int borderColor) {
        this.borderColor = borderColor;
        invalidate();
    }

    public int getBordersize() {
        return bordersize;
    }

    public void setBordersize(int bordersize) {
        this.bordersize = bordersize;
    }

    public int getBackgroundColor() {
        return backgroundColor;
    }

    @Override
    public void setBackgroundColor(int backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public int getTextColor() {
        return textColor;
    }

    public void setTextColor(int textColor) {
        this.textColor = textColor;
    }

    public int getTextSize() {
        return textSize;
    }

    public void setTextSize(int textSize) {
        this.textSize = textSize;
    }

    public RandomPeople(Context context) {
        super(context);
        setup();
    }

    public RandomPeople(Context context, AttributeSet attrs) {
        super(context, attrs);
        setup();
    }

    public RandomPeople(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setup();
    }

    public void setup() {
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if (msg.what == 0) {
                    isRun = false;
                }
            }
        };
        borderColor = BORDER_COLOR_DEFAULT;
        bordersize = BORDER_SIZE_DEFAULT;
        textColor = TEXT_COLOR_DEFAULT;
        textSize = TEXT_SIZE_DEFAULT;
        backgroundColor = BACKGROUND_COLOR_DEFAULT;
        paint = new Paint();
        paint.setAntiAlias(true);
        secondFormat = new SimpleDateFormat("ss");
        minuteFormat = new SimpleDateFormat("mm");
        hoursFormat = new SimpleDateFormat("hh");
        dayFormat = new SimpleDateFormat("dd");
        new Thread(this).start();
    }

    private void getsecond() {
        second = secondFormat.format(Calendar.getInstance().getTime());
    }

    private void getMinute() {
        minute = minuteFormat.format(Calendar.getInstance().getTime());
    }

    private void getHours() {
        hours = hoursFormat.format(Calendar.getInstance().getTime());
    }

    private void getDay() {
        day = dayFormat.format(Calendar.getInstance().getTime());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        width = getWidth();
        height = getHeight();
        int min = Math.min(width, height);
        paint.setColor(0xff263238);
        setBackgroundColor(0xff263238);

        paint.setColor(borderColor);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(bordersize);
        canvas.drawCircle(width / 2, height / 2, (min - bordersize) / 2, paint);

        paint.setColor(backgroundColor);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(width / 2, height / 2, min / 2 - bordersize, paint);
        paint.setColor(Color.WHITE);
        canvas.restore();
        for (int i = 0; i < 10; i++) {
            canvas.drawText(String.valueOf(i * 10) +"%", width / 2, height / 2 - min / 2 + 3 * bordersize, paint);
            canvas.rotate((float) 36, width / 2, height / 2);
        }
        canvas.restore();
        paint.setColor(textColor);
        Rect timeBound = new Rect();
        paint.getTextBounds("AI SẼ UỐNG", 0, "AI SẼ UỐNG".length(), timeBound);
        paint.setTextSize(textSize);
        canvas.drawText("AI SẼ UỐNG", (width / 2 - timeBound.centerX()), height / 2 - min / 2 + bordersize * 6, paint);
        rotate(isRun, canvas);
        canvas.drawCircle(width / 2, height / 2, min / 40, paint);
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(width / 2, height / 2, min / 80, paint);

    }

    @Override
    public void run() {
        float do2 = 5;
        try {
            while (true) {
                if (isRun) {
                    Log.i("Tag", "RUNNNNNNNNNNN");
                    if (time == 0) {
                        isRun = false;
                    } else
                        time--;
                    if (time >= k / 2) {
                        do2 = 5;
                    } else do2 = 3;
                    if (do1 >= 360) {
                        do1 = 0;
                    } else {
                        do1 += do2;
                        speed = 10;
                    }
                    postInvalidate(); //
                    Thread.sleep(2);
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void rotate(Boolean isRun, Canvas canvas) {
        if (true) {
            canvas.rotate(do1, width / 2, height / 2);
        }
    }


    public void onClick() {
        isRun = true;
        time = 10 * 100 + ra.nextInt(100 * 5);
        k = time;
        postInvalidate();
    }
}
