package com.example.intent;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;

public class SubActivity extends AppCompatActivity implements View.OnClickListener{
    private TextView textView,textView1;
    private Button button,btplay,btstop,btservice;
    private InnerServiceConnection mConnection;
    private Iplayer mPlayer;
    private String TAG = "SubActivity";

    private class InnerServiceConnection implements ServiceConnection{

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.d(TAG, "onServiceConnected: ");
           mPlayer = (Iplayer) service;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        textView = findViewById(R.id.sub_text);
        textView1 = findViewById(R.id.sub_text1);
        RegisterButonListener();

        Intent intent = this.getIntent();
        textView.setText(intent.getStringExtra("activity"));

        Bundle bundle = intent.getExtras();
        String string = bundle.getString("activity");
        textView1.setText(string);
        mConnection = new InnerServiceConnection();
    }


    private void RegisterButonListener(){
        button = findViewById(R.id.sub_bt);

        btplay = findViewById(R.id.sub_play);
        btstop = findViewById(R.id.sub_stop);
        btservice = findViewById(R.id.service);
        button.setOnClickListener(this);
        btplay.setOnClickListener(this);
        btstop.setOnClickListener(this);
        btservice.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch(v.getId()){
            case R.id.sub_bt:
                intent.setClass(this,MainActivity.class);
                startActivity(intent);
                //intent.setClass(this,MyService.class);
                //stopService(intent);
                break;
            case R.id.service:
                intent.setClass(this,MyService.class);
                bindService(intent,mConnection,BIND_AUTO_CREATE);
                break;
            case R.id.sub_play:
                try {
                    mPlayer.play();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.sub_stop:
                try {
                    mPlayer.pause();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            default:
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

}
