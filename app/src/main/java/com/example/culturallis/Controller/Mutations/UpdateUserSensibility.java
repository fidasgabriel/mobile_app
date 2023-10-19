package com.example.culturallis.Controller.Mutations;

import com.example.culturallis.Model.Usuario.Usuario;
import okhttp3.*;
import org.json.JSONObject;

import java.text.SimpleDateFormat;

public class UpdateUserSensibility {

    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    private OkHttpClient client = new OkHttpClient();

    String baseurl = "https://api-culturallis.onrender.com/api/culturallis";

    public Response updateUserSensibility(String email, Usuario userUpdated) throws Exception {
        String url = baseurl + "/alterarUsuariosSensivel/" + email;

        JSONObject json = new JSONObject();
        json.put("email", userUpdated.getEmail());
        json.put("telefone", userUpdated.getTelefone());
        json.put("cpf", userUpdated.getCpf());
        json.put("senha", userUpdated.getSenha());

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
