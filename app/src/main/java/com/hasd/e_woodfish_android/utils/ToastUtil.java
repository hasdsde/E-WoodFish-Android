package com.hasd.e_woodfish_android.utils;

import android.content.Context;
import android.widget.Toast;

public class ToastUtil {
    public static void showToast(Context ctx, String desc) {
        Toast.makeText(ctx, desc, Toast.LENGTH_SHORT).show();
    }
}
