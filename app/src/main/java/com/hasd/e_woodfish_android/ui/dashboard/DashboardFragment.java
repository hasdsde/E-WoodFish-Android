package com.hasd.e_woodfish_android.ui.dashboard;

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
import androidx.fragment.app.Fragment;

import com.android.volley.VolleyError;
import com.hasd.e_woodfish_android.R;
import com.hasd.e_woodfish_android.databinding.FragmentDashboardBinding;
import com.hasd.e_woodfish_android.ui.entity.Item;
import com.hasd.e_woodfish_android.ui.login.LoginActivity;
import com.hasd.e_woodfish_android.utils.Api;
import com.hasd.e_woodfish_android.utils.DialogUtl;
import com.hasd.e_woodfish_android.utils.ToastUtil;

import java.util.ArrayList;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;

public class DashboardFragment extends Fragment implements View.OnClickListener {
    private TextView userInfo;
    private Button Refresh;
    private SharedPreferences preferences;
    private static final String TAG = "Item_Page";
    private FragmentDashboardBinding binding;
    private Button click1;
    private Button click2;
    private Button click3;
    private TextView text1;
    private TextView text2;
    private TextView text3;
    private TextView desc1;
    private TextView desc2;
    private TextView desc3;
    private Integer score;
    private String username;
    private Item item3;
    private Item item2;
    private Item item1;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        preferences = getActivity().getSharedPreferences("UserData", Context.MODE_PRIVATE);
        binding = FragmentDashboardBinding.inflate(inflater, container, false);

        userInfo = binding.tvUserInfo;
        Refresh = binding.btnRefresh;
        click1 = binding.itemClick1;
        click2 = binding.itemClick2;
        click3 = binding.itemClick3;
        text1 = binding.itemText1;
        text2 = binding.itemText2;
        text3 = binding.itemText3;
        desc1 = binding.itemDesc1;
        desc2 = binding.itemDesc2;
        desc3 = binding.itemDesc3;
        username = preferences.getString("username", "");
        score = preferences.getInt("score", 0);
        userInfo.setText(String.format("%s,分数: %d", username, score));
        Refresh.setOnClickListener(this);
        click1.setOnClickListener(this);
        click2.setOnClickListener(this);
        click3.setOnClickListener(this);

        Api.sendGetRequest("/api/item/getAll", getActivity(), new Api.VolleyCallback() {

            @Override
            public void onSuccess(String result) {
                JSONObject res = JSONUtil.parseObj(result);
                JSONArray data = res.getJSONArray("data");
                ArrayList<Item> items = new ArrayList<>();
                for (int i = 0; i < data.size(); i++) {
                    JSONObject itemData = data.getJSONObject(i);
                    Item item = new Item();
                    item.setId(itemData.getInt("id"));
                    item.setName(itemData.getStr("name"));
                    item.setDescription(itemData.getStr("description"));
                    item.setCost(itemData.getInt("cost"));
                    items.add(item);
                }
                item1 = items.get(0);
                item2 = items.get(1);
                item3 = items.get(2);
                text1.setText(item1.getName());
                text2.setText(item2.getName());
                text3.setText(item3.getName());
                desc1.setText(item1.getDescription() + "\n 花费:" + item1.getCost());
                desc2.setText(item2.getDescription() + "\n 花费:" + item2.getCost());
                desc3.setText(item3.getDescription() + "\n 花费:" + item3.getCost());

            }

            @Override
            public void onError(VolleyError error) {

            }
        });


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

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_Refresh:
                ToastUtil.showToast(getActivity(), "刷新完成");//笑死
                break;
            case R.id.item_click1:
                if (score > item1.getCost()) {
                    Api.sendGetRequest(String.format("/api/cost/one?username=%s&cost=%d&itemid=%d&palt=4",
                            username, item1.getCost(), item1.getId()), getActivity(), new Api.VolleyCallback() {
                        @Override
                        public void onSuccess(String result) {
                            JSONObject res = JSONUtil.parseObj(result);
                            Log.d(TAG, "logs " + res);
                            if (res.getStr("code").toString().equals("200")) {
                                DialogUtl.showDialog(getActivity(), "佛不在乎");
                                ToastUtil.showToast(getActivity(), "此处应该有音乐");
                                score = score - item1.getCost();
                                SharedPreferences.Editor editor = preferences.edit();
                                editor.putInt("score", score);
                                editor.apply();
                                userInfo.setText(String.format("%s,分数: %d", username, score));
                            }
                        }

                        @Override
                        public void onError(VolleyError error) {

                        }
                    });
                } else {
                    ToastUtil.showToast(getActivity(), "功德不足！");
                }
                break;
            case R.id.item_click2:
                if (score > item2.getCost()) {
                    Api.sendGetRequest(String.format("/api/cost/one?username=%s&cost=%d&itemid=%d&palt=4",
                            username, item2.getCost(), item2.getId()), getActivity(), new Api.VolleyCallback() {
                        @Override
                        public void onSuccess(String result) {
                            JSONObject res = JSONUtil.parseObj(result);
                            Log.d(TAG, "logs " + res);
                            if (res.getStr("code").toString().equals("200")) {
                                DialogUtl.showDialog(getActivity(), "韩明不在乎");
                                ToastUtil.showToast(getActivity(), "此处应该有音乐");
                                score = score - item2.getCost();
                                SharedPreferences.Editor editor = preferences.edit();
                                editor.putInt("score", score);
                                editor.apply();
                                userInfo.setText(String.format("%s,分数: %d", username, score));
                            }
                        }

                        @Override
                        public void onError(VolleyError error) {

                        }
                    });
                } else {
                    ToastUtil.showToast(getActivity(), "功德不足！");
                }
                break;
            case R.id.item_click3:
                if (score > item3.getCost()) {
                    Api.sendGetRequest(String.format("/api/cost/one?username=%s&cost=%d&itemid=%d&palt=4",
                            username, item3.getCost(), item3.getId()), getActivity(), new Api.VolleyCallback() {
                        @Override
                        public void onSuccess(String result) {
                            JSONObject res = JSONUtil.parseObj(result);
                            Log.d(TAG, "logs " + res);
                            if (res.getStr("code").toString().equals("200")) {
                                DialogUtl.showDialog(getActivity(), "宝不在乎");
                                ToastUtil.showToast(getActivity(), "此处应该有音乐");
                                score = score - item3.getCost();
                                SharedPreferences.Editor editor = preferences.edit();
                                editor.putInt("score", score);
                                editor.apply();
                                userInfo.setText(String.format("%s,分数: %d", username, score));
                            }
                        }

                        @Override
                        public void onError(VolleyError error) {

                        }
                    });
                } else {
                    ToastUtil.showToast(getActivity(), "功德不足！");
                }
                break;
        }
    }
}