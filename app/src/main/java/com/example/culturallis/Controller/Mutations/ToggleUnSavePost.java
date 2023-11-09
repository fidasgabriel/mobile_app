package com.example.culturallis.Controller.Mutations;

import com.example.culturallis.Model.Global.Global;

import org.json.JSONObject;

import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ToggleUnSavePost {
    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    private OkHttpClient client = new OkHttpClient().newBuilder().connectTimeout(2, TimeUnit.MINUTES).writeTimeout(2, TimeUnit.MINUTES).readTimeout(3, TimeUnit.MINUTES).build();

    Global global = new Global();

    public Response toggleUnSave(Long pk_id_post) throws Exception {
        String url = global.getBaseUrl() + "/excluirPostFavorito";


        JSONObject json = new JSONObject();
        json.put("id", pk_id_post);

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
