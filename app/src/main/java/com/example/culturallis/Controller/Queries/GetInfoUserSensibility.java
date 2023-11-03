package com.example.culturallis.Controller.Queries;

import com.example.culturallis.Model.Global.Global;
import com.example.culturallis.Model.Usuario.Usuario;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GetInfoUserSensibility {

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

                usuario.setTelefone(jsonObject.getString("telefone"));
                usuario.setCpf(jsonObject.getString("cpf"));
                usuario.setEmail(jsonObject.getString("email"));
                usuario.setSenha(jsonObject.getString("senha"));
                return usuario;
            } else {
                throw new Exception("Falha ao pegar informações do usuário");
            }
        }
    }
}
