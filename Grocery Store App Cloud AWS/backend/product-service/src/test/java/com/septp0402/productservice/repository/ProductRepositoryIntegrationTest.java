package com.septp0402.productservice.repository;

import com.septp0402.productservice.model.*;
import org.junit.Ignore;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductRepositoryIntegrationTest {
    private ProductRepository productRepository;

    @BeforeEach
    public void setUp() {
        productRepository = new ProductRepository();

        // for tests set database path to from product-service
        productRepository.setJdbcConnectionPath("jdbc:sqlite:database/product.db");
    }

    @Test
    public void getAllProducts_ReturnsNotNull_WhenCalled() {
        // Call the method
        ArrayList<Product> products = productRepository.getAllProducts();

        // Check the result
        assertNotNull(products);
    }

    @Ignore // assumes a non-empty database is used. Change if otherwise
    @Test
    public void getAllProducts_ReturnsNonEmptyList_WhenCalled() {
        // Call the method
        ArrayList<Product> products = productRepository.getAllProducts();

        // check the result
        assertTrue(products.size() > 0);
    }

    @Ignore // To test change the value of numProducts to correct value
    @Test
    public void getAllProducts_ReturnsCorrectNmbProducts_WhenCalled() {
        // Call the method
        ArrayList<Product> products = productRepository.getAllProducts();
        int numProducts = 62;

        // check the result
        assertEquals(numProducts , products.size());
    }

    @Test
    public void getAllProducts_ReturnsProductsInAlphabeticalOrder_WhenCalled() {
        // Call the method
        ArrayList<Product> products = productRepository.getAllProducts();

        ArrayList<Product> productsCopy = new ArrayList<>();

        for (Product product : products) {
            productsCopy.add(product);
        }

        // Sort the productsCopy list based on the name attribute alphabetically
        Collections.sort(products, new Comparator<Product>() {
            @Override
            public int compare(Product product1, Product product2) {
                return product1.p_name().compareTo(product2.p_name());
            }
        });

        // check if the original list and copy are the same
        assertEquals(products, productsCopy);
    }

    @Test
    public void getSearchProducts_ReturnsNotNull_WhenCalledWithAnySearchTerm() {
        // initialise dummy search term
        String searchTerm = "ANonExistentTerm";

        // Call the method
        ArrayList<Product> products = productRepository.getSearchProducts(searchTerm);

        // Check the result
        assertNotNull(products);
    }

    @Test
    public void getSearchProducts_ReturnsEmpty_WhenCalledWithSearchTermForNoMatches() {
        // initialise dummy search term
        String searchTerm = "ANonExistentTerm";

        // Call the method
        ArrayList<Product> products = productRepository.getSearchProducts(searchTerm);

        // check the result
        assertTrue(products.size() == 0);
    }

    @Ignore // If fails, change value of searchTerm to one that exists in database
    @Test
    public void getSearchProducts_ReturnsNotEmpty_WhenCalledWithSearchTermForMatches() {
        // initialise dummy search term
        String searchTerm = "Milk";

        // Call the method
        ArrayList<Product> products = productRepository.getSearchProducts(searchTerm);

        // check the result
        assertTrue(products.size() > 0);
    }

    @Test
    public void getSearchProducts_ReturnsProductsInAlphabeticalOrder_WhenCalledWithSearchTermForMatches() {
        // initialise dummy search term
        String searchTerm = "Milk";

        // Call the method
        ArrayList<Product> products = productRepository.getSearchProducts(searchTerm);

        ArrayList<Product> productsCopy = new ArrayList<>();

        for (Product product : products) {
            productsCopy.add(product);
        }

        // Sort the productsCopy list based on the name attribute alphabetically
        Collections.sort(products, new Comparator<Product>() {
            @Override
            public int compare(Product product1, Product product2) {
                return product1.p_name().compareTo(product2.p_name());
            }
        });

        // check if the original list and copy are the same
        assertEquals(products, productsCopy);
    }

    @Ignore // To test change the value of originalNmbOfProducts to correct value
    @Test
    public void getSearchProducts_ReturnsCorrectNmbProducts_WhenCalledWithSearchTermForMatches() {
        // initialise dummy search term
        String searchTerm = "Milk";

        // Call the method
        ArrayList<Product> products = productRepository.getSearchProducts(searchTerm);

        int originalNmbOfProducts = 7;

        // check the result
        assertEquals(originalNmbOfProducts, products.size());
    }


    @Test
    public void getSearchProductsWithFilters_ReturnsNotNull_WhenCalledWithAnySearchCriteria() {
        // initialise category list
        ArrayList<String> categories = new ArrayList<>();
        categories.add("ANonExistentCategory");

        // initialise retailer list
        ArrayList<String> retailers = new ArrayList<>();
        retailers.add("ANonExistentCategory");

        // initialise search criteria object that won't have any products
        ProductSearchCriteria productSearchCriteria = new ProductSearchCriteria("ANonExistentTerm",
                categories, retailers, true, "A-Z");

        // Call the method
        ArrayList<Product> products = productRepository.getSearchProductsWithFilters(productSearchCriteria);

        // Check the result
        assertNotNull(products);
    }

    @Test
    public void getSearchProductsWithFilters_ReturnsEmpty_WhenCalledWithSearchCriteriaForNoMatches() {
        // initialise category list
        ArrayList<String> categories = new ArrayList<>();
        categories.add("ANonExistentCategory");

        // initialise retailer list
        ArrayList<String> retailers = new ArrayList<>();
        retailers.add("ANonExistentCategory");

        // initialise search criteria object that won't have any products
        ProductSearchCriteria productSearchCriteria = new ProductSearchCriteria("ANonExistentTerm",
                categories, retailers, true, "A-Z");

        // Call the method
        ArrayList<Product> products = productRepository.getSearchProductsWithFilters(productSearchCriteria);

        // Check the result
        assertTrue(products.size() == 0);
    }

    @Ignore // if fails ensure searchTerm is a value that exists in database
    @Test
    public void getSearchProductsWithFilters_ReturnsNotEmpty_WhenCalledWithSearchCriteriaForMatches() {
        // initialise search criteria object that won't have any products
        ProductSearchCriteria productSearchCriteria = new ProductSearchCriteria("Milk",
                null, null, false, null);

        // Call the method
        ArrayList<Product> products = productRepository.getSearchProductsWithFilters(productSearchCriteria);

        // Check the result
        assertTrue(products.size() > 0);
    }

    @Ignore // To test change the value of originalNmbOfProducts to correct value
    @Test
    public void getSearchProductsWithFilters_ReturnsCorrectNmbProducts_WhenCalledWithCorrectSearchTerm() {
        // initialise search criteria object that won't have any products
        ProductSearchCriteria productSearchCriteria = new ProductSearchCriteria("Milk",
                null, null, false, null);

        // Call the method
        ArrayList<Product> products = productRepository.getSearchProductsWithFilters(productSearchCriteria);

        int originalNmbOfProducts = 7;

        // check the result
        assertEquals(originalNmbOfProducts, products.size());
    }

    @Ignore // To test change the value of originalNmbOfProducts to correct value
    @Test
    public void getSearchProductsWithFilters_ReturnsCorrectNmbProducts_WhenCalledWithCorrectCategoryTerm() {
        // initialise category list
        ArrayList<String> categories = new ArrayList<>();
        categories.add("Dairy");

        // initialise search criteria object that won't have any products
        ProductSearchCriteria productSearchCriteria = new ProductSearchCriteria(null,
                categories, null, false, null);

        // Call the method
        ArrayList<Product> products = productRepository.getSearchProductsWithFilters(productSearchCriteria);

        int originalNmbOfProducts = 10;

        // check the result
        assertEquals(originalNmbOfProducts, products.size());
    }

    @Ignore // To test change the value of originalNmbOfProducts to correct value
    @Test
    public void getSearchProductsWithFilters_ReturnsCorrectNmbProducts_WhenCalledWithCorrectRetailerTerm() {
        // initialise retailers list
        ArrayList<String> retailers = new ArrayList<>();
        retailers.add("Coles");

        // initialise search criteria object that won't have any products
        ProductSearchCriteria productSearchCriteria = new ProductSearchCriteria(null,
                null, retailers, false, null);

        // Call the method
        ArrayList<Product> products = productRepository.getSearchProductsWithFilters(productSearchCriteria);

        int originalNmbOfProducts = 42;

        // check the result
        assertEquals(originalNmbOfProducts, products.size());
    }

    @Ignore // To test change the value of originalNmbOfProducts to correct value
    @Test
    public void getSearchProductsWithFilters_ReturnsCorrectNmbProducts_WhenCalledWithDiscountTermTrue() {
        // initialise search criteria object that won't have any products
        ProductSearchCriteria productSearchCriteria = new ProductSearchCriteria(null,
                null, null, true, null);

        // Call the method
        ArrayList<Product> products = productRepository.getSearchProductsWithFilters(productSearchCriteria);

        int originalNmbOfProducts = 23;

        // check the result
        assertEquals(originalNmbOfProducts, products.size());
    }

    @Test
    public void getSearchProductsWithFilters_ReturnsCorrectOrder_WhenCalledWithSpecificSortingTerm() {
        // initialise search criteria object that won't have any products
        ProductSearchCriteria productSearchCriteria = new ProductSearchCriteria(null,
                null, null, true, "z-a");

        // Call the method
        ArrayList<Product> products = productRepository.getSearchProductsWithFilters(productSearchCriteria);

        ArrayList<Product> productsCopy = new ArrayList<>();

        for (Product product : products) {
            productsCopy.add(product);
        }

        // Sort the productsCopy list based on the name attribute reverse alphabetically
        Collections.sort(products, new Comparator<Product>() {
            @Override
            public int compare(Product product1, Product product2) {
                return product2.p_name().compareTo(product1.p_name());
            }
        });

        // check if the original list and copy are the same
        assertEquals(products, productsCopy);
    }

    @Ignore // To test change the value of originalNmbOfProducts to correct value
    @Test
    public void getSearchProductsWithFilters_ReturnsCorrectNmbProducts_WhenCalledWithAllFilters() {
        // initialise category list
        ArrayList<String> categories = new ArrayList<>();
        categories.add("Dairy");

        // initialise retailer list
        ArrayList<String> retailers = new ArrayList<>();
        retailers.add("Coles");

        // initialise search criteria object that won't have any products
        ProductSearchCriteria productSearchCriteria = new ProductSearchCriteria("Milk",
                categories, retailers, true, "price low-high");

        // Call the method
        ArrayList<Product> products = productRepository.getSearchProductsWithFilters(productSearchCriteria);

        int originalNmbOfProducts = 1;

        // check the result
        assertEquals(originalNmbOfProducts, products.size());
    }

    @Test
    public void getSimilarProductsByID_ReturnsNotNull_WhenCalledWithAnySimilarProductCriteria() {
        // initialise dummy search term
        SimilarProductsCriteria similarProductsCriteria = new SimilarProductsCriteria(100, 100);

        // Call the method
        ArrayList<Product> products = productRepository.getSimilarProductsByID(similarProductsCriteria);

        // Check the result
        assertNotNull(products);
    }

    @Ignore // if fails ensure values in SimilarProductsCriteria does not exist in database
    @Test
    public void getSimilarProductsByID_ReturnsEmpty_WhenCalledWithSimilarProductCriteriaForNoMatches() {
        // initialise dummy search term
        SimilarProductsCriteria similarProductsCriteria = new SimilarProductsCriteria(999, 999);

        // Call the method
        ArrayList<Product> products = productRepository.getSimilarProductsByID(similarProductsCriteria);

        // check the result
        assertTrue(products.size() == 0);
    }

    @Ignore // if fails ensure values in SimilarProductsCriteria does  exist in database
    @Test
    public void getSimilarProductsByID_ReturnsNotEmpty_WhenCalledWithSimilarProductCriteriaForMatches() {
        // initialise dummy search term
        SimilarProductsCriteria similarProductsCriteria = new SimilarProductsCriteria(4, 1);

        // Call the method
        ArrayList<Product> products = productRepository.getSimilarProductsByID(similarProductsCriteria);

        // check the result
        assertTrue(products.size() > 0);
    }

    @Test
    public void getSimilarProductsByID_ReturnsProductsInAscendingPrice_WhenCalledWithSimilarProductCriteriaForMatches() {
        // initialise dummy search term
        SimilarProductsCriteria similarProductsCriteria = new SimilarProductsCriteria(4, 1);

        // Call the method
        ArrayList<Product> products = productRepository.getSimilarProductsByID(similarProductsCriteria);

        ArrayList<Product> productsCopy = new ArrayList<>();

        for (Product product : products) {
            productsCopy.add(product);
        }

        // Sort the productsCopy list based on the name attribute alphabetically
        Collections.sort(products, new Comparator<Product>() {
            @Override
            public int compare(Product product1, Product product2) {
                return product1.p_price().compareTo(product2.p_price());
            }
        });

        // check if the original list and copy are the same
        assertEquals(products, productsCopy);
    }

    @Ignore // To test change the value of originalNmbOfProducts to correct value
    @Test
    public void getSimilarProductsByID_ReturnsCorrectNmbProducts_WhenCalledWithSimilarProductCriteriaForMatches() {
        // initialise dummy search term
        SimilarProductsCriteria similarProductsCriteria = new SimilarProductsCriteria(4, 1);

        // Call the method
        ArrayList<Product> products = productRepository.getSimilarProductsByID(similarProductsCriteria);

        int originalNmbOfProducts = 3;

        // check the result
        assertEquals(originalNmbOfProducts, products.size());
    }

    @Test
    public void getUserReviewsByProductID_ReturnsNotNull_WhenProductWithNoReviewsCalled() {
        // initialise dummy product id
        int id = 999;

        // Call the method
        ArrayList<UserReview> reviews = productRepository.getUserReviewsByProductID(id);

        // Check the result
        assertNotNull(reviews);
    }

    @Ignore // if fails ensure id does not exist in database
    @Test
    public void getUserReviewsByProductID_ReturnsEmpty_WhenProductWithNoReviewsCalled() {
        // initialise dummy product id
        int id = 999;

        // Call the method
        ArrayList<UserReview> reviews = productRepository.getUserReviewsByProductID(id);

        // check the result
        assertTrue(reviews.size() == 0);
    }

    @Ignore // if fails ensure id does exist in database
    @Test
    public void getUserReviewsByProductID_ReturnsNotEmpty_WhenProductWithReviewsCalled() {
        // initialise dummy product id
        int id = 3;

        // Call the method
        ArrayList<UserReview> reviews = productRepository.getUserReviewsByProductID(id);

        // check the result
        assertTrue(reviews.size() > 0);
    }

    @Test
    public void getUserReviewsByProductID_ReturnsInAscendingRating_WhenProductWithReviewsCalled() {
        // initialise dummy product id
        int id = 3;

        // Call the method
        ArrayList<UserReview> reviews = productRepository.getUserReviewsByProductID(id);

        ArrayList<UserReview> reviewsCopy = new ArrayList<>();

        for (UserReview review : reviews) {
            reviewsCopy.add(review);
        }

        // Sort the reviewsCopy list in ascending ratings order
        Collections.sort(reviews, new Comparator<UserReview>() {
            @Override
            public int compare(UserReview review1, UserReview review2) {
                Double double1 = (Double) review1.review_rat();
                Double double2 = (Double) review2.review_rat();
                return double1.compareTo(double2);
            }
        });

        // check if the original list and copy are the same
        assertEquals(reviews, reviewsCopy);
    }

    @Ignore // To test change the value of originalNmbOfReviews to correct value
    @Test
    public void getUserReviewsByProductID_ReturnsCorrectNmbReviews_WhenProductWithReviewsCalled() {
        // initialise dummy product id
        int id = 3;

        // Call the method
        ArrayList<UserReview> reviews = productRepository.getUserReviewsByProductID(id);

        int originalNmbOfReviews = 1;

        // check the result
        assertEquals(originalNmbOfReviews, reviews.size());
    }

    @Ignore // if fails ensure id does not exist in database
    @Test
    public void getRetailerByProductID_ReturnsNull_WhenIncorrectProductID() {
        // initialise dummy product id
        int id = 999;

        // Call the method
        Retailer retailer = productRepository.getRetailerByProductID(id);

        // Check the result
        assertNull(retailer);
    }

    @Ignore // if fails ensure id does exist in database
    @Test
    public void getRetailerByProductID_ReturnsNotNull_WhenCorrectProductID() {
        // initialise dummy product id
        int id = 1;

        // Call the method
        Retailer retailer = productRepository.getRetailerByProductID(id);

        // Check the result
        assertNotNull(retailer);
    }

    @Ignore // To test change the value of expectedRetailer to correct value
    @Test
    public void getRetailerByProductID_ReturnsCorrectReview_WhenCorrectProductID() {
        // initialise dummy product id
        int id = 1;

        // Call the method
        Retailer retailer = productRepository.getRetailerByProductID(id);

        // Expected retailer product
        Retailer expectedRetailer = new Retailer(1, "Woolworths", "woolworths.com.au", "The fresh food people", "path_to_img", "path_to_img");

        // Check the result
        assertEquals(expectedRetailer, retailer);
    }

    @Test
    public void getAllCategories_ReturnsNotNull_WhenCalled() {
        // Call the method
        ArrayList<Category> categories = productRepository.getAllCategories();

        // Check the result
        assertNotNull(categories);
    }

    @Ignore // assumes a non-empty database is used. Change if otherwise
    @Test
    public void getAllCategories_ReturnsNonEmptyList_WhenCalled() {
        // Call the method
        ArrayList<Category> categories = productRepository.getAllCategories();

        // check the result
        assertTrue(categories.size() > 0);
    }

    @Ignore // To test change the value of numCategories to correct value
    @Test
    public void getAllCategories_ReturnsCorrectNmbProducts_WhenCalled() {
        // Call the method
        ArrayList<Category> categories = productRepository.getAllCategories();
        int numCategories = 8;

        // check the result
        assertEquals(numCategories , categories.size());
    }

    @Test
    public void getAllCategories_ReturnsCategoriesInAlphabeticalOrder_WhenCalled() {
        // Call the method
        ArrayList<Category> categories = productRepository.getAllCategories();

        ArrayList<Category> categoriesCopy = new ArrayList<>();

        for (Category category : categories) {
            categoriesCopy.add(category);
        }

        // Sort the categoriesCopy list based on the name attribute alphabetically
        Collections.sort(categories, new Comparator<Category>() {
            @Override
            public int compare(Category category1, Category category2) {
                return category1.cat_name().compareTo(category2.cat_name());
            }
        });

        // check if the original list and copy are the same
        assertEquals(categories, categoriesCopy);
    }

    @Test
    public void getAllDiscountedProducts_ReturnsNotNull_WhenCalled() {
        // Call the method
        ArrayList<Product> products = productRepository.getAllDiscountedProducts();

        // Check the result
        assertNotNull(products);
    }

    @Ignore // assumes a non-empty database is used. Change if otherwise
    @Test
    public void getAllDiscountedProducts_ReturnsNonEmptyList_WhenCalled() {
        // Call the method
        ArrayList<Product> products = productRepository.getAllDiscountedProducts();

        // check the result
        assertTrue(products.size() > 0);
    }

    @Ignore // To test change the value of numProducts to correct value
    @Test
    public void getAllDiscountedProducts_ReturnsCorrectNmbProducts_WhenCalled() {
        // Call the method
        ArrayList<Product> products = productRepository.getAllDiscountedProducts();
        int numProducts = 23;

        // check the result
        assertEquals(numProducts , products.size());
    }

    @Test
    public void getAllDiscountedProducts_OrgPriceNotNull_WhenCalled() {
        // Call the method
        ArrayList<Product> products = productRepository.getAllDiscountedProducts();

        // iterate through each returned product
        for (Product product : products) {
            // ensure original price parameter is not null
            assertNotNull(product.p_org_price());
        }
    }

    @Test
    public void getAllDiscountedProducts_ReturnsProductsInAscendingPriceOrder_WhenCalled() {
        // Call the method
        ArrayList<Product> products = productRepository.getAllDiscountedProducts();

        ArrayList<Product> productsCopy = new ArrayList<>();

        for (Product product : products) {
            productsCopy.add(product);
        }

        // Sort the productsCopy list in descending (org_price - price) order
        Collections.sort(products, new Comparator<Product>() {
            @Override
            public int compare(Product product1, Product product2) {
                Double value1 = (Double) product1.p_org_price() - product1.p_price();
                Double value2 = (Double) product2.p_org_price() - product2.p_price();

                return value2.compareTo(value1);
            }
        });

        // check if the original list and copy are the same
        assertEquals(products, productsCopy);
    }

    @Test
    public void getAllRetailers_ReturnsNotNull_WhenCalled() {
        // Call the method
        ArrayList<Retailer> retailers = productRepository.getAllRetailers();

        // Check the result
        assertNotNull(retailers);
    }

    @Ignore // assumes a non-empty database is used. Change if otherwise
    @Test
    public void getAllRetailers_ReturnsNonEmptyList_WhenCalled() {
        // Call the method
        ArrayList<Retailer> retailers = productRepository.getAllRetailers();

        // check the result
        assertTrue(retailers.size() > 0);
    }

    @Ignore // To test change the value of nmbRetailers to correct value
    @Test
    public void getAllRetailers_ReturnsCorrectNmbProducts_WhenCalled() {
        // Call the method
        ArrayList<Retailer> retailers = productRepository.getAllRetailers();
        int nmbRetailers = 2;

        // check the result
        assertEquals(nmbRetailers , retailers.size());
    }

    @Test
    public void getAllRetailers_ReturnsRetailersInAlphabeticalOrder_WhenCalled() {
        // Call the method
        ArrayList<Retailer> retailers = productRepository.getAllRetailers();

        ArrayList<Retailer> retailersCopy = new ArrayList<>();

        for (Retailer retailer : retailers) {
            retailersCopy.add(retailer);
        }

        // Sort the retailersCopy list based on the name attribute alphabetically
        Collections.sort(retailers, new Comparator<Retailer>() {
            @Override
            public int compare(Retailer retailer1, Retailer retailer2) {
                return retailer1.ret_name().compareTo(retailer2.ret_name());
            }
        });

        // check if the original list and copy are the same
        assertEquals(retailers, retailersCopy);
    }
}