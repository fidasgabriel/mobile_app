package com.example.culturallis.Controller;

import com.example.culturallis.Model.Usuario.Usuario;
import okhttp3.*;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class Mutations {

    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    private OkHttpClient client = new OkHttpClient();

    String baseurl = "https://api-culturallis.onrender.com/api/culturallis";

    public boolean addUsuario(String userName, String email, String password) throws Exception {
        String url = baseurl + "/inserirUsuario";

        JSONObject json = new JSONObject();
        json.put("nome_usuario", userName);
        json.put("email", email);
        json.put("senha", password);
        json.put("urlFoto", "https://cdn.pixabay.com/photo/2012/04/26/19/43/profile-42914_1280.png");

        RequestBody body = RequestBody.create(json.toString(), JSON);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                return true;
            } else {
                throw new Exception();
            }
        }
    }
}
