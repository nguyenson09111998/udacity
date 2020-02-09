package com.udacity.course3.reviews.controller;

import com.udacity.course3.reviews.entity.Product;
import com.udacity.course3.reviews.entity.Review;
import com.udacity.course3.reviews.repository.ProductRepository;
import com.udacity.course3.reviews.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpServerErrorException;

import javax.validation.Valid;
import java.util.Collections;
import java.util.List;

/**
 * Spring REST controller for working with review entity.
 */
@RestController
public class ReviewsController {

    // TODO: Wire JPA repositories here
    @Autowired
    ReviewRepository reviewRepository;

    @Autowired
    ProductRepository productRepository;
    /**
     * Creates a review for a product.
     * <p>
     * 1. Add argument for review entity. Use {@link RequestBody} annotation.
     * 2. Check for existence of product.
     * 3. If product not found, return NOT_FOUND.
     * 4. If found, save review.
     *
     * @param productId The id of the product.
     * @return The created review or 404 if product id is not found.
     */
    @RequestMapping(value = "/reviews/products/{productId}", method = RequestMethod.POST)
    public ResponseEntity<?> createReviewForProduct(
            @PathVariable("productId") Long productId,
            @Valid @RequestBody Review review) {
        try {
            Product product = productRepository.findById(productId);
            if(ObjectUtils.isEmpty(product)){
                return ResponseEntity.ok(HttpStatus.NOT_FOUND);
            }
            review.setProduct(product);
            reviewRepository.save(review);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch (HttpServerErrorException e){
            throw new HttpServerErrorException(HttpStatus.NOT_IMPLEMENTED);
        }
    }

    /**
     * Lists reviews by product.
     *
     * @param productId The id of the product.
     * @return The list of reviews.
     */
    @RequestMapping(value = "/reviews/products/{productId}", method = RequestMethod.GET)
    public ResponseEntity<List<?>> listReviewsForProduct(@PathVariable("productId") Long productId) {
        try {
            List<Review> list = reviewRepository.findAllByProductId(productId);
            return ResponseEntity.ok(list);
        }catch (HttpServerErrorException e){
            throw new HttpServerErrorException(HttpStatus.NOT_IMPLEMENTED);
        }
    }
}