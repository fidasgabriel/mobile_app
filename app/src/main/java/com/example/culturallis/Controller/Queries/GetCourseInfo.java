package com.example.culturallis.Controller.Queries;

import com.example.culturallis.Model.CourseDetails.CourseDetails;
import com.example.culturallis.Model.CoursesHome.CoursesHome;
import com.example.culturallis.Model.Global.Global;
import com.example.culturallis.Model.Modules.Modules;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class GetCourseInfo {

    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    private OkHttpClient client = new OkHttpClient();

    Global global = new Global();

    public CourseDetails getCoursesDetails(long idCurso) throws Exception {
        String url = global.getBaseUrl() + "/cursoInfo/";

        Request request = new Request.Builder()
                .url(url + idCurso)
                .get()
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                String responseData = response.body().string();

                CourseDetails courseDetails = new CourseDetails();

                if (responseData.isEmpty()) {
                    return null;
                }

                JSONObject jsonObject = new JSONObject(responseData);

                courseDetails.setPk_id(jsonObject.getLong("pk_id"));
                courseDetails.setNome(jsonObject.getString("nome"));
                courseDetails.setUrl_midia(jsonObject.getString("url_midia"));
                courseDetails.setFk_cul_usuarios_id(jsonObject.getInt("fk_cul_usuarios_id"));
                courseDetails.setFk_cul_categorias_id(jsonObject.getInt("fk_cul_categorias_id"));
                courseDetails.setCourseOwner(jsonObject.getString("courseOwner"));
                courseDetails.setCourseOwnerFoto(jsonObject.getString("courseOwnerFoto"));
                courseDetails.setCategoria(jsonObject.getString("categoria"));
                courseDetails.setDescricao(jsonObject.getString("descricao"));
                courseDetails.setPreco(jsonObject.getDouble("preco"));
                JSONArray modulosArray = jsonObject.getJSONArray("modulos");

                List<Modules> modulesList = new ArrayList<>();

                for (int i = 0; i < modulosArray.length(); i++) {
                    JSONObject moduleObject = modulosArray.getJSONObject(i);

                    Modules module = new Modules();

                    module.setNome(moduleObject.getString("nome"));
                    module.setUrl_material(moduleObject.getString("url_material"));

                    modulesList.add(module);
                }

                courseDetails.setModulos(modulesList);

                return courseDetails;
            } else {
                throw new Exception("Falha ao pegar as informações do curso");
            }
        }
    }
}
