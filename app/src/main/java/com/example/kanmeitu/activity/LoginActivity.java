package com.example.kanmeitu.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.SyncStateContract;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.kanmeitu.MainActivity;
import com.example.kanmeitu.R;
import com.example.kanmeitu.util.Constants;
import com.example.kanmeitu.util.RegexUtil;
import com.example.kanmeitu.util.SharedPreferencesUtil;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{
private EditText et_username;
private EditText et_password;
private SharedPreferencesUtil sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        et_username=findViewById(R.id.et_username);
        et_password=findViewById(R.id.et_password);
        Button bt_login=findViewById(R.id.bt_login);
        bt_login.setOnClickListener(this);
        sp = SharedPreferencesUtil.getInstance(getApplicationContext());

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.bt_login:
                login();
                break;
        }
    }
    public void login(){
        //获取用户输入的邮箱，密码，做校验
        String username=et_username.getText().toString().trim();
        //判断是否输入了邮箱
        if (TextUtils.isEmpty(username)){
            Toast.makeText(this,R.string.email_hint,Toast.LENGTH_SHORT).show();
            return;
        }
        if (!RegexUtil.isEmail(username)) {
            Toast.makeText(this, R.string.email_error, Toast.LENGTH_SHORT).show();
            return;
        }
        String password = et_password.getText().toString().trim();

        //判断是否输入了密码
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, R.string.password_hint, Toast.LENGTH_SHORT).show();
            return;
        }

        //判断密码长度是否为6~15位
        if (password.length() < 6 || password.length() > 15) {
            Toast.makeText(this, R.string.password_length_error, Toast.LENGTH_SHORT).show();
            return;
        }

        //TODO 在这里就调用调用服务端的登录接口
        //我们这里就简单实现，将密码和用户名都写到本地了
        if ((Constants.PHONE.equals(username) && Constants.PASSWORD.equals(password))) {
            //TODO 通常软件的做法是，这里登录完成后保存一个标致，下次就不用在登录了
            //我们这里就不讲解这么多了，因为涉及到的知识太多了
         sp.setLogin(true);
            //登录成功，进入首页
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);

            //关闭当前界面
            finish();
        }else {
            //登录失败，进行提示
            //用户提示友好了对攻击你的人来说，就更方便了，所以这里大家做一个权衡
            Toast.makeText(this, R.string.username_or_password_error, Toast.LENGTH_SHORT).show();
        }
    }
    }

