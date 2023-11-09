package com.example.culturallis.Controller.Queries;

import com.example.culturallis.Model.CoursesHome.CoursesHome;
import com.example.culturallis.Model.Global.Global;
import com.example.culturallis.Model.PostsHome.PostsHome;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class GetOwnCoursesRamdonly {
    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    private OkHttpClient client = new OkHttpClient().newBuilder().connectTimeout(2, TimeUnit.MINUTES).writeTimeout(2, TimeUnit.MINUTES).readTimeout(3, TimeUnit.MINUTES).build();
    Global global = new Global();

    public List<CoursesHome> getOwnCoursesRamdonly(String email) throws Exception {
        String url = global.getBaseUrl() + "/meusCursos/";

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
                List<CoursesHome> courses = objectMapper.readValue(responseData, new TypeReference<List<CoursesHome>>() {});
                return courses;
            } else {
                throw new Exception("Falha ao pegar cursos do próprio usuário");
            }
        }
    }
}
