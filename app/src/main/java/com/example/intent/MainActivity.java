package com.example.intent;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText mEditText;
    private String TAG = "MainActivity_log";
    BroadcastReceiver  mBroadcastReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEditText = findViewById(R.id.edt);
        RegisterButtonListener();
        mBroadcastReceiver = new MyReceiver();

    }


    private void RegisterButtonListener(){
        findViewById(R.id.bt1).setOnClickListener(this);
        findViewById(R.id.bt2).setOnClickListener(this);
        findViewById(R.id.bt3).setOnClickListener(this);
        findViewById(R.id.bt4).setOnClickListener(this);
        findViewById(R.id.bt5).setOnClickListener(this);
        findViewById(R.id.bt6).setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()){
            case R.id.bt1:
                intent.setClass(this,SubActivity.class);
                intent.putExtra("activity","通过intent 传过来的值");
                startActivity(intent);
                Toast.makeText(MainActivity.this, "点击bt1", Toast.LENGTH_SHORT).show();
                break;
            case R.id.bt2:
                intent.setClass(this,SubActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("activity","通过bundle方法传过来的值");
                intent.putExtras(bundle);
                startActivity(intent);
                Toast.makeText(MainActivity.this, "点击bt2", Toast.LENGTH_SHORT).show();
                break;
            case R.id.bt3:
                IntentFilter intentFilter = new IntentFilter();
                intentFilter.addAction("com.example.intent.MainActivity");
                registerReceiver(mBroadcastReceiver,intentFilter);
                //intent.setClass(this,MyService.class);
                //startService(intent);
                Toast.makeText(MainActivity.this, "registerReceiver(mBroadcastReceiver,intentFilter", Toast.LENGTH_SHORT).show();
                break;
            case R.id.bt4:
                unregisterReceiver(mBroadcastReceiver);
                //intent.setClass(this,MyService.class);
                //stopService(intent);
                Toast.makeText(MainActivity.this, "unregisterReceiver(mBroadcastReceiver)", Toast.LENGTH_SHORT).show();
                break;
            case R.id.bt5:
                intent.setAction("com.example.intent.MainActivity");
                intent.putExtra("MainActivity",mEditText.getText().toString());
                sendBroadcast(intent);
                Toast.makeText(MainActivity.this, "点击bt5", Toast.LENGTH_SHORT).show();
                break;
            case R.id.bt6:
                intent.setAction("com.example.intent.MainActivity");
                sendOrderedBroadcast(intent,null);
                Toast.makeText(MainActivity.this, "点击bt6", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
     }
}
