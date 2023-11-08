package com.example.culturallis.Controller.Mutations;

import android.util.Log;

import com.example.culturallis.Model.Global.Global;

import org.json.JSONObject;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class AdquireCourse {

    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    private OkHttpClient client = new OkHttpClient();

    Global global = new Global();

    public Response adquireCourse(long courseId, String email) throws Exception {
        String url = global.getBaseUrl() + "/adquirirCurso/";

        JSONObject json = new JSONObject();
        RequestBody body = RequestBody.create(json.toString(), JSON);

        Request request = new Request.Builder()
                .url(url + courseId + "/" + email)
                .post(body)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                return response;
            }else {
                throw new Exception();
            }
        }
    }
}
