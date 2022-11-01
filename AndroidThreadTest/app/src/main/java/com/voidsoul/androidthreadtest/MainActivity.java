package com.voidsoul.androidthreadtest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    public static final int UPDATE_TEXT = 1;
    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button changeText = (Button) findViewById(R.id.change_text);
        changeText.setOnClickListener(this);
        textView = (TextView) findViewById(R.id.text);
    }
 
    @SuppressLint("HandlerLeak")
    private final Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case UPDATE_TEXT:
                    textView.setText("Nice to meet you");
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.change_text:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Message message = new Message();
                        message.what = UPDATE_TEXT;
                        handler.sendMessage(message);
                    }
                }).start();
                break;
            default:
                break;
        }
    }


}