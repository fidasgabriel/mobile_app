package com.example.culturallis.Model.Entity;

import android.graphics.drawable.AnimatedVectorDrawable;

public class PostCard {

    private Long pk_id;
    private String perfilImage;
    private String postImage;
    private String postAuthor;
    private boolean liked;
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPerfilImage() {
        return perfilImage;
    }

    public void setPerfilImage(String perfilImage) {
        this.perfilImage = perfilImage;
    }

    public String getPostImage() {
        return postImage;
    }

    public void setPostImage(String postImage) {
        this.postImage = postImage;
    }

    public String getPostAuthor() {
        return postAuthor;
    }

    public void setPostAuthor(String postAuthor) {
        this.postAuthor = postAuthor;
    }

    public boolean isLiked() {
        return liked;
    }

    public PostCard(Long pk_id, String postImage, String perfilImage, String postAuthor, boolean liked, boolean saved, String description) {
        this.pk_id = pk_id;
        this.postImage = postImage;
        this.perfilImage = perfilImage;
        this.postAuthor = postAuthor;
        this.liked = liked;
        this.saved = saved;
        this.description = description;
    }

    public PostCard(String postImage, String perfilImage, String postAuthor, boolean liked, boolean saved, String description) {
        this.postImage = postImage;
        this.perfilImage = perfilImage;
        this.postAuthor = postAuthor;
        this.liked = liked;
        this.description = description;
        this.saved = saved;
    }

    public Long getPk_id() {
        return pk_id;
    }

    public void setPk_id(Long pk_id) {
        this.pk_id = pk_id;
    }

    public void setLiked(boolean liked) {
        this.liked = liked;
    }

    public void setSaved(boolean saved) {
        this.saved = saved;
    }

    public void setLiked() {
        this.liked = !liked;
    }

    public boolean isSaved() {
        return saved;
    }

    public void setSaved(boolean option) {
        this.saved = option;
    }

    private boolean saved;

    @Override
    public String toString() {
        return "PostCard{" +
                "pk_id=" + pk_id +
                ", perfilImage='" + perfilImage + '\'' +
                ", postImage='" + postImage + '\'' +
                ", postAuthor='" + postAuthor + '\'' +
                ", liked=" + liked +
                ", description='" + description + '\'' +
                ", saved=" + saved +
                '}';
    }
}
