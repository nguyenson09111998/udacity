package com.udacity.course3.reviews.controller;

import com.udacity.course3.reviews.entity.Comment;
import com.udacity.course3.reviews.entity.CommentMG;
import com.udacity.course3.reviews.entity.Review;
import com.udacity.course3.reviews.entity.ReviewMG;
import com.udacity.course3.reviews.repository.CommentRepository;
import com.udacity.course3.reviews.repository.ReviewMGRepository;
import com.udacity.course3.reviews.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpServerErrorException;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Spring REST controller for working with comment entity.
 */
@RestController
@RequestMapping("/comments")
public class CommentsController {

    // TODO: Wire needed JPA repositories here
    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private ReviewMGRepository reviewMGRepository;

    /**
     * Creates a comment for a review.
     *
     * 1. Add argument for comment entity. Use {@link RequestBody} annotation.
     * 2. Check for existence of review.
     * 3. If review not found, return NOT_FOUND.
     * 4. If found, save comment.
     *
     * @param reviewId The id of the review.
     */
    @RequestMapping(value = "/reviews/{reviewId}", method = RequestMethod.POST)
    public ResponseEntity<?> createCommentForReview(
            @PathVariable("reviewId") Long reviewId,
            @Valid @RequestBody Comment comment) {
        try {
            Review review = reviewRepository.findAllById(reviewId);
            ReviewMG reviewMG = reviewMGRepository.findById(reviewId)
                    .orElseThrow(IllegalArgumentException::new);
            if(ObjectUtils.isEmpty(review)){
                return ResponseEntity.ok(HttpStatus.NOT_FOUND);
            }
            if(ObjectUtils.isEmpty(reviewMG)){
                return ResponseEntity.ok(HttpStatus.NOT_FOUND);
            }
            comment.setReview(review);
            commentRepository.save(comment);

            Date date = new Date();
            CommentMG commentMG = new CommentMG(comment.getContent(),date);
            if( reviewMG.getCommentMG() != null && !reviewMG.getCommentMG().isEmpty()){
                reviewMG.getCommentMG().add(commentMG);
            }else {
                List<CommentMG> commentMGList = new ArrayList<>();
                commentMGList.add(commentMG);
                reviewMG.setCommentMG(commentMGList);
            }
            reviewMGRepository.save(reviewMG);

            return ResponseEntity.ok(reviewMG);
        }catch (HttpServerErrorException e){
            throw new HttpServerErrorException(HttpStatus.NOT_IMPLEMENTED);
        }
    }

    /**
     * List comments for a review.
     *
     * 2. Check for existence of review.
     * 3. If review not found, return NOT_FOUND.
     * 4. If found, return list of comments.
     *
     * @param reviewId The id of the review.
     */
    @RequestMapping(value = "/reviews/{reviewId}", method = RequestMethod.GET)
    public ResponseEntity<?> listCommentsForReview(@PathVariable("reviewId") Long reviewId) {
        try {

            Review review = reviewRepository.findAllById(reviewId);
            ReviewMG reviewMG = reviewMGRepository.findById(reviewId)
                    .orElseThrow(IllegalArgumentException::new);
            if(ObjectUtils.isEmpty(review) || ObjectUtils.isEmpty(reviewMG)){
                return ResponseEntity.ok(HttpStatus.NOT_FOUND);
            }
            List<CommentMG> commentList = reviewMG.getCommentMG();
            return ResponseEntity.ok(commentList);
        }catch (HttpServerErrorException e){
            throw new HttpServerErrorException(HttpStatus.NOT_IMPLEMENTED);
        }
    }
}