package com.example.culturallis.Controller.Queries;

import android.util.Log;

import com.example.culturallis.Model.Global.Global;
import com.example.culturallis.Model.Usuario.Usuario;
import okhttp3.*;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GetUserInfo {

    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    private OkHttpClient client = new OkHttpClient();

    Global global = new Global();

    public Usuario getInfoUser(String email) throws Exception {
        String url = global.getBaseUrl() + "/email/";

        Request request = new Request.Builder()
                .url(url + email)
                .get()
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                String responseData = response.body().string();

                if (responseData.isEmpty()) {
                    return null;
                }

                JSONObject jsonObject = new JSONObject(responseData);

                Usuario usuario = new Usuario();

                usuario.setNome_usuario(jsonObject.getString("nomeUsuario"));
                usuario.setUrl_foto(jsonObject.getString("urlFoto"));
                usuario.setEmail(jsonObject.getString("email"));
                usuario.setCpf(jsonObject.getString("cpf"));

                String dateOfBirthStr = jsonObject.optString("dataNasc");

                if (!dateOfBirthStr.isEmpty()) {
                    SimpleDateFormat inputDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");

                    try {
                        Date dateOfBirth = inputDateFormat.parse(dateOfBirthStr);
                        usuario.setData_nascimento(dateOfBirth);
                    } catch (ParseException e) {
                        e.printStackTrace();
                        usuario.setData_nascimento(null);
                    }
                } else {
                    usuario.setData_nascimento(null);
                }

                usuario.setBio(jsonObject.getString("bio"));
                return usuario;
            } else {
                throw new Exception("Falha ao pegar informações do usuário");
            }
        }
    }
}

