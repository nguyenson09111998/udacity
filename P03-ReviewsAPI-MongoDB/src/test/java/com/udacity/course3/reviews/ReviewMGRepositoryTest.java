package com.udacity.course3.reviews;

import com.udacity.course3.reviews.entity.ReviewMG;
import com.udacity.course3.reviews.repository.ReviewMGRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@DataMongoTest
public class ReviewMGRepositoryTest {

	@Autowired
	private ReviewMGRepository reviewMGRepository;

	@Test
	public void contextLoads() {
	}

	@Test
	public void saveToDbMongo() {
		ReviewMG reviewMG = new ReviewMG();
		reviewMG.setId((long)12);
		reviewMG.setContent("Hello");
		reviewMG.setProductId((long)2);
		reviewMGRepository.save(reviewMG);

		ReviewMG mg = reviewMGRepository.findById((long)12)
				.orElseThrow(IllegalArgumentException::new);
		Assert.assertEquals("Hello",mg.getContent());
	}

	@Test
	public void findReviewByProductId(){
		ReviewMG reviewMG = new ReviewMG();
		reviewMG.setId((long)12);
		reviewMG.setContent("Thanks");
		reviewMG.setProductId((long)2);
		reviewMGRepository.save(reviewMG);

		List<ReviewMG> list = reviewMGRepository.findReviewByProductId((long)2);
		Assert.assertEquals(1,list.size());
	}


}