package com.example.thread02072019;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Thread thread1 , thread2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                showLog("Thread A");
            }
        });
        thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                showLog("Thread B");
            }
        });
        thread2.start();
        thread1.start();
    }
    synchronized private void showLog(String threadName){
        for (int i = 0 ; i<= 1000 ; i++){
            Log.d("BBB",threadName + " " + i + " start");
        }

    }
}
