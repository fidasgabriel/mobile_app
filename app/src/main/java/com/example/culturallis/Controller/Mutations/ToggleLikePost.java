package com.example.culturallis.Controller.Mutations;

import android.util.Log;

import com.example.culturallis.Model.Global.Global;

import org.json.JSONObject;

import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ToggleLikePost {
    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    private OkHttpClient client = new OkHttpClient().newBuilder().connectTimeout(2, TimeUnit.MINUTES).writeTimeout(2, TimeUnit.MINUTES).readTimeout(3, TimeUnit.MINUTES).build();

    Global global = new Global();

    public Response toggleLike(Long pk_id_post, String email) throws Exception {
        String url = global.getBaseUrl() + "/curtirPost/";


        JSONObject json = new JSONObject();

        RequestBody body = RequestBody.create(json.toString(), JSON);
        Request request = new Request.Builder()
                .url(url + pk_id_post + "/" + email)
                .post(body)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                return response;
            } else {
                throw new Exception();
            }
        }
    }
}
