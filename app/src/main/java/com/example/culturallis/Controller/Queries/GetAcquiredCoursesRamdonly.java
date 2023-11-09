package com.example.culturallis.Controller.Queries;

import com.example.culturallis.Model.CoursesHome.CoursesHome;
import com.example.culturallis.Model.Global.Global;
import com.example.culturallis.Model.PostsHome.PostsHome;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class GetAcquiredCoursesRamdonly {
    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    private OkHttpClient client = new OkHttpClient();
    Global global = new Global();

    public List<CoursesHome> getAcquiredCoursesRamdonly(String email) throws Exception {
        String url = global.getBaseUrl() + "/meusCursosAdquiridos/";

        Request request = new Request.Builder()
                .url(url + email)
                .get()
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                String responseData = response.body().string();


                if (responseData.isEmpty()) {
                    return new ArrayList<>();
                }

                ObjectMapper objectMapper = new ObjectMapper();
                List<CoursesHome> posts = objectMapper.readValue(responseData, new TypeReference<List<CoursesHome>>() {});
                return posts;
            } else {
                throw new Exception("Falha ao pegar cursos adquiridos do usuário");
            }
        }
    }
}
