package com.hasd.e_woodfish_android.ui.notifications;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.hasd.e_woodfish_android.R;
import com.hasd.e_woodfish_android.databinding.FragmentNotificationsBinding;

public class NotificationsFragment extends Fragment {
    private TextView userInfo;
    private Button Refresh;
    private SharedPreferences preferences;
    private static final String TAG = "Top_Page";
    private FragmentNotificationsBinding binding;
    private TableLayout table;
    private TableRow tableRow;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        preferences = getActivity().getSharedPreferences("UserData", Context.MODE_PRIVATE);
        binding = FragmentNotificationsBinding.inflate(inflater, container, false);
        userInfo = binding.tvUserInfo;
        Refresh = binding.btnRefresh;
        table = binding.tlInfo;


        tableRow = new TableRow(getActivity());
        tableRow.setGravity(Gravity.CENTER);
        TextView textView1 = new TextView(getActivity());
        TextView textView2 = new TextView(getActivity());
        TextView textView3 = new TextView(getActivity());
        
        textView1.setText("1");
        textView2.setText("由Java添加的代码");
        textView3.setText("100");
        textView1.setTextSize(15);
        textView1.setTypeface(null, Typeface.BOLD);
        textView1.setPadding(20, 10, 20, 10);
        textView1.setTextColor(getResources().getColor(R.color.white));
        textView1.setBackgroundColor(getResources().getColor(R.color.purple_200));
        textView1.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

        textView2.setTextSize(15);
        textView2.setTypeface(null, Typeface.BOLD);
        textView2.setPadding(20, 10, 20, 10);
        textView2.setTextColor(getResources().getColor(R.color.white));
        textView2.setBackgroundColor(getResources().getColor(R.color.purple_200));
        textView2.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

        textView3.setTextSize(15);
        textView3.setTypeface(null, Typeface.BOLD);
        textView3.setPadding(20, 10, 20, 10);
        textView3.setTextColor(getResources().getColor(R.color.white));
        textView3.setBackgroundColor(getResources().getColor(R.color.purple_200));
        textView3.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

        tableRow.addView(textView1);
        tableRow.addView(textView2);
        tableRow.addView(textView3);
        table.addView(tableRow);

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


}