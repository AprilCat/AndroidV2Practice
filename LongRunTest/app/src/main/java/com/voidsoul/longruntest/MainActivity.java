package com.voidsoul.longruntest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button running = (Button) findViewById(R.id.running_service);
        running.setOnClickListener(this);
        Button stop = (Button) findViewById(R.id.stop_service);
        stop.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.running_service:
                Intent i = new Intent(this, LongRunningService.class);
                startService(i);
                break;
            case R.id.stop_service:
                Intent si = new Intent(this, LongRunningService.class);
                stopService(si);
                break;
            default:
                break;
        }
    }
}