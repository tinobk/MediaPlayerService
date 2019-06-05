package com.sun.tino.mediaplayerservice;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Process;

import java.util.Objects;

public class ServiceMp3Player extends Service {
    private static final int MSG_PLAY_MUSIC = 100;
    private static final int MSG_PAUSE_MUSIC = 101;
    private MediaPlayer mMediaPlayer;
    private ServiceHandler mServiceHandler;

    private final class ServiceHandler extends Handler {
        ServiceHandler(Looper looper){
            super(looper);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what == MSG_PLAY_MUSIC) mMediaPlayer.start();
            else if(msg.what == MSG_PAUSE_MUSIC) mMediaPlayer.pause();
        }
    }

    public ServiceMp3Player() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mMediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.ailanguoithuongem);
        mMediaPlayer.setLooping(true);
        mMediaPlayer.setVolume(100,100);
        HandlerThread thread = new HandlerThread("ServiceStartArguments", Process.THREAD_PRIORITY_BACKGROUND);
        thread.start();
        Looper looper = thread.getLooper();
        mServiceHandler = new ServiceHandler(looper);
    }

    @SuppressLint("NewApi")
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if(intent != null){
            Message message;
            if (Objects.equals(intent.getAction(), MainActivity.ACTION_PLAY)){
                message = new Message();
                message.what = MSG_PLAY_MUSIC;
                mServiceHandler.sendMessage(message);
            }
            else if (Objects.equals(intent.getAction(), MainActivity.ACTION_PAUSE)){
                message = new Message();
                message.what = MSG_PAUSE_MUSIC;
                mServiceHandler.sendMessage(message);
            }
        }
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(mMediaPlayer != null) mMediaPlayer.release();
    }
}
