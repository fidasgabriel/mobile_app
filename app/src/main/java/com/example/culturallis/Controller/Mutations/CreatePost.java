package com.example.culturallis.Controller.Mutations;

import android.util.Log;

import org.json.JSONObject;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class CreatePost {
    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    private OkHttpClient client = new OkHttpClient();

    String baseurl = "https://api-culturallis.onrender.com/api/culturallis";

    public Response createPost(String email, String urlPhoto, String desc) throws Exception {
        String url = baseurl + "/criarPost";


        JSONObject json = new JSONObject();
        json.put("url_midia", urlPhoto);
        json.put("fk_cul_usuarios_id", Integer.parseInt(String.valueOf(email)));
        json.put("descricao", desc);

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
