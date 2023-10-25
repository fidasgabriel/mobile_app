package com.example.culturallis.Model.Entity;

import android.graphics.drawable.AnimatedVectorDrawable;

public class PostCard {
    private int perfilImage;
    private int postImage;
    private String postAuthor;
    private boolean liked;
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPerfilImage() {
        return perfilImage;
    }

    public void setPerfilImage(int perfilImage) {
        this.perfilImage = perfilImage;
    }

    public int getPostImage() {
        return postImage;
    }

    public void setPostImage(int postImage) {
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

    public PostCard(int postImage, int perfilImage, String postAuthor,boolean liked, boolean saved, String description) {
        this.postImage = postImage;
        this.perfilImage = perfilImage;
        this.postAuthor = postAuthor;
        this.liked = liked;
        this.description = description;
        this.saved = saved;
    }

    public void setLiked() {
        this.liked = !liked;
    }

    public boolean isSaved() {
        return saved;
    }

    public void setSaved() {
        this.saved = !saved;
    }

    private boolean saved;
}
