package com.example.mytimer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Chronometer;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView cm;
    private boolean running;
    long tMillisec,tstart,tBuff,tUpdate=0;
    int sec,min,millisec;
    LinearLayout container;
    Handler handler;
    TextView txtValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setStatusBarColor(getResources().getColor(R.color.status));
        cm=findViewById(R.id.stopwatch);
        container=findViewById(R.id.container);
        handler=new Handler();



    }

    public Runnable runnable=new Runnable() {
        @Override
        public void run() {
            tMillisec=SystemClock.uptimeMillis()-tstart;
            tUpdate=tBuff+tMillisec;
            sec=(int)(tUpdate/1000);
            min=sec/60;
            sec=sec%60;
            millisec=(int)(tUpdate%1000);
            Log.d("seconds",String.valueOf(millisec));
            cm.setText(String.format("%02d",min)+":"+String.format("%02d",sec)+":"+String.format("%03d",millisec));
            handler.postDelayed(this,0);
        }
    };

    public void startcm(View v){
        if(!running){
            handler.postDelayed(runnable,0);
            tstart=SystemClock.uptimeMillis();
//            Log.d("startTime",String.valueOf(tstart));
            running=true;
        }else{
            tBuff+=tMillisec;
            handler.removeCallbacks(runnable);
            running=false;
        }
    }

    public void resetcm(View v){
//        tstart=SystemClock.uptimeMillis();
//        handler.postDelayed(runnable,0);
        handler.removeCallbacks(runnable);
        cm.setText("00:00:000");
        tBuff=0L;
        tMillisec=0L;
        tstart=0L;
        sec=0;
        min=0;
        millisec=0;
        running=false;
        container.removeAllViews();
    }

    public void lapcm(View v){
        LayoutInflater inflater=(LayoutInflater)getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View addView=inflater.inflate(R.layout.nextlayout,null);
        txtValue=(TextView)addView.findViewById(R.id.txtContainer);
        txtValue.setText(cm.getText());
        container.addView(addView);
    }

    public void alarm(View v){
       Intent gotoAlarm=new Intent(this,Alarm.class);
       startActivity(gotoAlarm);
    }

    public void timer(View v){
        Intent gotoTimer=new Intent(this,Timer.class);
        startActivity(gotoTimer);
    }
}