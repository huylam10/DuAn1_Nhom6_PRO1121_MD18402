package com.example.nhom6_pro1121_md18402.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;

import com.example.nhom6_pro1121_md18402.MainActivity;
import com.example.nhom6_pro1121_md18402.R;

public class Intro extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

       new Handler().postDelayed(new Runnable() {
           @Override
           public void run() {
               Intent inten = new Intent(Intro.this, Login.class);
               startActivity(inten);
               finish();
           }
       },3000);
    }
}