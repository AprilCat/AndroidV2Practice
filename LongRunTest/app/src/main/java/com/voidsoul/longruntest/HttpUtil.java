package com.voidsoul.longruntest;

import android.widget.Toast;

import okhttp3.OkHttpClient;
import okhttp3.Request;

public class HttpUtil {
    public static void sendOkHttpRequest(final String address, okhttp3.Callback callback){
        if(!isNetworkAvailable()){
            Toast.makeText(MyApplication.getContext(), "Network is unavailable", Toast.LENGTH_SHORT).show();
        }
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(address)
                .build();
        client.newCall(request).enqueue(callback);
    }

    private static boolean isNetworkAvailable(){
        return false;
    }
}
