package com.septp0402.orchestratorservice.proxy;

import java.util.ArrayList;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "product-service", url = "${product.service.url}")
public interface ProductProxy {

    @GetMapping("/product/search/{searchTerm}")
    String getSearchProducts(@PathVariable String searchTerm);

    @GetMapping("/product")
    String getAllProducts();

    @PostMapping("/product/search")
    String getSearchProductsWithFilters(@RequestBody String searchCriteria);

    @PostMapping("/product/similarproduct")
    String getSimilarProductsByID(@RequestBody String similarCriteriaString);

    @GetMapping("/product/review/{pID}")
    String getUserReviewsByProductID(@PathVariable int pID);

    @GetMapping("/product/retailer/{pID}")
    String getRetailerByProductID(@PathVariable int pID);

    @GetMapping("/product/category")
    String getAllCategories();

    @GetMapping("product/discount")
    String getAllDiscountedProducts();

    @GetMapping("/product/retailer")
    String getAllRetailers();

    @GetMapping("/product/{pID}")
    String getProductByID(@PathVariable int pID);

}
