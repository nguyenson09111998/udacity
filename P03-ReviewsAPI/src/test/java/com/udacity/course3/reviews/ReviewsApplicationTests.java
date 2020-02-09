package com.udacity.course3.reviews;

import com.udacity.course3.reviews.entity.Product;
import com.udacity.course3.reviews.entity.Review;
import com.udacity.course3.reviews.repository.CommentRepository;
import com.udacity.course3.reviews.repository.ProductRepository;
import com.udacity.course3.reviews.repository.ReviewRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ReviewsApplicationTests {

	@Autowired
	CommentRepository commentRepository;

	@Autowired
	ReviewRepository reviewRepository;

	@Autowired
	ProductRepository productRepository;

	@Before
	public void setUp(){
		Product product = new Product();
		product.setName("product1");
		productRepository.save(product);

		Review review = new Review();
		review.setProduct(product);
		review.setContent("review1");
		reviewRepository.save(review);
	}
	@Test
	public void contextLoads() {
	}

	@Test
	public void productEntity(){
		Product product = productRepository.findById((long)1);
		Assert.assertEquals("product1",product.getName());
	}

	@Test
	public void findProductFromReview(){
		Product product = productRepository.findById((long)1);
		Review review = reviewRepository.findAllByProductId(product.getId()).get(0);
		Assert.assertEquals("review1",review.getContent());
	}
}