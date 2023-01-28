package com.hasd.e_woodfish_android.ui.login;

import android.content.Context;
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
import com.hasd.e_woodfish_android.utils.MyApplication;
import com.hasd.e_woodfish_android.utils.ToastUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private static Context ctx;
    private EditText et_username;
    private EditText et_password;
    private Button btn_login;
    private Button btn_register;
    private final String TAG = "LOGIN_PAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ctx = MyApplication.getContext();
        et_username = findViewById(R.id.et_username);
        et_password = findViewById(R.id.et_password);
        btn_login = findViewById(R.id.btn_login);
        btn_register = findViewById(R.id.btn_register);
        btn_login.setOnClickListener(this);
        btn_register.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        String username = et_username.getText().toString();
        String password = et_password.getText().toString();
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("username", username);
            jsonObject.put("password", password);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        switch (view.getId()) {
            case R.id.btn_register:
                ToastUtil.showToast(this, "你点击了注册");
                break;
            case R.id.btn_login:
                try {
                    Api.sendPostRequest("/api/user/login", jsonObject, this, new Api.VolleyJsonCallback() {
                        @Override
                        public void onSuccess(JSONObject jsonObject) throws JSONException {
                            Log.d(TAG, "onSuccess: " + jsonObject.toString());
                            if (jsonObject.get("code").toString().equals("200")) {
                                Log.d(TAG, "onSuccess: 登录成功");
                                DialogUtl.showDialog(LoginActivity.this, "登录成功");
                            }
                        }

                        @Override
                        public void onError(VolleyError error) {
                            Log.d(TAG, "onError: " + error.toString());
                        }
                    });
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }

                break;
        }
    }
}



















