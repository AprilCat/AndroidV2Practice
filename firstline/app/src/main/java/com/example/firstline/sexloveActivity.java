package com.example.firstline;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class sexloveActivity extends BaseActivity {
    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        intent.putExtra("data_return", "fuck again cqq");
        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("sexloveActivity", "onRestart: ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("sexloveActivity", "onDestroy: ");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sexlove_layout);
        Intent intent = getIntent();
        String data = intent.getStringExtra("extra_data");
        Log.d("sexloveActivity", "onCreate: " + data);
        Log.d("sexloveActivity", "Task id is " + getTaskId());
        Toast.makeText(sexloveActivity.this, data, Toast.LENGTH_SHORT).show();
        Button button2 = (Button) findViewById(R.id.button_2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(sexloveActivity.this, ThirdActivity.class);
                startActivity(intent);
//                intent.putExtra("data_return", "hello cqq");
//                setResult(RESULT_OK, intent);
//                finish();
            }
        });
    }
    public static void actionStart(Context context, String data1, String data2){
        Intent intent = new Intent(context, sexloveActivity.class);
        intent.putExtra("param1", data1);
        intent.putExtra("param2", data2);
        context.startActivity(intent);
    }
}