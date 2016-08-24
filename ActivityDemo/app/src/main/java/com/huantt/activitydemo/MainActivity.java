package com.huantt.activitydemo;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity implements View.OnClickListener, Runnable {
    private static final String TAG = "MainActivity";
    private static final int UPDATE_TEXT_VIEW = 0;
    private EditText edtNumA;
    private EditText edtNumB;
    private Button btnCong;
    private TextView txtNumber;
    private int tong;
    private Handler handler;
    private int time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "Created");
        setContentView(R.layout.activity_main);
        initComponents();
        Thread thread = new Thread(this);
        thread.start();
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case UPDATE_TEXT_VIEW:
                        txtNumber.setText(msg.arg1 + "");
                        break;
                }
            }
        };
    }

    private void initComponents() {
        edtNumA = (EditText) findViewById(R.id.edt_numA);
        edtNumB = (EditText) findViewById(R.id.edt_numB);
        btnCong = (Button) findViewById(R.id.btn_cong);
        txtNumber = (TextView) findViewById(R.id.txt_number);
        btnCong.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int a = Integer.parseInt(edtNumA.getText().toString());
        int b = Integer.parseInt(edtNumB.getText().toString());
        switch (v.getId()) {
            case R.id.btn_cong:
                tong = a + b;
                Log.i("KQ", String.valueOf(tong));
        }
    }

    @Override
    public void run() {
        while (true) {
            Message msg = new Message();
            msg.what = UPDATE_TEXT_VIEW;
            msg.arg1 = time;
            handler.sendMessage(msg);
            time++;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
