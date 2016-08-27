package com.huantt.asynctask;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Huan on 8/24/2016.
 */

public class TwoActivity extends Activity implements View.OnClickListener {
    TextView edtNhap;
    Button btnSend;

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.activity_two);
        initCOmponent();
    }

    private void initCOmponent() {
        edtNhap = (TextView) findViewById(R.id.txt_noi_dung);
        btnSend = (Button) findViewById(R.id.btn_back);
        btnSend.setOnClickListener(this);
        Intent intent = getIntent();
        edtNhap.setText(intent.getStringExtra("Noi dung"));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_back:
                break;
        }
    }
}
