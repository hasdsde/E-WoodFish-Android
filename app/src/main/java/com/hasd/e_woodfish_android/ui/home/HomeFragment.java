package com.hasd.e_woodfish_android.ui.home;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.android.volley.VolleyError;
import com.hasd.e_woodfish_android.R;
import com.hasd.e_woodfish_android.databinding.FragmentHomeBinding;
import com.hasd.e_woodfish_android.ui.login.LoginActivity;
import com.hasd.e_woodfish_android.utils.Api;
import com.hasd.e_woodfish_android.utils.ToastUtil;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private TextView userInfo;
    private Button loginOut;
    private SharedPreferences preferences;
    private static final String TAG = "Home_Page";
    private ImageView muyu;
    private Button btn_click;
    private Integer score;
    private String username;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        preferences = getActivity().getSharedPreferences("UserData", Context.MODE_PRIVATE);
        binding = FragmentHomeBinding.inflate(inflater, container, false);

        userInfo = binding.tvUserInfo;
        loginOut = binding.btnLoginOut;
        btn_click = binding.btnClick;

        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ImageView muyu = view.findViewById(R.id.iv_muyu);
        muyu.setImageResource(R.drawable.woodfish_purple);
        username = preferences.getString("username", "");
        score = preferences.getInt("score", 0);
        //点击事件
        loginOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("username", "");
                editor.putInt("score", 0);
                editor.apply();
                JumpToLogin();
            }
        });
        btn_click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "username:" + username);
                Log.d(TAG, "userInfo:" + userInfo.getText());
                Api.sendGetRequest("/api/logs/swear?score=1&username=" + username, getActivity(), new Api.VolleyCallback() {
                    @Override
                    public void onSuccess(String result) {
                        JSONObject res = JSONUtil.parseObj(result);
                        if (res.getStr("code").toString().equals("200")) {
                            JSONObject data = res.getJSONObject("data");
                            SharedPreferences.Editor editor = preferences.edit();
                            HomeFragment.this.score = data.getInt("score");
                            editor.putInt("score", HomeFragment.this.score);
                            editor.apply();
                            ToastUtil.showToast(getActivity(), "功德+1");
                            userInfo.setText(String.format("%s,分数: %d", username, HomeFragment.this.score));
                        }
                    }

                    @Override
                    public void onError(VolleyError error) {

                    }
                });
            }
        });
        //检测是否存在记录
        if (username.isEmpty()) {
            JumpToLogin();
        } else {
            ToastUtil.showToast(getActivity(), "载入信息成功");
            userInfo.setText(String.format("%s,分数: %d", username, score));
        }
        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void JumpToLogin() {
        Intent intent = new Intent(getActivity(), LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        ToastUtil.showToast(getActivity(), "需要登录");
    }
}