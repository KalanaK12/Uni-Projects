package com.septp0402.orchestratorservice.service;

import com.septp0402.orchestratorservice.proxy.ProductProxy;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Service
public class ProductService {

    private ProductProxy productProxy;

    @Autowired
    public ProductService(ProductProxy productProxy) {
        this.productProxy = productProxy;
    }

    public String getAllProducts() {
        return productProxy.getAllProducts();
    }

    public String getSearchProducts(String searchTerm) {
        return productProxy.getSearchProducts(searchTerm);
    }

    public String getSearchProductsWithFilters(String searchCriteria) {
        return productProxy.getSearchProductsWithFilters(searchCriteria);
    }

    public String getSimilarProductsByID(String similarCriteriaString) {
        return productProxy.getSimilarProductsByID(similarCriteriaString);
    }
    public String getUserReviewsByProductID(int pID) {
        return productProxy.getUserReviewsByProductID(pID);
    }

    public String getRetailerByProductID(@PathVariable int pID) {
        return productProxy.getRetailerByProductID(pID);
    }

    public String getAllCategories() {
        return productProxy.getAllCategories();
    }

    public String getAllDiscountedProducts() {
        return productProxy.getAllDiscountedProducts();
    }

    public String getAllRetailers() {
        return productProxy.getAllRetailers();
    }

    public String getProductByID(int pID) {
        return productProxy.getProductByID(pID);
    }
}
