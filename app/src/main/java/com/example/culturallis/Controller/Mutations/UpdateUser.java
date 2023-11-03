package com.example.culturallis.Controller.Mutations;

import android.util.Log;

import com.example.culturallis.Model.Global.Global;
import com.example.culturallis.Model.Usuario.Usuario;
import okhttp3.*;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class UpdateUser {

    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    private OkHttpClient client = new OkHttpClient();

    Global global = new Global();

    public Response updateUsuario(String email, Usuario userUpdated) throws Exception {
        String url = global.getBaseUrl() + "/alterarUsuarios/" + email;

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        JSONObject json = new JSONObject();
        json.put("nomeUsuario", userUpdated.getNome_usuario());
        json.put("dataNasc", simpleDateFormat.format(userUpdated.getData_nascimento()));
        json.put("bio", userUpdated.getBio());
        json.put("urlFoto", userUpdated.getUrl_foto());

        RequestBody body = RequestBody.create(json.toString(), JSON);
        Request request = new Request.Builder()
                .url(url)
                .patch(body)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                return response;
            } else {
                Exception e = new Exception();
                e.printStackTrace();
                return null;
            }
        }
    }
}
