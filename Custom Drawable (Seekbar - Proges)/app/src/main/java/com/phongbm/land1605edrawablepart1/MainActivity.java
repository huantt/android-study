package com.phongbm.land1605edrawablepart1;

import android.graphics.drawable.ClipDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private ImageView imgPicture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeComponents();
    }

    private void initializeComponents() {
        imgPicture = (ImageView) findViewById(R.id.img_picture);
        final ClipDrawable clipDrawable = (ClipDrawable) imgPicture.getDrawable();

        new Thread(new Runnable() {
            @Override
            public void run() {
                int level = 0;
                boolean isOpen = true;
                while (true) {
                    if (isOpen) {
                        level += 50;
                        if (level == 10000) {
                            isOpen = false;
                        }
                    } else {
                        level -= 50;
                        if (level == 0) {
                            isOpen = true;
                        }
                    }

                    final int l = level;
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            clipDrawable.setLevel(l);
                        }
                    });

                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }


}