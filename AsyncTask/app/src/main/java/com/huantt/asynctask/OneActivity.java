package com.huantt.asynctask;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Huan on 8/24/2016.
 */

public class OneActivity extends Activity implements View.OnClickListener {
    EditText edtNhap;
    Button btnSend;

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.activity_one);
        initCOmponent();
    }

    private void initCOmponent() {
        edtNhap = (EditText) findViewById(R.id.txt_nhap);
        btnSend = (Button) findViewById(R.id.btn_send);
        btnSend.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_back:
                String nhap = edtNhap.getText().toString();
                if(nhap.isEmpty()){

                }else {
                    Intent intent = new Intent(getBaseContext(),TwoActivity.class);
//                    intent.setClass(getBaseContext(), TwoActivity.class);
                    intent.putExtra("Noi dung", nhap);
                    startActivity(intent);

                }
                break;
        }
    }
}
