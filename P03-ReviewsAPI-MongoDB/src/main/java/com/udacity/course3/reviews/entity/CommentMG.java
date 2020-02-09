package com.udacity.course3.reviews.entity;

import java.util.Date;

public class CommentMG {
    private String content;
    private Date createDate;

    public CommentMG() {
    }

    public CommentMG(String content, Date createDate) {
        this.content = content;
        this.createDate = createDate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
