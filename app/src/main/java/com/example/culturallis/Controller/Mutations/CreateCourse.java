package com.example.culturallis.Controller.Mutations;

import android.util.Log;

import com.example.culturallis.Model.Global.Global;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class CreateCourse {

    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    private OkHttpClient client = new OkHttpClient();

    Global global = new Global();

    public Response createCourse(String email, String nome, String fotoPost, String descricao, String categoria, Double preco, List<String> conteudosList) throws Exception {
        String url = global.getBaseUrl() + "/inserirCurso/";

        List<String> list = new ArrayList<>();

        for (String text : conteudosList) {
            if (text != null) {
                list.add(text);
            }
        }

        JSONObject json = new JSONObject();
        json.put("nome", nome);
        json.put("fotoPost", fotoPost);
        json.put("descricao", descricao);
        json.put("categoria", categoria);
        json.put("preco", preco);

        JSONArray conteudosArray = new JSONArray();
        for (String text : conteudosList) {
            if (text != null) {
                conteudosArray.put(text);
            }
        }

        json.put("conteudosList", conteudosArray);


        RequestBody body = RequestBody.create(json.toString(), JSON);
        Request request = new Request.Builder()
                .url(url + email)
                .post(body)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                return response;
            }else{
                throw new Exception();
            }
        }}
}
