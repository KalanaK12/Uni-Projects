package com.septp0402.productservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.septp0402.productservice.model.*;
import com.septp0402.productservice.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
class ProductControllerUnitTest {
    ProductController productController;
    ProductRepository productRepository;

    @BeforeEach
    void setup() {
        this.productRepository = mock(ProductRepository.class);
        this.productController = new ProductController(this.productRepository);
    }

    @Test
    void getAllProducts_ReturnEmpty_WhenNoProducts() {
        // when getAllProducts in productRepository is called, will return an empty list
        when(this.productRepository.getAllProducts()).thenReturn(new ArrayList<>());

        // the list returned by productController when getAllProducts is called should also be empty
        assertEquals(0, this.productController.getAllProducts().size());
    }

    @Test
    void getAllProducts_CorrectSize_WhenProductsExists() {
        // create a mock product lists and add two product
        ArrayList<Product> products = new ArrayList<>();
        products.add(new Product(1, 1, "Woolworths", 3,
                "dairy", 1, "milk", 3.50, null, "Woolworths milk",
                "Woolworths milk", 2.0, "path/image.jpg"));
        products.add(new Product(2, 2, "Coles", 3,
                "dairy", 1, "milk", 2.50, null, "Coles milk",
                "Coles milk", 2.0, "path/image.jpg"));

        // when getAllProducts in productRepository is called, return products
        when(this.productRepository.getAllProducts()).thenReturn(products);

        // the list returned by productController when getAllProducts is called should also be empty
        assertEquals(2, this.productController.getAllProducts().size());
    }

    @Test
    void getAllProducts_CorrectProducts_WhenProductsExists() {
        // create a mock product lists and add two product
        ArrayList<Product> products = new ArrayList<>();
        Product product = new Product(1, 1, "Woolworths", 3,
                "dairy", 1, "milk", 3.50, null, "Woolworths milk",
                "Woolworths milk", 2.0, "path/image.jpg");
        products.add(product);

        // when getAllProducts in productRepository is called, return products
        when(this.productRepository.getAllProducts()).thenReturn(products);

        // the list returned by productController when getAllProducts is called should also be empty
        assertEquals(product, this.productController.getAllProducts().get(0));
    }

    @Test
    void getSearchProducts_ReturnEmpty_WhenNoProducts() {
        // initialise a search term that doesn't exit
        String searchTerm = "SearchTermThatDoesNotExist";

        // when getSearchProducts in productRepository is called, will return an empty list
        when(this.productRepository.getSearchProducts(searchTerm)).thenReturn(new ArrayList<>());

        // the list returned by productController when getSearchProducts is called should also be empty
        assertEquals(0, this.productController.getSearchProducts(searchTerm).size());
    }

    @Test
    void getSearchProducts_CorrectSize_WhenProductsExists() {
        // initialise a mock search term that does exist
        String searchTerm = "Milk";

        // create a mock product lists and add two product
        ArrayList<Product> products = new ArrayList<>();
        products.add(new Product(1, 1, "Woolworths", 3,
                "dairy", 1, "milk", 3.50, null, "Woolworths milk",
                "Woolworths milk", 2.0, "path/image.jpg"));
        products.add(new Product(2, 2, "Coles", 3,
                "dairy", 1, "milk", 2.50, null, "Coles milk",
                "Coles milk", 2.0, "path/image.jpg"));

        // when getAllProducts in getSearchProducts is called, return products
        when(this.productRepository.getSearchProducts(searchTerm)).thenReturn(products);

        // the list returned by productController when getAllProducts is called should also be empty
        assertEquals(2, this.productController.getSearchProducts(searchTerm).size());
    }

    @Test
    void getSearchProducts_CorrectProducts_WhenProductsExists() {
        // initialise a mock search term that does exist
        String searchTerm = "Milk";

        // create a mock product lists and add two product
        ArrayList<Product> products = new ArrayList<>();
        Product product = new Product(1, 1, "Woolworths", 3,
                "dairy", 1, "milk", 3.50, null, "Woolworths milk",
                "Woolworths milk", 2.0, "path/image.jpg");
        products.add(product);

        // when getSearchProducts in productRepository is called, return products
        when(this.productRepository.getSearchProducts(searchTerm)).thenReturn(products);

        // the list returned by productController when getSearchProducts is called should also be empty
        assertEquals(product, this.productController.getSearchProducts(searchTerm).get(0));
    }

    @Test
    void getSearchProductsWithFilters_ReturnEmpty_WhenNoProducts() throws JsonProcessingException {
        // initialise dummy ProductSearchCriteria
        ProductSearchCriteria productSearchCriteria = new ProductSearchCriteria("SearchTermThatDoesNotExist",
                null, null, false, null);

        // when getSearchProductsWithFilters in productRepository is called, will return an empty list
        when(this.productRepository.getSearchProductsWithFilters(productSearchCriteria)).thenReturn(new ArrayList<>());

        // get string of productSearchCriteria
        String productSearchCriteriaJson = new ObjectMapper().writeValueAsString(productSearchCriteria);

        // the list returned by productController when getSearchProductsWithFilters is called should also be empty
        assertEquals(0, this.productController.getSearchProductsWithFilters(productSearchCriteriaJson).size());
    }

    @Test
    void getSearchProductsWithFilters_CorrectSize_WhenProductsExists() throws JsonProcessingException {
        // initialise dummy ProductSearchCriteria
        ProductSearchCriteria productSearchCriteria = new ProductSearchCriteria(null,
                null, null, false, null);

        // create a mock product lists and add two product
        ArrayList<Product> products = new ArrayList<>();
        products.add(new Product(1, 1, "Woolworths", 3,
                "dairy", 1, "milk", 3.50, null, "Woolworths milk",
                "Woolworths milk", 2.0, "path/image.jpg"));
        products.add(new Product(2, 2, "Coles", 3,
                "dairy", 1, "milk", 2.50, null, "Coles milk",
                "Coles milk", 2.0, "path/image.jpg"));

        // when getSearchProductsWithFilters in productRepository is called, return products
        when(this.productRepository.getSearchProductsWithFilters(productSearchCriteria)).thenReturn(products);

        // get string of productSearchCriteria
        String productSearchCriteriaJson = new ObjectMapper().writeValueAsString(productSearchCriteria);

        // the list returned by productController when getSearchProductsWithFilters is called should also be empty
        assertEquals(2, this.productController.getSearchProductsWithFilters(productSearchCriteriaJson).size());
    }

    @Test
    void getSearchProductsWithFilters_CorrectProducts_WhenProductsExists() throws JsonProcessingException {
        // initialise dummy ProductSearchCriteria
        ProductSearchCriteria productSearchCriteria = new ProductSearchCriteria(null,
                null, null, false, null);

        // create a mock product lists and add two product
        ArrayList<Product> products = new ArrayList<>();
        Product product = new Product(1, 1, "Woolworths", 3,
                "dairy", 1, "milk", 3.50, null, "Woolworths milk",
                "Woolworths milk", 2.0, "path/image.jpg");
        products.add(product);

        // when getSearchProductsWithFilters in productRepository is called, return products
        when(this.productRepository.getSearchProductsWithFilters(productSearchCriteria)).thenReturn(products);

        // get string of productSearchCriteria
        String productSearchCriteriaJson = new ObjectMapper().writeValueAsString(productSearchCriteria);

        // the list returned by productController when getSearchProductsWithFilters is called should also be empty
        assertEquals(product, this.productController.getSearchProductsWithFilters(productSearchCriteriaJson).get(0));
    }

    @Test
    void getSimilarProductsByID_ReturnEmpty_WhenNoProducts() throws JsonProcessingException {
        // initialise dummy SimilarProductsCriteria
        SimilarProductsCriteria similarProductsCriteria = new SimilarProductsCriteria(4, 1);

        // when getSimilarProductsByID in productRepository is called, will return an empty list
        when(this.productRepository.getSimilarProductsByID(similarProductsCriteria)).thenReturn(new ArrayList<>());

        // get string of similarProductsCriteria
        String similarProductsCriteriaJson = new ObjectMapper().writeValueAsString(similarProductsCriteria);

        // the list returned by productController when getSimilarProductsByID is called should also be empty
        assertEquals(0, this.productController.getSimilarProductsByID(similarProductsCriteriaJson).size());
    }

    @Test
    void getSimilarProductsByID_CorrectSize_WhenProductsExists() throws JsonProcessingException {
        // initialise dummy SimilarProductsCriteria
        SimilarProductsCriteria similarProductsCriteria = new SimilarProductsCriteria(4, 1);

        // create a mock product lists and add two product
        ArrayList<Product> products = new ArrayList<>();
        products.add(new Product(1, 1, "Woolworths", 3,
                "dairy", 1, "milk", 3.50, null, "Woolworths milk",
                "Woolworths milk", 2.0, "path/image.jpg"));
        products.add(new Product(2, 2, "Coles", 3,
                "dairy", 1, "milk", 2.50, null, "Coles milk",
                "Coles milk", 2.0, "path/image.jpg"));

        // when getSimilarProductsByID in productRepository is called, return products
        when(this.productRepository.getSimilarProductsByID(similarProductsCriteria)).thenReturn(products);

        // get string of similarProductsCriteria
        String similarProductsCriteriaJson = new ObjectMapper().writeValueAsString(similarProductsCriteria);

        // the list returned by productController when getSimilarProductsByID is called should also be empty
        assertEquals(2, this.productController.getSimilarProductsByID(similarProductsCriteriaJson).size());
    }

    @Test
    void getSimilarProductsByID_CorrectProducts_WhenProductsExists() throws JsonProcessingException {
        // initialise dummy SimilarProductsCriteria
        SimilarProductsCriteria similarProductsCriteria = new SimilarProductsCriteria(4, 1);

        // create a mock product lists and add two product
        ArrayList<Product> products = new ArrayList<>();
        Product product = new Product(1, 1, "Woolworths", 3,
                "dairy", 1, "milk", 3.50, null, "Woolworths milk",
                "Woolworths milk", 2.0, "path/image.jpg");
        products.add(product);

        // when getSimilarProductsByID in productRepository is called, return products
        when(this.productRepository.getSimilarProductsByID(similarProductsCriteria)).thenReturn(products);

        // get string of similarProductsCriteria
        String similarProductsCriteriaJson = new ObjectMapper().writeValueAsString(similarProductsCriteria);

        // the list returned by productController when getSimilarProductsByID is called should also be empty
        assertEquals(product, this.productController.getSimilarProductsByID(similarProductsCriteriaJson).get(0));
    }

    @Test
    void getUserReviewsByProductID_ReturnEmpty_WhenNoProducts() {
        // initialise a product id that doesn't exit
        int id = 999;

        // when getUserReviewsByProductID in productRepository is called, will return an empty list
        when(this.productRepository.getUserReviewsByProductID(id)).thenReturn(new ArrayList<>());

        // the list returned by productController when getUserReviewsByProductID is called should also be empty
        assertEquals(0, this.productController.getUserReviewsByProductID(id).size());
    }

    @Test
    void getUserReviewsByProductID_CorrectSize_WhenProductsExists() {
        // initialise a product id that does exit
        int id = 999;

        // create a mock review lists and add two reviews
        ArrayList<UserReview> reviews = new ArrayList<>();
        reviews.add(new UserReview(1, 1, 1, "Good product", 4.0));
        reviews.add(new UserReview(2, 3, 2, "Great product", 5.0));

        // when getUserReviewsByProductID in getSearchProducts is called, return products
        when(this.productRepository.getUserReviewsByProductID(id)).thenReturn(reviews);

        // the list returned by productController when getUserReviewsByProductID is called should also be empty
        assertEquals(2, this.productController.getUserReviewsByProductID(id).size());
    }

    @Test
    void getUserReviewsByProductID_CorrectProducts_WhenProductsExists() {
        // initialise a product id that does exit
        int id = 999;

        // create a mock review lists and add two reviews
        ArrayList<UserReview> reviews = new ArrayList<>();
        UserReview userReview = new UserReview(1, 1, 1, "Good product", 4.0);
        reviews.add(userReview);

        // when getUserReviewsByProductID in productRepository is called, return products
        when(this.productRepository.getUserReviewsByProductID(id)).thenReturn(reviews);

        // the list returned by productController when getUserReviewsByProductID is called should also be empty
        assertEquals(userReview, this.productController.getUserReviewsByProductID(id).get(0));
    }

    @Test
    void getRetailerByProductID_ReturnEmpty_WhenNoProducts() {
        // initialise a product id that doesn't exit
        int id = 999;

        // when getRetailerByProductID in productRepository is called, will return an empty list
        when(this.productRepository.getRetailerByProductID(id)).thenReturn(null);

        // the list returned by productController when getRetailerByProductID is called should also be empty
        assertNull(this.productController.getRetailerByProductID(id));
    }

    @Test
    void getRetailerByProductID_CorrectRetailer_WhenProductsExists() {
        // initialise a product id that does exit
        int id = 1;

        // create a mock retailer product
        Retailer retailer = new Retailer(1, "Coles", "coles.com.au", null, null, null);

        // when getRetailerByProductID in productRepository is called, return products
        when(this.productRepository.getRetailerByProductID(id)).thenReturn(retailer);

        // the list returned by productController when getRetailerByProductID is called should also be empty
        assertEquals(retailer, this.productController.getRetailerByProductID(id));
    }

    @Test
    void getAllCategories_ReturnEmpty_WhenNoProducts() {
        // when getAllCategories in productRepository is called, will return an empty list
        when(this.productRepository.getAllCategories()).thenReturn(new ArrayList<>());

        // the list returned by productController when getAllCategories is called should also be empty
        assertEquals(0, this.productController.getAllCategories().size());
    }

    @Test
    void getAllCategories_CorrectSize_WhenProductsExists() {
        // create a mock product lists and add two product
        ArrayList<Category> categories = new ArrayList<>();
        categories.add(new Category(1, "Dairy"));
        categories.add(new Category(2, "Fruits"));

        // when getAllProducts in getAllCategories is called, return categories
        when(this.productRepository.getAllCategories()).thenReturn(categories);

        // the list returned by productController when getAllCategories is called should also be empty
        assertEquals(2, this.productController.getAllCategories().size());
    }

    @Test
    void getAllCategories_CorrectProducts_WhenProductsExists() {
        // create a mock product lists and add two product
        ArrayList<Category> categories = new ArrayList<>();
        Category category = new Category(1, "Dairy");
        categories.add(category);

        // when getAllProducts in getAllCategories is called, return categories
        when(this.productRepository.getAllCategories()).thenReturn(categories);

        // the list returned by productController when getAllCategories is called should also be empty
        assertEquals(category, this.productController.getAllCategories().get(0));
    }

    @Test
    void getAllDiscountedProducts_ReturnEmpty_WhenNoProducts() {
        // when getAllDiscountedProducts in productRepository is called, will return an empty list
        when(this.productRepository.getAllDiscountedProducts()).thenReturn(new ArrayList<>());

        // the list returned by productController when getAllDiscountedProducts is called should also be empty
        assertEquals(0, this.productController.getAllDiscountedProducts().size());
    }

    @Test
    void getAllDiscountedProducts_CorrectSize_WhenProductsExists() {
        // create a mock product lists and add two product
        ArrayList<Product> products = new ArrayList<>();
        products.add(new Product(1, 1, "Woolworths", 3,
                "dairy", 1, "milk", 3.50, 4.50, "Woolworths milk",
                "Woolworths milk", 2.0, "path/image.jpg"));
        products.add(new Product(2, 2, "Coles", 3,
                "dairy", 1, "milk", 2.50, 3.00, "Coles milk",
                "Coles milk", 2.0, "path/image.jpg"));

        // when getAllDiscountedProducts in productRepository is called, return products
        when(this.productRepository.getAllDiscountedProducts()).thenReturn(products);

        // the list returned by productController when getAllDiscountedProducts is called should also be empty
        assertEquals(2, this.productController.getAllDiscountedProducts().size());
    }

    @Test
    void getAllDiscountedProducts_CorrectProducts_WhenProductsExists() {
        // create a mock product lists and add two product
        ArrayList<Product> products = new ArrayList<>();
        Product product = new Product(1, 1, "Woolworths", 3,
                "dairy", 1, "milk", 3.50, 4.50, "Woolworths milk",
                "Woolworths milk", 2.0, "path/image.jpg");
        products.add(product);

        // when getAllDiscountedProducts in productRepository is called, return products
        when(this.productRepository.getAllDiscountedProducts()).thenReturn(products);

        // the list returned by productController when getAllDiscountedProducts is called should also be empty
        assertEquals(product, this.productController.getAllDiscountedProducts().get(0));
    }

    @Test
    void getAllRetailers_ReturnEmpty_WhenNoProducts() {
        // when getAllRetailers in productRepository is called, will return an empty list
        when(this.productRepository.getAllRetailers()).thenReturn(new ArrayList<>());

        // the list returned by productController when getAllRetailers is called should also be empty
        assertEquals(0, this.productController.getAllRetailers().size());
    }

    @Test
    void getAllRetailers_CorrectSize_WhenProductsExists() {
        // create a mock retailers lists and add two retailers
        ArrayList<Retailer> retailers = new ArrayList<>();
        retailers.add(new Retailer(1, "Woolworths", "woolworths.com.au", "Woolworths description",
                "path/image.jpg", "path/image.jpg"));
        retailers.add(new Retailer(2, "Coles", "coles.com.au", "Coles description",
                "path/image.jpg", "path/image.jpg"));

        // when getAllProducts in productRepository is called, return retailers
        when(this.productRepository.getAllRetailers()).thenReturn(retailers);

        // the list returned by productController when getAllRetailers is called should also be empty
        assertEquals(2, this.productController.getAllRetailers().size());
    }

    @Test
    void getAllRetailers_CorrectProducts_WhenProductsExists() {
        // create a mock retailers lists and add two retailers
        ArrayList<Retailer> retailers = new ArrayList<>();
        Retailer retailer = new Retailer(1, "Woolworths", "woolworths.com.au", "Woolworths description",
                "path/image.jpg", "path/image.jpg");
        retailers.add(retailer);

        // when getAllRetailers in productRepository is called, return retailers
        when(this.productRepository.getAllRetailers()).thenReturn(retailers);

        // the list returned by productController when getAllRetailers is called should also be empty
        assertEquals(retailer, this.productController.getAllRetailers().get(0));
    }

}