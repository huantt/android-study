package com.huantt.example;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by Huan on 8/14/2016.
 */

public class ClockView extends View implements Runnable {

    private int borderColor;
    private int bordersize;
    private int backgroundColor;
    private int textColor;
    private int textSize;
    public static final int BORDER_COLOR_DEFAULT = 0xff009688;
    public static final int BORDER_SIZE_DEFAULT = 32;
    public static final int BACKGROUND_COLOR_DEFAULT = 0xff4DB6AC;
    public static final int TEXT_COLOR_DEFAULT = 0xffffffff;
    public static final int TEXT_SIZE_DEFAULT = 48;
    private Paint paint;
    private String time;
    private SimpleDateFormat dateFomart;

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

    public ClockView(Context context) {
        super(context);
        setup();
    }

    public ClockView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setup();
    }

    public ClockView(Context context, AttributeSet attrs, int defStyleAttr) {
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
        dateFomart = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        new Thread(this).start();
    }

    private void getTimeNow() {
        time = dateFomart.format(Calendar.getInstance().getTime());
    }

    public String getHours() {
        SimpleDateFormat format1 = new SimpleDateFormat("ss");
        return format1.format(Calendar.getInstance().getTime());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = getWidth();
        int height = getHeight();
        int min = Math.min(width, height);

        paint.setColor(borderColor);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(bordersize);
        canvas.drawCircle(width / 2, height / 2, (min - bordersize) / 2, paint);

        paint.setColor(backgroundColor);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(width / 2, height / 2, min / 2 - bordersize, paint);

        getTimeNow();
        Rect timeBound = new Rect();
        paint.getTextBounds(time, 0, time.length(), timeBound);
        paint.setTextSize(textSize);
        paint.setColor(textColor);
        canvas.drawText(time, (width / 2 - timeBound.centerX()), (height / 2 - timeBound.centerY()), paint);
    }

    @Override
    public void run() {
        try {
            while (true) {
                getTimeNow();
                if (getHours() == "57") {
                    Toast toast = Toast.makeText(getContext(), getHours(), Toast.LENGTH_SHORT);
                    toast.show();
                    System.out.println("HEHE");
                }
                postInvalidate(); //
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
