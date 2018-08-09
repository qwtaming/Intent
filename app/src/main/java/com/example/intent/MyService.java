package com.example.intent;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.Environment;
import android.os.IBinder;
import android.support.v4.media.session.IMediaControllerCallback;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;

import static android.content.ContentValues.TAG;

public class MyService extends Service {
    private MediaPlayer mPlayer;
    public static final String TAG = "MyService_log";

    private class InnerBinder extends Binder implements Iplayer{

        @Override
        public void play(){
            Log.d(TAG, "play: ");
            try {
                mPlayer = new MediaPlayer();
                mPlayer.reset();
                mPlayer.setDataSource(Environment.getExternalStorageDirectory().getAbsolutePath() + "/Music/伍佰-突然的自我.mp3");
                mPlayer.prepare();
                mPlayer.start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        public void pause() {
            mPlayer.release();
        }
    }

    public MyService() {
        Log.d(TAG, "MyService: ");
    }

    @Override
    public void onCreate() {
        Log.d(TAG, "onCreate: ");
        super.onCreate();
        mPlayer = new MediaPlayer();

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand: ");
        //IntentFilter intentFilter = new IntentFilter();
        //intentFilter.addAction("com.example.intent.MainActivity");
        //registerReceiver(mBroadcastReceiver,intentFilter);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onBind: ");
        Toast.makeText(MyService.this, "onBind", Toast.LENGTH_SHORT).show();
        InnerBinder innerBinder = new InnerBinder();
        return innerBinder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d(TAG, "onUnbind: ");
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy: ");
        super.onDestroy();
    }


}
