package com.example.i16014.tugaskelompok01;

import android.os.CountDownTimer;

/**
 * Created by user on 05/10/2018.
 */

public class Countdown {

    public CountDownTimer ctTimer;
    public int timeLeftMillis = 120000;

    public void startCountDown(){
        this.ctTimer = new CountDownTimer(this.timeLeftMillis, 1000) {
            @Override
            public void onTick(long l) {

            }

            @Override
            public void onFinish() {

            }
        }.start();
    }

}