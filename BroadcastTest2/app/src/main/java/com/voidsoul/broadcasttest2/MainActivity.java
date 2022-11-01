package com.voidsoul.broadcasttest2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView textView;
    private AnotherBroadcastReceiver anotherBroadcastReceiver;
    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(anotherBroadcastReceiver);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.textview111);
        IntentFilter intentFilter = new IntentFilter();
        anotherBroadcastReceiver = new AnotherBroadcastReceiver();
        intentFilter.addAction("com.voidsoul.broadcasttest.MY_BROADCAST");

//        intentFilter.setPriority(100);
        registerReceiver(anotherBroadcastReceiver, intentFilter);
    }
}