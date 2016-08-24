package com.huantt.example;

import android.graphics.Color;

import java.util.Random;

/**
 * Created by Huan on 8/22/2016.
 */

public class Star {
    private static final int LEFT = 0;
    private static final int RIGHT = 1;
    private static final int TOP = 2;
    private static final int BOTTOM = 3;
    public static String STAR = "*";
    private int orient;
    private int x;
    private int y;
    private int size;
    private int color;
    private Random random = new Random();


    public Star(int orient, int x, int y, int size, int color) {
        this.orient = orient;
        this.x = x;
        this.y = y;
        this.size = size;
        this.color = color;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getSize() {
        return size;
    }

    public int getColor() {
        return color;
    }

    public void move(int widthScreen, int heightScreen) {
        color = Color.rgb(random.nextInt(255),random.nextInt(255),random.nextInt(255));
        switch (orient) {
            case LEFT:
                if (x == 0) {
                    y = random.nextInt(heightScreen);
                    x = random.nextInt(widthScreen);
                }
                x--;
                break;
            case RIGHT:
                if (x == widthScreen) {
                    y = random.nextInt(heightScreen);
                    x = random.nextInt(widthScreen);
                }
                x++;
                break;
            case TOP:
                if (y == 0) {
                    y = random.nextInt(heightScreen);
                    x = random.nextInt(widthScreen);
                }
                y--;
                break;
            case BOTTOM:
                if (y == heightScreen) {
                    y = random.nextInt(heightScreen);
                    x = random.nextInt(widthScreen);
                }
                y++;
                break;
        }
    }
}
