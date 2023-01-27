package com.hasd.e_woodfish_android.utils;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.Map;

public class Api {
    public static void sendGetRequest(String url, Context ctx, final VolleyCallback callback) {
        String BASE_URL = "https://muyu.hasdsd.cn";
        RequestQueue queue = Volley.newRequestQueue(ctx);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, BASE_URL + url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        callback.onSuccess(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                callback.onError(error);
            }
        });
        queue.add(stringRequest);
    }

    public static void sendPostRequest(String url, final Map<String, String> params, Context ctx, final VolleyCallback callback) {
        RequestQueue queue = Volley.newRequestQueue(ctx);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        callback.onSuccess(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                callback.onError(error);
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                return params;
            }
        };
        queue.add(stringRequest);
    }

    public interface VolleyCallback {
        void onSuccess(String result);

        void onError(VolleyError error);
    }
//    public static StringRequest Get(Context ctx, String url) {
//        RequestQueue queue = Volley.newRequestQueue(ctx);
//        String BASE_URL = "https://muyu.hasdsd.cn";
//        return new StringRequest(
//                Request.Method.GET, BASE_URL + url, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                Log.d("Test", response);
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Log.e("Test", error.getMessage(), error);
//            }
//        });
////        queue.add(stringRequest);
//    }

}
