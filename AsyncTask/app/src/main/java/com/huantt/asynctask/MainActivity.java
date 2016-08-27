package com.huantt.asynctask;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends Activity implements View.OnClickListener {
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    EditText edtLink;
    Button btnDownload;
    ProgressBar pbDownload;
    ImageView imgPicture;
    private String link;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initComponent();
    }

    private void initComponent() {
        edtLink = (EditText) findViewById(R.id.txt_link);

        btnDownload = (Button) findViewById(R.id.btn_download);
        btnDownload.setOnClickListener(this);

        pbDownload = (ProgressBar) findViewById(R.id.pb_download);
        pbDownload.setMax(100);
        pbDownload.setProgress(0);
        pbDownload.setSecondaryProgress(0); // Giá trị đệm như kiểu youtube ấy

        imgPicture = (ImageView) findViewById(R.id.img_downloaded);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_download:
                link = edtLink.getText().toString();
                if (link.isEmpty()) {
                    Toast.makeText(this, "Nhập link kìa !!!", Toast.LENGTH_SHORT);

                } else {
                    DownloadAsycsTask downloadAsycsTask = new DownloadAsycsTask(imgPicture, pbDownload);
                    downloadAsycsTask.execute(link);
                }
                break;

        }
    }

    private class DownloadAsycsTask extends AsyncTask<String, Integer, Bitmap> {

        private int size;
        private byte bytes[];
        private ImageView imageView;
        private int i = 0;
        private HttpURLConnection connection;
        private ProgressBar progressBar;

        private DownloadAsycsTask(ImageView inputImageView, ProgressBar progressBar) {
            imageView = inputImageView;
            this.progressBar = progressBar;
        }

        @Override

        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Bitmap doInBackground(String... urls) {
            Bitmap imgBitmap;
            try {
                URL url = new URL(urls[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();
                BufferedInputStream bufferedInputStream = new BufferedInputStream(connection.getInputStream());
                size = connection.getContentLength();
                Log.e("AAAAAAAAAAAAAAAAAAAAA", String.valueOf(size));
                bytes = new byte[size];
                int b;
                while ((b = bufferedInputStream.read()) != -1) {
                    bytes[i] = (byte) b;
                    i++;
                    publishProgress(i*100/size);
                }
                imgBitmap = BitmapFactory.decodeByteArray(bytes, 0, size);
                return imgBitmap;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            Log.d("Finish:", String.valueOf(i * 100 / size));
            imageView.setImageBitmap(bitmap);
        }


        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            Log.d("Downloaded:", String.valueOf(i * 100 / size));
            progressBar.setProgress(values[0]);
        }

    }
}
