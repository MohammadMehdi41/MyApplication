package com.example.admin.myapplication.datamodel;


import android.graphics.drawable.Drawable;

public class Post {

    private int id;
    private Drawable postImageUrl;
    private String title;
    private String content;
    private String date;
    private int isVisited=0;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getIsVisited() {
        return isVisited;
    }

    public void setIsVisited(int isVisited) {
        this.isVisited = isVisited;
    }


    public Drawable getPostImageUrl() {
        return postImageUrl;
    }

    public void setPostImageUrl(Drawable postImageUrl) {
        this.postImageUrl = postImageUrl;
    }
}
