package com.example.thread02072019;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Thread thread1, thread2, thread3;
    int a, b, c = 0;
    Laco laco = new Laco(0);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (laco) {
                    for (int i = 0; ; ) {
                        if (laco.getVitri() == 0) {
                            a = i;
                            Log.d("BBB", "Gia tri cua A : " + a + " , vi tri :" + i);
                            laco.setVitri(1);
                            laco.notifyAll();
                            i++;
                        }else if (laco.getVitri() == 3){
                            return;
                        }else {
                            try {
                                laco.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        });
        thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (laco) {
                    for (int i = 0; ; ) {
                        if (laco.getVitri() == 1) {
                            b = i;
                            Log.d("BBB", "Gia tri cua B : " + b + " , vi tri :" + i);
                            laco.setVitri(2);
                            laco.notifyAll();
                            i++;
                        }else if (laco.getVitri() == 3){
                            return;
                        } else {
                            try {
                                laco.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                }

            }
        });
        thread3 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (laco) {
                    for (int i = 0; i < 100; ) {
                        if (laco.getVitri() == 2) {
                            c = a + b;
                            Log.d("BBB", "Gia tri cua C : " + c + " , vi tri :" + i);
                            laco.setVitri(0);
                            laco.notifyAll();
                            i++;
                            if (i == 100){
                                laco.setVitri(3);
                                return;
                            }
                        } else {
                            try {
                                laco.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        });

        thread2.start();
        thread1.start();
        thread3.start();

    }

}
