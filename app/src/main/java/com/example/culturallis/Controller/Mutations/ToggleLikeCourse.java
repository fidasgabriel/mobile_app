package com.example.culturallis.Controller.Mutations;

import com.example.culturallis.Model.Global.Global;

import org.json.JSONObject;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ToggleLikeCourse {
    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    private OkHttpClient client = new OkHttpClient();

    Global global = new Global();

    public Response toggleLikeCourse(Long pk_id_course, String email) throws Exception {
        String url = global.getBaseUrl() + "/salvarCurso/";


        JSONObject json = new JSONObject();

        RequestBody body = RequestBody.create(json.toString(), JSON);
        Request request = new Request.Builder()
                .url(url + pk_id_course + "/" + email)
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
