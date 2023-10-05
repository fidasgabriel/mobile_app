package com.example.culturallis.Model.Entity;

import android.graphics.drawable.AnimatedVectorDrawable;

public class CourseCard {
    private int perfilImage;
    private int courseImage;
    private String courseTitle;
    private String courseAuthor;
    private int viewsCount;
    private AnimatedVectorDrawable likeDrawable;
    private boolean liked;
    public CourseCard(int courseImage, int perfilImage, String courseTitle, String courseAuthor, int viewsCount, boolean liked) {
        this.courseImage = courseImage;
        this.perfilImage = perfilImage;
        this.courseTitle = courseTitle;
        this.courseAuthor = courseAuthor;
        this.viewsCount = viewsCount;
        this.liked = liked;
    }

    public int getPerfilImage() {
        return perfilImage;
    }

    public int getCourseImage() {
        return courseImage;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public String getCourseAuthor() {
        return courseAuthor;
    }

    public int getViewsCount() {
        return viewsCount;
    }

    public AnimatedVectorDrawable getLikeDrawable() {
        return likeDrawable;
    }
    public boolean isLiked() {
        return liked;
    }

    public void setLikeDrawable(AnimatedVectorDrawable likeDrawable) {
        this.likeDrawable = likeDrawable;
    }
    public void setLiked() {
        this.liked = !this.liked;
    }

    public void setPerfilImage(int perfilImage) {
        this.perfilImage = perfilImage;
    }

    public void setCourseImage(int courseImage) {
        this.courseImage = courseImage;
    }

    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }

    public void setCourseAuthor(String courseAuthor) {
        this.courseAuthor = courseAuthor;
    }

    public void setViewsCount(int viewsCount) {
        this.viewsCount = viewsCount;
    }

    @Override
    public String toString() {
        return "CourseCard{" +
                "perfilImage=" + perfilImage +
                ", courseImage=" + courseImage +
                ", courseTitle='" + courseTitle + '\'' +
                ", courseAuthor='" + courseAuthor + '\'' +
                ", viewsCount=" + viewsCount +
                ", liked=" + liked +
                '}';
    }
}
