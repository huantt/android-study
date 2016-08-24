package com.huantt.example;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Huan on 8/22/2016.
 */

public class Sky extends View implements Runnable {
    private Paint paint;
    private ArrayList<Star> stars;
    private int widthScreen;
    private int heightScreen;
    private Random r = new Random();
    private static int NUM_OF_STARS = 48;
    private int withScreen;
    private int heigthScren;

    public Sky(Context context) {
        super(context);
        setup();
    }

    public Sky(Context context, AttributeSet attrs) {
        super(context, attrs);
        setup();

    }

    public Sky(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setup();

    }

    private void setup() {
        paint = new Paint();
        stars = new ArrayList<>();
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        withScreen = metrics.widthPixels;
        heigthScren = metrics.heightPixels;
        for (int i = 0; i < NUM_OF_STARS; i++) {
            stars.add(new Star(r.nextInt(4), r.nextInt(withScreen), r.nextInt(heigthScren), r.nextInt(100 + r.nextInt(144)), Color.rgb(r.nextInt(255),r.nextInt(255),r.nextInt(255))));
        }
        new Thread(this).start();

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (int i = 0; i < NUM_OF_STARS; i++) {
            Log.i("INFOR", String.valueOf(stars.get(i).getX()));
            paint.setColor(stars.get(i).getColor());
            paint.setTextSize(stars.get(i).getSize());
            canvas.drawText(Star.STAR, stars.get(i).getX(), stars.get(i).getY(), paint);
        }
    }

    @Override
    public void run() {
        while (true) {
            for (int i = 0; i < NUM_OF_STARS; i++) {
                stars.get(i).move(withScreen, heigthScren);
            }
            postInvalidate();
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
