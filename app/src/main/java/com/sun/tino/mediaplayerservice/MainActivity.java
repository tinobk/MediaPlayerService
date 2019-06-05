package com.sun.tino.mediaplayerservice;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity{

    private Intent mIntent;
    static final String ACTION_PLAY = "play_music";
    static final String ACTION_PAUSE = "pause_music";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void stopMusic(View view) {
        mIntent = new Intent(MainActivity.this, ServiceMp3Player.class);
        this.stopService(mIntent);
    }

    public void pauseMusic(View view) {
        mIntent = new Intent(MainActivity.this, ServiceMp3Player.class);
        mIntent.setAction(ACTION_PAUSE);
        this.startService(mIntent);
    }

    public void playMusic(View view) {
        mIntent = new Intent(MainActivity.this, ServiceMp3Player.class);
        mIntent.setAction(ACTION_PLAY);
        this.startService(mIntent);
    }
}
