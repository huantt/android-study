package com.huantt.example;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by Huan on 8/14/2016.
 */

public class ClockAnalogView extends View implements Runnable {

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

    public ClockAnalogView(Context context) {
        super(context);
        setup();
    }

    public ClockAnalogView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setup();
    }

    public ClockAnalogView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setup();
    }

    public void setup() {
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
        int width = getWidth();
        int height = getHeight();
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

        paint.setColor(textColor);
        Rect timeBound = new Rect();
        paint.getTextBounds("TATHUAN", 0, "TATHUAN".length(), timeBound);
        paint.setTextSize(textSize);
        canvas.drawText("TATHUAN", (width / 2 - timeBound.centerX()), height / 2 - min / 2 + bordersize * 6, paint);

        getDay();
        getMinute();
        paint.setColor(Color.WHITE);
        paint.getTextBounds(day, 0, minute.length(), timeBound);
        paint.setTextSize(textSize);
        canvas.drawText(day, (width / 2 - timeBound.centerX()), height / 2 + min / 2 - bordersize * 3, paint);
        paint.setStrokeWidth(5);

        paint.setColor(Color.WHITE);
        for (int i = 0; i < 12; i++) {
            canvas.drawLine(width / 2, height / 2 - min / 2 + bordersize * 2, width / 2, height / 2 - min / 2 + bordersize + 5, paint);
            canvas.rotate(30, width / 2, height / 2);
        }

        paint.setStrokeWidth(15);
        getHours();
        paint.setColor(Color.RED);
        int hh = Integer.parseInt(hours);
        canvas.rotate(30 * hh, width / 2, height / 2);
        canvas.drawLine(width / 2, height / 2 + bordersize, width / 2, height / 2 - min / 2 + 4 * bordersize, paint);
        canvas.rotate(-30 * hh, width / 2, height / 2);

        getMinute();
        paint.setColor(Color.RED);
        int mm = Integer.parseInt(minute);
        canvas.rotate(6 * mm, width / 2, height / 2);
        canvas.drawLine(width / 2, height / 2 + bordersize, width / 2, height / 2 - min / 2 + 3 * bordersize, paint);
        canvas.rotate(-6 * mm, width / 2, height / 2);

        getsecond();
        paint.setColor(Color.WHITE);
        paint.setStrokeWidth(10);
        paint.setColor(Color.WHITE);
        int ss = Integer.parseInt(second);
        canvas.rotate(6 * ss, width / 2, height / 2);
        canvas.drawLine(width / 2, height / 2 + 2 * bordersize, width / 2, height / 2 - min / 2 + 2 * bordersize, paint);

        paint.setColor(Color.WHITE);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(width / 2, height / 2, min / 40, paint);

        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(width / 2, height / 2, min / 80, paint);

    }

    @Override
    public void run() {
        try {
            while (true) {
                getsecond();
                postInvalidate(); //
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
