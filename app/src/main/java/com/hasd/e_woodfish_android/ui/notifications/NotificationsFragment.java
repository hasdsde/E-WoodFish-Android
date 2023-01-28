package com.hasd.e_woodfish_android.ui.notifications;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.hasd.e_woodfish_android.databinding.FragmentNotificationsBinding;
import com.hasd.e_woodfish_android.ui.login.LoginActivity;
import com.hasd.e_woodfish_android.utils.ToastUtil;

public class NotificationsFragment extends Fragment {
    private TextView userInfo;
    private Button Refresh;
    private SharedPreferences preferences;
    private static final String TAG = "Top_Page";
    private FragmentNotificationsBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        preferences = getActivity().getSharedPreferences("UserData", Context.MODE_PRIVATE);
        binding = FragmentNotificationsBinding.inflate(inflater, container, false);
        userInfo = binding.tvUserInfo;
        Refresh = binding.btnRefresh;

        String username = preferences.getString("username", "");
        Integer score = preferences.getInt("score", 0);
        userInfo.setText(String.format("%s,分数: %d", username, score));
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