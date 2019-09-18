package com.example.kanmeitu.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.WindowManager;

import com.example.kanmeitu.MainActivity;
import com.example.kanmeitu.R;
import com.example.kanmeitu.util.SharedPreferencesUtil;

public class SplashActivity extends AppCompatActivity {

private Handler handler=new Handler(){
    @Override
    public void handleMessage(@NonNull Message msg) {
        super.handleMessage(msg);
        next();
    }
};
    private SharedPreferencesUtil sp;
private void next(){
    Intent intent=null;
    if (sp.isLogin()){
         intent=new Intent(SplashActivity.this, MainActivity.class);
    }else {
         intent=new Intent(SplashActivity.this,LoginActivity.class);

    }
    startActivity(intent);
    finish();
}
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
         sp = SharedPreferencesUtil.getInstance(getApplicationContext());
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                handler.sendEmptyMessage(0);
            }
        },3000);
    }
}
