package com.hasd.e_woodfish_android.ui.login;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.VolleyError;
import com.hasd.e_woodfish_android.R;
import com.hasd.e_woodfish_android.utils.Api;
import com.hasd.e_woodfish_android.utils.DialogUtl;
import com.hasd.e_woodfish_android.utils.ToastUtil;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText et_username;
    private EditText et_password;
    private Button btn_login;
    private Button btn_register;
    private final String TAG = "LOGIN";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        et_username = findViewById(R.id.et_username);
        et_password = findViewById(R.id.et_password);
        btn_login = findViewById(R.id.btn_login);
        btn_register = findViewById(R.id.btn_register);
        btn_login.setOnClickListener(this);
        btn_register.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        String username = et_username.toString();
        String password = et_password.toString();
        switch (view.getId()) {
            case R.id.btn_register:
                ToastUtil.showToast(this, "你点击了注册");
                Api.sendGetRequest("/api/user/top", this, new Api.VolleyCallback() {
                    @Override
                    public void onSuccess(String result) {
                        Log.d(TAG, "onSuccess: " + result);
                    }

                    @Override
                    public void onError(VolleyError error) {

                    }
                });
                break;
            case R.id.btn_login:
                DialogUtl.showDialog(this, "提示", "你点击了登录");
                break;
        }
    }
}