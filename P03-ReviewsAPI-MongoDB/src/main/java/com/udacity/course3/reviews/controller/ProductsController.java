package com.udacity.course3.reviews.controller;

import com.udacity.course3.reviews.entity.Product;
import com.udacity.course3.reviews.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpServerErrorException;

import javax.validation.Valid;
import java.util.List;

/**
 * Spring REST controller for working with product entity.
 */
@RestController
@RequestMapping("/products")
public class ProductsController {

    // TODO: Wire JPA repositories here
    @Autowired
    private ProductRepository productRepository;
    /**
     * Creates a product.
     *
     * 1. Accept product as argument. Use {@link RequestBody} annotation.
     * 2. Save product.
     */
    @RequestMapping(value = "/", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> createProduct(@Valid @RequestBody Product product) {
        try {

            Product save = productRepository.save(product);
            return ResponseEntity.ok(save);
        }catch (HttpServerErrorException e){
            throw new HttpServerErrorException(HttpStatus.NOT_IMPLEMENTED);
        }
    }

    /**
     * Finds a product by id.
     *
     * @param id The id of the product.
     * @return The product if found, or a 404 not found.
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> findById(@PathVariable("id") Long id) {
        try {
            Product product = productRepository.findById(id);
            if(ObjectUtils.isEmpty(product)){
                return ResponseEntity.ok(HttpStatus.NOT_FOUND);
            }
            return ResponseEntity.ok(product);
        }catch (HttpServerErrorException e){
            throw new HttpServerErrorException(HttpStatus.NOT_IMPLEMENTED);
        }
    }

    /**
     * Lists all products.
     *
     * @return The list of products.
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<?> listProducts() {
        try {
            return productRepository.findAll();
        }catch (HttpServerErrorException e){
            throw new HttpServerErrorException(HttpStatus.NOT_IMPLEMENTED);
        }
    }
}