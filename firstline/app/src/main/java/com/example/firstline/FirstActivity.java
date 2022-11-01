package com.example.firstline;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class FirstActivity extends BaseActivity {
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.add_item:
                Toast.makeText(FirstActivity.this, "push dick into cqq's vagina", Toast.LENGTH_SHORT).show();
                break;
            case R.id.remove_item:
                Toast.makeText(FirstActivity.this, "pull dick from cqq's vagina", Toast.LENGTH_SHORT).show();
                break;
            default:
        }
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    String returnData = data.getStringExtra("data_return");
                    Toast.makeText(FirstActivity.this, returnData, Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("FirstActivity", "onRestart: ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("FirstActivity", "onDestroy: ");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_layout);
        Button button = (Button) findViewById(R.id.button_1);
        Log.d("FirstActivity", "onCreate: " + this.toString());
        Log.d("FirstActivity", "Task id is " + getTaskId());
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(FirstActivity.this, "cum shot inside cqq's vagina", Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(FirstActivity.this, sexloveActivity.class);
//                intent.putExtra("extra_data", "fuck");
//                startActivityForResult(intent, 1);
                sexloveActivity.actionStart(FirstActivity.this, "fuck", "dick");
            }
        });
    }
}