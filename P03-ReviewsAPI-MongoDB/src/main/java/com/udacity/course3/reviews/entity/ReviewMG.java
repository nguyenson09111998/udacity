package com.udacity.course3.reviews.entity;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.util.List;

@Document("reviews")
public class ReviewMG {
    @Id
    private Long id;
    @Indexed(name = "conntent")
    private String content;
    @Indexed(name = "productId")
    private Long productId;
    @Indexed(name = "comments")
    private List<CommentMG> commentMG;

    public ReviewMG() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public List<CommentMG> getCommentMG() {
        return commentMG;
    }

    public void setCommentMG(List<CommentMG> commentMG) {
        this.commentMG = commentMG;
    }
}
