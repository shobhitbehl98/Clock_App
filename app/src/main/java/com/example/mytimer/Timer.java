package com.example.mytimer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class Timer extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);
        getWindow().setStatusBarColor(getResources().getColor(R.color.status));

    }

    public void alarm_(View view){
        Intent it=new Intent(this,Alarm.class);
        startActivity(it);
    }
    public void stopwatch_(View view){
        Intent it=new Intent(this,MainActivity.class);
        startActivity(it);
    }

}