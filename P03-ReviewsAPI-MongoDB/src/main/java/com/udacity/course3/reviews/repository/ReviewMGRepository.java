package com.udacity.course3.reviews.repository;

import com.udacity.course3.reviews.entity.ReviewMG;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Configuration
public interface ReviewMGRepository extends MongoRepository<ReviewMG,Long> {
//    ReviewMG findById(Long id);
    List<ReviewMG> findReviewByProductId(Long productId);
}
