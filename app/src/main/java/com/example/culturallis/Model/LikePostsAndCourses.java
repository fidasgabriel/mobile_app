package com.example.culturallis.Model;

public class LikePostsAndCourses {

    private Long pk_id_post;

    private String email;

    public LikePostsAndCourses(Long pk_id_post, String email) {
        this.pk_id_post = pk_id_post;
        this.email = email;
    }

    public Long getPk_id_post() {
        return pk_id_post;
    }

    public void setPk_id_post(Long pk_id_post) {
        this.pk_id_post = pk_id_post;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "LikePostsAndCourses{" +
                "pk_id_post=" + pk_id_post +
                ", email='" + email + '\'' +
                '}';
    }
}
