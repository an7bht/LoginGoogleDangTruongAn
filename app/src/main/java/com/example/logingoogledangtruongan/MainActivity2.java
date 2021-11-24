package com.example.logingoogledangtruongan;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;

public class MainActivity2 extends AppCompatActivity {
    ImageView img, imgMin, img2;
    ImageView btnPlay;
    ProgressBar progress;
    boolean i =false;
    private  ServiceMyClass mServiceMyClass;
    private boolean isServiceConnected=false;
    int x;
    int totalTime;
    TextView txtProgress;
    TextView txtTotalTime;

    private ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            ServiceMyClass.MyBinder myBinder = (ServiceMyClass.MyBinder) service;
            mServiceMyClass = myBinder.getServiceMyClass();
            // mServiceMyClass.start();
            //  mServiceMyClass.setMusic(x);
            //  mServiceMyClass.play();
            isServiceConnected =true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

            isServiceConnected =false;
        }
    };




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        start();
        setContentView(R.layout.activity_main2);

        img = findViewById(R.id.img_rotage);
        imgMin = findViewById(R.id.img_rotage_min);
        img2 = findViewById(R.id.imageView4);
        btnPlay = findViewById(R.id.imgStart);
        progress = findViewById(R.id.progressBarBB);

        x =R.raw.cuoithoi_masew;

        totalTime = MediaPlayer.create(getBaseContext(), x).getDuration();
        Date date = new Date(totalTime);
        txtProgress = findViewById(R.id.txtProcess);
        txtTotalTime =findViewById(R.id.txtTotaltime);
        String minutes = date.getMinutes() > 9 ? date.getMinutes() + "" : "0" + date.getMinutes();
        String second = date.getSeconds() > 9 ? date.getSeconds() + "" : "0" + date.getSeconds();
        txtTotalTime.setText("" + milliSecondsToTimer((long) totalTime));
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.anim_rotage);
        Animation animationStop = AnimationUtils.loadAnimation(this, R.anim.anim_rotage_stop);
        Animation animationMin = AnimationUtils.loadAnimation(this, R.anim.anim_rotage_min);
        Animation animationMinStop = AnimationUtils.loadAnimation(this, R.anim.anim_rotage_min_stop);
        Animation animation2 = AnimationUtils.loadAnimation(this, R.anim.anim_rotage_img2);


            img2.startAnimation(animation2);
//        img.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                img.startAnimation(animation);
//            }
//        });
        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity2.this, "Service đã được tạo!", Toast.LENGTH_SHORT).show();

                if(i){
                    if(isServiceConnected){
                        mServiceMyClass.pause();
                        txtProgress.post(mUpdateTime);
                        i=false;}
                    img.startAnimation(animationStop);
                    imgMin.startAnimation(animationMinStop);

                }

                else {

                    if(isServiceConnected){
                        mServiceMyClass.setMusic(x);
                        mServiceMyClass.play();
                        progress.post(mUpdateProgress);
                        txtProgress.post(mUpdateTime);
                        i=true;
                        img.startAnimation(animation);
                        imgMin.startAnimation(animationMin);
                    }
                }

            }
        });
    }



    public void start(){
        Intent intent = new Intent(this, ServiceMyClass.class);
        bindService(intent, mServiceConnection, Context.BIND_AUTO_CREATE);


    }
    public void stop(){
        if(isServiceConnected){
            unbindService(mServiceConnection);
            isServiceConnected =false;

        }

    }

    private Runnable mUpdateProgress = new Runnable() {
        public void run() {
            int currentDuration;
            if(isServiceConnected){
                if (mServiceMyClass.getMP().isPlaying()) {
                    currentDuration = mServiceMyClass.getMP().getCurrentPosition();
                    double time = currentDuration * 1.0 / totalTime * 100;
                    progress.setProgress((int) time);
                    progress.postDelayed(this, 1000);
                }else {
                    progress.removeCallbacks(this);
                }
            }

        }
    };

    private Runnable mUpdateTime = new Runnable() {
        public void run() {
            int currentDuration;
            if(isServiceConnected){
                if (mServiceMyClass.getMP().isPlaying()) {
                    currentDuration = mServiceMyClass.getMP().getCurrentPosition();
                    updatePlayer(currentDuration);
                    txtProgress.postDelayed(this, 1000);
                }else {
                    txtProgress.removeCallbacks(this);
                }
            }

        }
    };

    private void updatePlayer(int currentDuration){
        txtProgress.setText("" + milliSecondsToTimer((long) currentDuration));
    }

    public  String milliSecondsToTimer(long milliseconds) {
        String finalTimerString = "";
        String secondsString = "";

        // Convert total duration into time
        int hours = (int) (milliseconds / (1000 * 60 * 60));
        int minutes = (int) (milliseconds % (1000 * 60 * 60)) / (1000 * 60);
        int seconds = (int) ((milliseconds % (1000 * 60 * 60)) % (1000 * 60) / 1000);
        // Add hours if there
        if (hours > 0) finalTimerString += (hours > 9 ? hours : "0" + hours) + ":";
        finalTimerString += (minutes > 9 ? minutes : "0" + minutes) + ":";
        finalTimerString += (seconds > 9 ? seconds : "0" + seconds);

        // return timer string
        return finalTimerString;
    }


}