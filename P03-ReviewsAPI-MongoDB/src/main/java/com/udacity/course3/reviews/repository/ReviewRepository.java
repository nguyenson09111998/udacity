package com.udacity.course3.reviews.repository;

import com.udacity.course3.reviews.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review,Long> {
    @Query("SELECT r FROM Review r WHERE r.product.id = :id")
    List<Review> findAllByProductId(@Param("id") Long id);

    Review findAllById(Long reviewId);
}
