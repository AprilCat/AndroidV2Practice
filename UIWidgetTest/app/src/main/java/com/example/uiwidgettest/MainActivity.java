package com.example.uiwidgettest;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText editText;
    private ImageView imageView;
    private ProgressBar progressBar;
    private Button button2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = (Button) findViewById(R.id.button);
        button2 = (Button) findViewById(R.id.button_2);
        editText = (EditText) findViewById(R.id.edit_text);
        imageView = (ImageView) findViewById(R.id.image_view);
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
        button.setOnClickListener(this);
        button2.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button:
                String fuck_ = editText.getText().toString();
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
                alertDialog.setTitle("fuck");
                alertDialog.setMessage("twice");
                alertDialog.setCancelable(false);
                alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                alertDialog.show();
                imageView.setImageResource(R.drawable.img_2);
                Toast.makeText(MainActivity.this, fuck_, Toast.LENGTH_SHORT).show();
                if(progressBar.getVisibility() == View.GONE){
                    progressBar.setVisibility(View.VISIBLE);
                    int progress = progressBar.getProgress();
                    progress += 10;
                    progressBar.setProgress(progress);
                }else{
                    progressBar.setVisibility(View.GONE);
                }
                break;
            case R.id.button_2:
                ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);
                progressDialog.setTitle("xx");
                progressDialog.setMessage("fucking....");
                progressDialog.setCancelable(true);
                progressDialog.show();
                break;
            default:
                break;
        }
    }
}