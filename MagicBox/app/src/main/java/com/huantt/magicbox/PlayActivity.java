package com.huantt.magicbox;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class PlayActivity extends AppCompatActivity implements View.OnClickListener, Runnable {
    private static final int WIN = 5;
    private static final int LOSE = 1;
    private static final int UPDATE_TIME = 3;
    private static final int UPDATE_SUM = 4;
    Button[][] buttons;
    TextView txtScore;
    TextView txtTime;
    private int score;
    private Random random;
    private int timeCountDown = 10;
    private Handler handler;
    private int sum;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        initComponent();
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what) {
                    case UPDATE_TIME:
                        timeCountDown--;
                        txtTime.setText(String.valueOf(timeCountDown));
                        break;
                    case WIN:
                        toast("YOU WIN");
                        break;
                    case LOSE:
                        toast("YOU LOSE");
                        break;
                    case UPDATE_SUM:
                        txtScore.setText(String.valueOf(sum));
                        break;

                }
            }
        };
        new Thread(this).start();
    }

    private void initComponent() {
        random = new Random();
        buttons = new Button[3][3];
        buttons[0][0] = (Button) findViewById(R.id.btn_00);
        buttons[0][1] = (Button) findViewById(R.id.btn_01);
        buttons[0][2] = (Button) findViewById(R.id.btn_02);
        buttons[1][0] = (Button) findViewById(R.id.btn_10);
        buttons[1][1] = (Button) findViewById(R.id.btn_11);
        buttons[1][2] = (Button) findViewById(R.id.btn_12);
        buttons[2][0] = (Button) findViewById(R.id.btn_20);
        buttons[2][1] = (Button) findViewById(R.id.btn_21);
        buttons[2][2] = (Button) findViewById(R.id.btn_22);
        buttons[0][0].setOnClickListener(this);
        buttons[0][1].setOnClickListener(this);
        buttons[0][2].setOnClickListener(this);
        buttons[1][0].setOnClickListener(this);
        buttons[1][1].setOnClickListener(this);
        buttons[1][2].setOnClickListener(this);
        buttons[2][0].setOnClickListener(this);
        buttons[2][1].setOnClickListener(this);
        buttons[2][2].setOnClickListener(this);
        txtScore = (TextView) findViewById(R.id.txt_score);
        txtTime = (TextView) findViewById(R.id.txt_time);
    }

    @Override
    public void onClick(View v) {
        score = 1 + random.nextInt(10);
        if (((Button) v).getText() != "") {
            sum -= Integer.parseInt(String.valueOf(((Button) v).getText()));
        }
        sum += score;
        handler.sendEmptyMessage(UPDATE_SUM);
        ((Button) v).setBackgroundColor(0xff64DD17);
        ((Button) v).setText(String.valueOf(score));
        if (sum >= 50) {
            handler.sendEmptyMessage(WIN);
        }
    }

    @Override
    public void run() {
        try {
            Thread.sleep(1000);
            while (true) {
                if (timeCountDown == 0) {
                    handler.sendEmptyMessage(LOSE);
                    break;
                } else {
                    handler.sendEmptyMessage(UPDATE_TIME);
                }
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private void toast(String content) {
        Toast.makeText(this, content, Toast.LENGTH_SHORT).show();

    }
}
