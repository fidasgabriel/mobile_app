package com.example.culturallis.Controller.Queries;

import android.util.Log;

import com.example.culturallis.Model.Global.Global;

import okhttp3.*;
import org.json.JSONObject;

public class LoginUser {
    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    private OkHttpClient client = new OkHttpClient();
    Global global = new Global();
    public Response loginUser(String email, String password) throws Exception {
        String url = global.getBaseUrl() + "/login";

        JSONObject json = new JSONObject();
        json.put("email", email);
        json.put("senha", password);

        RequestBody body = RequestBody.create(json.toString(), JSON);
        Request request = new Request.Builder()
                .url(url)
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
