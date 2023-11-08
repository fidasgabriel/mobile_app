package com.example.culturallis.Model.Global;

public class Global {

    private String baseUrl;

    public Global(){
            this.baseUrl = "https://api-culturallis.onrender.com/api/culturallis"; //"https://api-culturallis.onrender.com/api/culturallis" ; "https://cult2.onrender.com/api/culturallis"
    }

    public String getBaseUrl() {
        return baseUrl;
    }

}
