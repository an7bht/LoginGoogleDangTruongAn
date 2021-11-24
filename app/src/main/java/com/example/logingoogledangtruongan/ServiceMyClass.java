package com.example.logingoogledangtruongan;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;


public class ServiceMyClass extends Service {
    MediaPlayer mp;
    int paused=-1;
    private final MyBinder myBinder =new MyBinder();

    public class MyBinder extends Binder {
       public ServiceMyClass getServiceMyClass(){
           return ServiceMyClass.this;
        }
    }
    @Override
    public IBinder onBind(Intent intent) {
        Log.e("Service","onbinder");
        return myBinder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.e("Service","unbinder");
        return super.onUnbind(intent);
    }

    @Override
    public void onCreate() {
        super.onCreate();

        Toast.makeText(this, "Service đã được tạo!", Toast.LENGTH_SHORT).show();
        Log.e("Service","create");

    }
    public void start(){
        if(mp!=null)
            mp.start();
        Log.e("Service","start");

    }
    public void pause(){

        mp.pause();
        paused = mp.getCurrentPosition();

    }
    public boolean isPlaying(){
        return  mp.isPlaying();
    }
    public void setMusic(int x){

            mp= MediaPlayer.create(getApplicationContext(), x);

        Log.e("Service","setMusic");

    }
    public void play(){

        if (paused == -1) {
            mp.start();
        } else{
            mp.seekTo(paused);
            mp.start();
        }


    }
    @Override
    public void onDestroy() {
        Toast.makeText(this, "Service đã bị hủy!", Toast.LENGTH_SHORT).show();
        Log.e("Service","huy");
        if(mp!=null){
            mp.release();
            mp = null;
        }
    }

    public MediaPlayer getMP(){
        return this.mp;
    }

}
