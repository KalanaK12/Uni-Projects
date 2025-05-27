package com.septp0402.orchestratorservice.controller;

import com.septp0402.orchestratorservice.service.ProductService;

import io.micrometer.common.util.StringUtils;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProductController {

    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/product")
    public String getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/product/search/{searchTerm}")
    public String getSearchProducts(@PathVariable String searchTerm) {
        return productService.getSearchProducts(searchTerm);
    }

    @PostMapping("/product/search")
    public String getSearchProductsWithFilters(@RequestBody String searchCriteria) {
        return productService.getSearchProductsWithFilters(searchCriteria);
    }

    @GetMapping("/product/livesearch")
    public String getLiveSearchProducts(@RequestParam String searchInput) {
        
        String tempString = searchInput;
        int stringLength = tempString.trim().length();

        if(stringLength == 0) {
            return productService.getAllProducts();
        } 
        else {
            return productService.getSearchProducts(searchInput.trim());
        }

    }
    
    @PostMapping("/product/similarproduct")
    public String getSearchProductsByID(@RequestBody String similarCriteriaString) {
        return productService.getSimilarProductsByID(similarCriteriaString);
    }

    @GetMapping("/product/review/{pID}")
    public String getUserReviewsByProductID(@PathVariable int pID) {
        return productService.getUserReviewsByProductID(pID);
    }

    @GetMapping("/product/retailer/{pID}")
    public String getRetailerByProductID(@PathVariable int pID) {
        return productService.getRetailerByProductID(pID);
    }

    @GetMapping("/product/category")
    public String getAllCategories() {
        return productService.getAllCategories();
    }

    @GetMapping("product/discount")
    public String getAllDiscountedProducts() {
        return productService.getAllDiscountedProducts();
    }

    @GetMapping("/product/retailer")
    public String getAllRetailers() {
        return productService.getAllRetailers();
    }

    @GetMapping("/product/{pID}")
    public String getProductByID(@PathVariable int pID) {
        return productService.getProductByID(pID);
    }

}
