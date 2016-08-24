package com.huantt.crazymath;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class PlayActivity extends AppCompatActivity implements View.OnClickListener, Runnable {
    private static final int UPDATE_TEXT_VIEW = 0;
    private static final int MAX_TIME_COUNT_DOWN = 4;
    private static final int GAME_OVER = 1;
    private TextView txtScores;
    private TextView txtTimeCountDown;
    private TextView txtPhepTinh;
    private ImageView imgYes;
    private ImageView imgNo;
    private int num1;
    private int num2;
    private int sum;
    private Random random;
    private int score;
    private boolean isRuning;
    private int timeCountDown;
    private int answer;
    private Handler handler;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        initComponent();
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case UPDATE_TEXT_VIEW:
                        timeCountDown--;
                        txtTimeCountDown.setText(String.valueOf(timeCountDown));
                        break;
                    case GAME_OVER:
                        toastMsg();
                        break;
                }
            }
        };
        initListener();
        new Thread(this).start();
    }

    private void initComponent() {
        random = new Random();
        txtScores = (TextView) findViewById(R.id.txt_score);
        txtTimeCountDown = (TextView) findViewById(R.id.txt_time_count_down);
        txtPhepTinh = (TextView) findViewById(R.id.txt_phep_tinh);
        imgYes = (ImageView) findViewById(R.id.img_yes);
        imgNo = (ImageView) findViewById(R.id.img_no);
        makeQuesion();
        isRuning = true;

    }

    private void initListener() {
        imgNo.setOnClickListener(this);
        imgYes.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_no:
                if (answer != sum) {
                    score++;
                    makeQuesion();
                    Toast.makeText(this, "TRUE", Toast.LENGTH_SHORT).show();
                    txtScores.setText(String.valueOf(score));
                } else{
                    isRuning = false;
                    Toast.makeText(this, "FALSE", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.img_yes:
                if (answer == sum) {
                    score++;
                    makeQuesion();
                    Toast.makeText(this, "TRUE", Toast.LENGTH_SHORT).show();
                    txtScores.setText(String.valueOf(score));
                } else {
                    isRuning = false;
                    Toast.makeText(this, "FALSE", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }


    @Override
    public void run() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        while (true) {
            if (timeCountDown == 0 || isRuning ==false) {
                handler.sendEmptyMessage(GAME_OVER);
                break;
            } else {
                handler.sendEmptyMessage(UPDATE_TEXT_VIEW);
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }

    private void makeQuesion() {
        num2 = random.nextInt(10);
        num1 = random.nextInt(10);
        sum = num1 + num2;
        if (random.nextBoolean()) {
            answer = sum;
        } else answer = sum - 3 + random.nextInt(4);
        txtPhepTinh.setText(num1 + " + " + num2 + " = " + answer);
        timeCountDown = MAX_TIME_COUNT_DOWN;
        txtTimeCountDown.setText(String.valueOf(MAX_TIME_COUNT_DOWN));
        Log.d("PHEP TINH: " , sum + " - " + answer);
    }

    private void toastMsg() {
        Toast.makeText(this, "GAME OVER", Toast.LENGTH_LONG).show();
    }
}
