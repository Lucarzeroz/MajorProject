package com.example.majorproject;

import android.widget.ImageView;

import com.google.android.material.textfield.TextInputEditText;

public class Posts {

    private String username, time, title, content;
    private int postImage;
    private ImageView commentbtn;
    private TextInputEditText comment;

    public Posts(String username, String time, String title, String content, int postImage, ImageView commentbtn, TextInputEditText comment) {
        this.username = username;
        this.time = time;
        this.title = title;
        this.content = content;
        this.postImage = postImage;
        this.commentbtn = commentbtn;
        this.comment = comment;
    }

    public Posts(String username, String time, String title, String content, int ic_like, int ic_send) {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
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

    public int getPostImage() {
        return postImage;
    }

    public void setPostImage(int postImage) {
        this.postImage = postImage;
    }

    public ImageView getCommentbtn() {
        return commentbtn;
    }

    public void setCommentbtn(ImageView commentbtn) {
        this.commentbtn = commentbtn;
    }

    public TextInputEditText getComment() {
        return comment;
    }

    public void setComment(TextInputEditText comment) {
        this.comment = comment;
    }
}
