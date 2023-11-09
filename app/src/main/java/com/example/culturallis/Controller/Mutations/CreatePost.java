package com.example.culturallis.Controller.Mutations;

import android.util.Log;

import com.example.culturallis.Model.Global.Global;

import org.json.JSONObject;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class CreatePost {
    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    private OkHttpClient client = new OkHttpClient();

    Global global = new Global();

    public Response createPost(String email, String urlPhoto, String desc) throws Exception {
        String url = global.getBaseUrl() + "/criarPost/";


        JSONObject json = new JSONObject();
        json.put("url_midia", urlPhoto);
        json.put("descricao", desc);


        RequestBody body = RequestBody.create(json.toString(), JSON);
        Request request = new Request.Builder()
                .url(url + email)
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
