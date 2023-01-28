package com.hasd.e_woodfish_android.utils;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class Api {
    static String BASE_URL = "https://muyu.hasdsd.cn";

    public static void sendGetRequest(String url, Context ctx, final VolleyCallback callback) {
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

    public static void sendPostRequest(String url, JSONObject json, Context ctx, final VolleyJsonCallback jsonCallback) throws UnsupportedEncodingException {
        RequestQueue queue = Volley.newRequestQueue(ctx);
        HashMap<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        headers.put("application/json", "charset=utf-8");

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, BASE_URL + url, json, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    jsonCallback.onSuccess(response);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                jsonCallback.onError(error);
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                return headers;
            }
        };
        queue.add(request);

    }

    public interface VolleyCallback {
        void onSuccess(String result);

        void onError(VolleyError error);
    }


    public interface VolleyJsonCallback {
        void onSuccess(JSONObject jsonObject) throws JSONException;

        void onError(VolleyError error);
    }
}


