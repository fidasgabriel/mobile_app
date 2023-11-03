package com.example.culturallis.Model.Entity;


public class CourseCard {

    private Long pk_id;
    private String perfilImage;
    private String courseImage;
    private String courseTitle;
    private String courseAuthor;
    private int viewsCount;
    private boolean liked;

    public CourseCard(Long pk_id, String perfilImage, String courseImage, String courseTitle, String courseAuthor, int viewsCount, boolean liked) {
        this.pk_id = pk_id;
        this.perfilImage = perfilImage;
        this.courseImage = courseImage;
        this.courseTitle = courseTitle;
        this.courseAuthor = courseAuthor;
        this.viewsCount = viewsCount;
        this.liked = liked;
    }

    public CourseCard(String courseImage, String perfilImage, String courseTitle, String courseAuthor, int viewsCount, boolean liked) {
        this.courseImage = courseImage;
        this.perfilImage = perfilImage;
        this.courseTitle = courseTitle;
        this.courseAuthor = courseAuthor;
        this.viewsCount = viewsCount;
        this.liked = liked;
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

    public String getPerfilImage() {
        return perfilImage;
    }

    public String getCourseImage() {
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

    public boolean isLiked() {
        return liked;
    }

    public void setLiked(boolean option) {
        this.liked = option;
    }

    public void setPerfilImage(String perfilImage) {
        this.perfilImage = perfilImage;
    }

    public void setCourseImage(String courseImage) {
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
