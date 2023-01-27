package com.hasd.e_woodfish_android.utils;

import android.app.AlertDialog;
import android.content.Context;

import com.hasd.e_woodfish_android.R;

public class DialogUtl {
    public static void showDialog(Context ctx,String title,String msg) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(ctx, 0)
                .setIcon(R.drawable.ic_baseline_info_24)
                .setTitle(title)
                .setMessage(msg)
                .setPositiveButton("确定", null)
                .setCancelable(true);
        AlertDialog dialog = dialogBuilder.create();
        dialog.show();
    }
}
