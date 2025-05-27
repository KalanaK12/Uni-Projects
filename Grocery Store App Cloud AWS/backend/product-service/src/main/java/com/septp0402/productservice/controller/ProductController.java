package com.septp0402.productservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.septp0402.productservice.model.*;
import com.septp0402.productservice.repository.ProductRepository;
import com.septp0402.productservice.model.Product;
import com.septp0402.productservice.model.ProductSearchCriteria;
import com.septp0402.productservice.model.Retailer;
import com.septp0402.productservice.model.SimilarProductsCriteria;
import com.septp0402.productservice.model.UserReview;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class ProductController {
    public ProductRepository productRepository;
    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping("/product")
    public ArrayList<Product> getAllProducts() {
        return productRepository.getAllProducts();
    }

    @GetMapping("/product/search/{searchTerm}")
    public ArrayList<Product> getSearchProducts(@PathVariable String searchTerm) {
        return productRepository.getSearchProducts(searchTerm);
    }

    @PostMapping("/product/search")
    public ArrayList<Product> getSearchProductsWithFilters(@RequestBody String pCriteria) {
        ObjectMapper objectMapper = new ObjectMapper();
        ProductSearchCriteria productSearchCriteria;
        try {
            productSearchCriteria = objectMapper.readValue(pCriteria, ProductSearchCriteria.class);
        } catch (JsonProcessingException e) {
            System.out.println(e);
            return null;
        }
        return productRepository.getSearchProductsWithFilters(productSearchCriteria);
    }

    @PostMapping("/product/similarproduct")
    public ArrayList<Product> getSimilarProductsByID(@RequestBody String similarCriteriaString) {
        ObjectMapper objectMapper = new ObjectMapper();
        SimilarProductsCriteria similarProductsCriteria;
        try {
            similarProductsCriteria = objectMapper.readValue(similarCriteriaString, SimilarProductsCriteria.class);
        } catch (JsonProcessingException e) {
            System.out.println(e);
            return null;
        }
        return productRepository.getSimilarProductsByID(similarProductsCriteria);
    }

    @GetMapping("/product/review/{pID}")
    public ArrayList<UserReview> getUserReviewsByProductID(@PathVariable int pID) {
        return productRepository.getUserReviewsByProductID(pID);
    }

    @GetMapping("/product/retailer/{pID}")
    public Retailer getRetailerByProductID(@PathVariable int pID) {
        return productRepository.getRetailerByProductID(pID);
    }
    

    @GetMapping("/product/category")
    public ArrayList<Category> getAllCategories() {
        return productRepository.getAllCategories();
    }

    @GetMapping("product/discount")
    public ArrayList<Product> getAllDiscountedProducts() {
        return productRepository.getAllDiscountedProducts();
    }

    @GetMapping("/product/retailer")
    public ArrayList<Retailer> getAllRetailers() {
        return productRepository.getAllRetailers();
    }

    @GetMapping("/product/{pID}")
    public Product getProductByID(@PathVariable int pID) {
        return productRepository.getProductByID(pID);
    }
}
