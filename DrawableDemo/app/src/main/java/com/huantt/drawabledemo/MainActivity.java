package com.huantt.drawabledemo;

import android.graphics.drawable.ClipDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    private ImageView imgPicture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        intitComponet();
    }

    private void intitComponet() {
        imgPicture = (ImageView) findViewById(R.id.img_picture);
        (new Thread(new Runnable() {
            @Override
            public void run() {
                int level = 0;
                boolean isOpen = true;
                final ClipDrawable clipDrawable = (ClipDrawable) imgPicture.getDrawable();
                while (true) {
                    if (isOpen == true) {
                        if (level == 10000) {
                            isOpen = false;
                        } else
                            level += 50;
                    } else {
                        if (level == 0) {
                            isOpen = true;
                        } else
                            level -= 50;
                    }
                    Log.d("TAG", String.valueOf(level));
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
        })).start();
    }
}
