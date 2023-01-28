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
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.hasd.e_woodfish_android.databinding.FragmentHomeBinding;
import com.hasd.e_woodfish_android.ui.login.LoginActivity;
import com.hasd.e_woodfish_android.utils.ToastUtil;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private TextView userInfo;
    private Button loginOut;
    private SharedPreferences preferences;
    private static final String TAG = "Home_Page";

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        preferences = getActivity().getSharedPreferences("UserData", Context.MODE_PRIVATE);
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        userInfo = binding.tvUserInfo;
        loginOut = binding.btnLoginOut;
        userInfo.setText("测试");
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
        return binding.getRoot();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        preferences = getActivity().getSharedPreferences("UserData", Context.MODE_PRIVATE);
        //检测是否存在记录
        String username = preferences.getString("username", "");
        Integer score = preferences.getInt("score", 0);
        if (username.isEmpty()) {
            JumpToLogin();
        } else {
            ToastUtil.showToast(getActivity(), "载入信息成功");
        }
        Log.d(TAG, "onCreate: " + username);

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