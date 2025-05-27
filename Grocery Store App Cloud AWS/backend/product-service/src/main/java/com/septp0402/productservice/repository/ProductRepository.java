package com.septp0402.productservice.repository;

import com.septp0402.productservice.model.*;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;

@Repository
public class ProductRepository {
    private JDBCConnection jdbcConnection = new JDBCConnection();

    public void setJdbcConnectionPath(String newPath) {
        jdbcConnection.DATABASE = newPath;
    }

    /**
     * Get all products in database
     */
    public ArrayList<Product> getAllProducts() {
        ArrayList<Product> products = new ArrayList<>();

        Connection connection = null;

        try {
            connection = DriverManager.getConnection(jdbcConnection.DATABASE);

            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            String query = "SELECT p.*, c.cat_name as p_cat_name, sc.subcat_name as p_subcat_name, r.ret_name as p_ret_name FROM Product p "
                    + "LEFT JOIN Category C on p.p_cat_id = C.cat_id "
                    + "LEFT JOIN SubCategory SC on p.p_subcat_id = SC.subcat_id "
                    + "LEFT JOIN Retailer R on p.p_retailer_id = R.ret_id "
                    + "ORDER BY p.p_name ASC";

            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                int product_id = resultSet.getInt("p_id");
                int product_retailer_id = resultSet.getInt("p_retailer_id");
                String product_retailer_name = resultSet.getString("p_ret_name");
                int product_cat_id = resultSet.getInt("p_cat_id");
                String product_cat_name = resultSet.getString("p_cat_name");
                int product_subcat_id = resultSet.getInt("p_subcat_id");
                String product_subcat_name = resultSet.getString("p_subcat_name");
                double product_price = resultSet.getDouble("p_price");
                String product_name = resultSet.getString("p_name");
                String product_desc = resultSet.getString("p_desc");
                String product_weight = resultSet.getString("p_weight");
                String product_image = resultSet.getString("p_img");
                String product_org_price = resultSet.getString("p_org_price");
                boolean wasNull = resultSet.wasNull();

                if (!wasNull) {
                    double product_org_price_notnull = Double.parseDouble(product_org_price);
                    products.add(
                            new Product(
                                    product_id,
                                    product_retailer_id,
                                    product_retailer_name,
                                    product_cat_id,
                                    product_cat_name,
                                    product_subcat_id,
                                    product_subcat_name,
                                    product_price,
                                    product_org_price_notnull,
                                    product_name,
                                    product_desc,
                                    Double.valueOf(product_weight),
                                    product_image
                            )
                    );
                }
                else {
                    products.add(
                            new Product(
                                    product_id,
                                    product_retailer_id,
                                    product_retailer_name,
                                    product_cat_id,
                                    product_cat_name,
                                    product_subcat_id,
                                    product_subcat_name,
                                    product_price,
                                    null,
                                    product_name,
                                    product_desc,
                                    Double.valueOf(product_weight),
                                    product_image
                            )
                    );
                }


            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return products;
    }

    public ArrayList<Product> getSearchProducts(String searchTerm) {
        ArrayList<Product> products = new ArrayList<>();

        Connection connection = null;

        try {
            connection = DriverManager.getConnection(jdbcConnection.DATABASE);

            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT p.*, c.cat_name as p_cat_name, sc.subcat_name as p_subcat_name, r.ret_name as p_ret_name FROM Product p " +
                            "LEFT JOIN Category c ON p.p_cat_id = c.cat_id " +
                            "LEFT JOIN SubCategory SC on p.p_subcat_id = SC.subcat_id " +
                            "LEFT JOIN Retailer R on p.p_retailer_id = R.ret_id " +
                            "WHERE p.p_name LIKE ? OR c.cat_name LIKE ? OR SC.subcat_name LIKE ? OR R.ret_name LIKE ? " +
                            "ORDER BY p.p_name ASC "
            );

            // Setting parameters for the prepared statement
            preparedStatement.setString(1, "%" + searchTerm + "%");
            preparedStatement.setString(2, "%" + searchTerm + "%");
            preparedStatement.setString(3, "%" + searchTerm + "%");
            preparedStatement.setString(4, "%" + searchTerm + "%");


            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int product_id = resultSet.getInt("p_id");
                int product_retailer_id = resultSet.getInt("p_retailer_id");
                String product_retailer_name = resultSet.getString("p_ret_name");
                int product_cat_id = resultSet.getInt("p_cat_id");
                String product_cat_name = resultSet.getString("p_cat_name");
                int product_subcat_id = resultSet.getInt("p_subcat_id");
                String product_subcat_name = resultSet.getString("p_subcat_name");
                double product_price = resultSet.getDouble("p_price");
                String product_name = resultSet.getString("p_name");
                String product_desc = resultSet.getString("p_desc");
                String product_weight = resultSet.getString("p_weight");
                String product_image = resultSet.getString("p_img");
                String product_org_price = resultSet.getString("p_org_price");
                boolean wasNull = resultSet.wasNull();

                if(!wasNull) {
                    double product_org_price_notnull = Double.parseDouble(product_org_price);
                    products.add(
                            new Product(
                                    product_id,
                                    product_retailer_id,
                                    product_retailer_name,
                                    product_cat_id,
                                    product_cat_name,
                                    product_subcat_id,
                                    product_subcat_name,
                                    product_price,
                                    product_org_price_notnull,
                                    product_name,
                                    product_desc,
                                    Double.valueOf(product_weight),
                                    product_image
                            )
                    );
                }
                else {
                    products.add(
                            new Product(
                                    product_id,
                                    product_retailer_id,
                                    product_retailer_name,
                                    product_cat_id,
                                    product_cat_name,
                                    product_subcat_id,
                                    product_subcat_name,
                                    product_price,
                                    null,
                                    product_name,
                                    product_desc,
                                    Double.valueOf(product_weight),
                                    product_image
                            )
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return products;
    }

    public ArrayList<Product> getSearchProductsWithFilters(ProductSearchCriteria productSearchCriteria) {
        // initialise a lise of products to return
        ArrayList<Product> products = new ArrayList<>();

        // set initial query statement
        StringBuilder queryBuilder = new StringBuilder("SELECT p.*, c.cat_name as p_cat_name, sc.subcat_name as p_subcat_name, r.ret_name as p_ret_name FROM Product p " +
                "LEFT JOIN Category c ON p.p_cat_id = c.cat_id " +
                "LEFT JOIN SubCategory SC on p.p_subcat_id = SC.subcat_id " +
                "LEFT JOIN Retailer r ON p.p_retailer_id = r.ret_id WHERE 1=1 ");

        // Search term filter
        if (productSearchCriteria.searchTerm() != null) {
            queryBuilder.append("AND (p.p_name LIKE ? OR c.cat_name LIKE ? OR SC.subcat_name LIKE ? OR r.ret_name LIKE ?) ");
        }

        // Category filter
        if (productSearchCriteria.categoryFilter() != null && !productSearchCriteria.categoryFilter().isEmpty()) {
            queryBuilder.append("AND c.cat_name IN (");
            for (int i = 0; i < productSearchCriteria.categoryFilter().size(); i++) {
                queryBuilder.append("?");
                if (i < productSearchCriteria.categoryFilter().size() - 1) {
                    queryBuilder.append(", ");
                }
            }
            queryBuilder.append(") ");
        }

        // Retailer filter
        if (productSearchCriteria.retailerFilter() != null && !productSearchCriteria.retailerFilter().isEmpty()) {
            queryBuilder.append("AND r.ret_name IN (");
            for (int i = 0; i < productSearchCriteria.retailerFilter().size(); i++) {
                queryBuilder.append("?");
                if (i < productSearchCriteria.retailerFilter().size() - 1) {
                    queryBuilder.append(", ");
                }
            }
            queryBuilder.append(") ");
        }

        // Discount filter
        if (productSearchCriteria.discountFilter()) {
            queryBuilder.append("AND p.p_org_price IS NOT NULL ");
        }

        // Sorting filter
        String sortingString;
        // If sorting filter is not value, make all lower case
        if (productSearchCriteria.sortingFilter() != null) {
            sortingString = productSearchCriteria.sortingFilter().toLowerCase();
        }
        // else if null, make a-z
        else {
            sortingString = "a-z";
        }
        // determine which sorting method to use
        switch (sortingString) {
            case "z-a":
                queryBuilder.append("ORDER BY p.p_name DESC ");
                break;
            case "price low-high":
                queryBuilder.append("ORDER BY p.p_price ASC ");
                break;
            case "price high-low":
                queryBuilder.append("ORDER BY p.p_price DESC ");
                break;
            case "unit price low-high":
                queryBuilder.append("ORDER BY p.p_price/p.p_weight ASC ");
                break;
            case "unit price high-low":
                queryBuilder.append("ORDER BY p.p_price/p.p_weight DESC ");
                break;
            default: // "a-z" and any other unexpected values
                queryBuilder.append("ORDER BY p.p_name ASC ");
                break;
        }

        Connection connection = null;

        // try to connect to database
        try {
            connection = DriverManager.getConnection(jdbcConnection.DATABASE);

            // set the prepared statement form string builder
            PreparedStatement preparedStatement = connection.prepareStatement(queryBuilder.toString());

            // set the start parameter index
            int paramIndex = 1;

            // if search term was not null, set first 3 parameters
            if (productSearchCriteria.searchTerm() != null) {
                preparedStatement.setString(paramIndex++, "%" + productSearchCriteria.searchTerm() + "%");
                preparedStatement.setString(paramIndex++, "%" + productSearchCriteria.searchTerm() + "%");
                preparedStatement.setString(paramIndex++, "%" + productSearchCriteria.searchTerm() + "%");
                preparedStatement.setString(paramIndex++, "%" + productSearchCriteria.searchTerm() + "%");
            }

            // if category filter is not empty or null, set parameters for the number of category filters
            if (productSearchCriteria.categoryFilter() != null && !productSearchCriteria.categoryFilter().isEmpty()) {
                for (String category : productSearchCriteria.categoryFilter()) {
                    preparedStatement.setString(paramIndex++, category);
                }
            }

            // if category filter is not empty or null, set parameters for the number of retailer filters
            if (productSearchCriteria.retailerFilter() != null && !productSearchCriteria.retailerFilter().isEmpty()) {
                for (String retailer : productSearchCriteria.retailerFilter()) {
                    preparedStatement.setString(paramIndex++, retailer);
                }
            }

            // execute query
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                // get each column value
                int product_id = resultSet.getInt("p_id");
                int product_retailer_id = resultSet.getInt("p_retailer_id");
                String product_retailer_name = resultSet.getString("p_ret_name");
                int product_cat_id = resultSet.getInt("p_cat_id");
                String product_cat_name = resultSet.getString("p_cat_name");
                int product_subcat_id = resultSet.getInt("p_subcat_id");
                String product_subcat_name = resultSet.getString("p_subcat_name");
                double product_price = resultSet.getDouble("p_price");
                String product_name = resultSet.getString("p_name");
                String product_desc = resultSet.getString("p_desc");
                String product_weight = resultSet.getString("p_weight");
                String product_image = resultSet.getString("p_img");
                String product_org_price = resultSet.getString("p_org_price");
                boolean wasNull = resultSet.wasNull();

                // add as a product to product list
                if(!wasNull) {
                    double product_org_price_notnull = Double.parseDouble(product_org_price);
                    products.add(
                            new Product(
                                    product_id,
                                    product_retailer_id,
                                    product_retailer_name,
                                    product_cat_id,
                                    product_cat_name,
                                    product_subcat_id,
                                    product_subcat_name,
                                    product_price,
                                    product_org_price_notnull,
                                    product_name,
                                    product_desc,
                                    Double.valueOf(product_weight),
                                    product_image
                            )
                    );
                }
                else {
                    products.add(
                            new Product(
                                    product_id,
                                    product_retailer_id,
                                    product_retailer_name,
                                    product_cat_id,
                                    product_cat_name,
                                    product_subcat_id,
                                    product_subcat_name,
                                    product_price,
                                    null,
                                    product_name,
                                    product_desc,
                                    Double.valueOf(product_weight),
                                    product_image
                            )
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        // return the product list
        return products;
    }

    public ArrayList<Product> getSimilarProductsByID(SimilarProductsCriteria similarCriteria) {

        // initialise a lise of products to return
        ArrayList<Product> products = new ArrayList<>();
        
        Connection connection = null;

        try {
            connection = DriverManager.getConnection(jdbcConnection.DATABASE);

            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT p.*, c.cat_name as p_cat_name, sc.subcat_name as p_subcat_name, r.ret_name as p_ret_name FROM Product p " +
                            "LEFT JOIN Category c ON p.p_cat_id = c.cat_id " +
                            "LEFT JOIN SubCategory SC on p.p_subcat_id = SC.subcat_id " +
                            "LEFT JOIN Retailer R on p.p_retailer_id = R.ret_id " +
                            "WHERE p.p_subcat_id LIKE ? AND p.p_id NOT LIKE ? " +
                            "ORDER BY p.p_price ASC "
        );

            // Setting parameters for the prepared statement
            preparedStatement.setInt(1, similarCriteria.p_sub_cat());
            preparedStatement.setInt(2, similarCriteria.p_id());

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int product_id = resultSet.getInt("p_id");
                int product_retailer_id = resultSet.getInt("p_retailer_id");
                String product_retailer_name = resultSet.getString("p_ret_name");
                int product_cat_id = resultSet.getInt("p_cat_id");
                String product_cat_name = resultSet.getString("p_cat_name");
                int product_subcat_id = resultSet.getInt("p_subcat_id");
                String product_subcat_name = resultSet.getString("p_subcat_name");
                double product_price = resultSet.getDouble("p_price");
                String product_name = resultSet.getString("p_name");
                String product_desc = resultSet.getString("p_desc");
                String product_weight = resultSet.getString("p_weight");
                String product_image = resultSet.getString("p_img");
                String product_org_price = resultSet.getString("p_org_price");
                boolean wasNull = resultSet.wasNull();

                if(!wasNull) {
                    double product_org_price_notnull = Double.parseDouble(product_org_price);
                    products.add(
                            new Product(
                                    product_id,
                                    product_retailer_id,
                                    product_retailer_name,
                                    product_cat_id,
                                    product_cat_name,
                                    product_subcat_id,
                                    product_subcat_name,
                                    product_price,
                                    product_org_price_notnull,
                                    product_name,
                                    product_desc,
                                    Double.valueOf(product_weight),
                                    product_image
                            )
                    );
                }
                else {
                    products.add(
                            new Product(
                                    product_id,
                                    product_retailer_id,
                                    product_retailer_name,
                                    product_cat_id,
                                    product_cat_name,
                                    product_subcat_id,
                                    product_subcat_name,
                                    product_price,
                                    null,
                                    product_name,
                                    product_desc,
                                    Double.valueOf(product_weight),
                                    product_image
                            )
                    );
                }
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return products;
    }

    public ArrayList<UserReview> getUserReviewsByProductID(int pID) {
        
        ArrayList<UserReview> reviews = new ArrayList<>();
        
        Connection connection = null;

        try {
            connection = DriverManager.getConnection(jdbcConnection.DATABASE);

            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT r.* FROM UserReview r " +
                            "WHERE r.p_id LIKE ? " +
                            "ORDER BY r.review_rat ASC "
        );

            // Setting parameters for the prepared statement
            preparedStatement.setInt(1, pID);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int review_id = resultSet.getInt("review_id");
                int c_id = resultSet.getInt("c_id");
                int p_id = resultSet.getInt("p_id");
                String review_det = resultSet.getString("review_det");
                double review_rat = resultSet.getDouble("review_rat");

                reviews.add(
                    new UserReview(
                        review_id,
                        c_id,
                        p_id,
                        review_det,
                        review_rat
                    )
                );
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return reviews;
    }

    public Retailer getRetailerByProductID(int pID) {
        
        Retailer retailer = null;
        Connection connection = null;

        try {
            connection = DriverManager.getConnection(jdbcConnection.DATABASE);

            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT r.* FROM Retailer r " +
                            "LEFT JOIN Product p ON r.ret_id = p.p_retailer_id " +
                            "WHERE p.p_id LIKE ? "
        );

            // Setting parameters for the prepared statement
            preparedStatement.setInt(1, pID);

            ResultSet resultSet = preparedStatement.executeQuery();

            
            while (resultSet.next()) {

                int ret_id = resultSet.getInt("ret_id");
                String ret_name = resultSet.getString("ret_name");
                String ret_web = resultSet.getString("ret_web");
                String ret_det = resultSet.getString("ret_det");
                String ret_small_img = resultSet.getString("ret_small_img");
                String ret_caro_img = resultSet.getString("ret_caro_img");

                retailer = new Retailer(ret_id, ret_name, ret_web, ret_det, ret_small_img, ret_caro_img);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return retailer;
    }

    public ArrayList<Category> getAllCategories() {
        // initialise categories list to return
        ArrayList<Category> categories = new ArrayList<>();

        Connection connection = null;

        try {
            // establish connection
            connection = DriverManager.getConnection(jdbcConnection.DATABASE);

            // prepare statement
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM Category " +
                            "ORDER BY cat_name ASC"
            );


            // execute query
            ResultSet resultSet = preparedStatement.executeQuery();

            // go through returned categories
            while (resultSet.next()) {
                // get attributes
                int cat_id = resultSet.getInt("cat_id");
                String cat_name = resultSet.getString("cat_name");

                // add each category to a categories
                categories.add(
                        new Category(
                                cat_id,
                                cat_name
                        )
                );
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return categories;
    }

    public ArrayList<Product> getAllDiscountedProducts() {
        // initialise products list to return
        ArrayList<Product> products = new ArrayList<>();

        Connection connection = null;

        try {
            // establish connection
            connection = DriverManager.getConnection(jdbcConnection.DATABASE);

            // prepare statement
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT p.*, c.cat_name as p_cat_name, sc.subcat_name as p_subcat_name, r.ret_name as p_ret_name FROM Product p " +
                            "LEFT JOIN Category c ON p.p_cat_id = c.cat_id " +
                            "LEFT JOIN SubCategory SC on p.p_subcat_id = SC.subcat_id " +
                            "LEFT JOIN Retailer R on p.p_retailer_id = R.ret_id " +
                            "WHERE p.p_org_price IS NOT NULL " +
                            "ORDER BY (p.p_org_price - p.p_price) DESC"
            );


            // execute query
            ResultSet resultSet = preparedStatement.executeQuery();

            // go through returned products
            while (resultSet.next()) {
                // get attributes
                int product_id = resultSet.getInt("p_id");
                int product_retailer_id = resultSet.getInt("p_retailer_id");
                String product_retailer_name = resultSet.getString("p_ret_name");
                int product_cat_id = resultSet.getInt("p_cat_id");
                String product_cat_name = resultSet.getString("p_cat_name");
                int product_subcat_id = resultSet.getInt("p_subcat_id");
                String product_subcat_name = resultSet.getString("p_subcat_name");
                double product_price = resultSet.getDouble("p_price");
                String product_name = resultSet.getString("p_name");
                String product_desc = resultSet.getString("p_desc");
                String product_weight = resultSet.getString("p_weight");
                String product_image = resultSet.getString("p_img");
                String product_org_price = resultSet.getString("p_org_price");

                // convert product_org_price to double
                double product_org_price_notnull = Double.parseDouble(product_org_price);

                // add each category to a products
                products.add(
                        new Product(
                                product_id,
                                product_retailer_id,
                                product_retailer_name,
                                product_cat_id,
                                product_cat_name,
                                product_subcat_id,
                                product_subcat_name,
                                product_price,
                                product_org_price_notnull,
                                product_name,
                                product_desc,
                                Double.valueOf(product_weight),
                                product_image
                        )
                );
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return products;
    }

    public ArrayList<Retailer> getAllRetailers() {
        // initialise retailers list to return
        ArrayList<Retailer> retailers = new ArrayList<>();

        Connection connection = null;

        try {
            // establish connection
            connection = DriverManager.getConnection(jdbcConnection.DATABASE);

            // prepare statement
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM Retailer " +
                            "ORDER BY ret_name ASC"
            );


            // execute query
            ResultSet resultSet = preparedStatement.executeQuery();

            // go through returned retailers
            while (resultSet.next()) {
                // get attributes
                int ret_id = resultSet.getInt("ret_id");
                String ret_name = resultSet.getString("ret_name");
                String ret_web = resultSet.getString("ret_web");
                String ret_det = resultSet.getString("ret_det");
                String ret_small_img = resultSet.getString("ret_small_img");
                String ret_caro_img = resultSet.getString("ret_caro_img");


                // add each retailer to a retailers
                retailers.add(
                        new Retailer(
                                ret_id, ret_name, ret_web, ret_det, ret_small_img, ret_caro_img
                        ));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return retailers;
    }

    public Product getProductByID(int pID) {
        Product product = null;

        Connection connection = null;

        try {
            connection = DriverManager.getConnection(jdbcConnection.DATABASE);

            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            String query = "SELECT p.*, c.cat_name as p_cat_name, sc.subcat_name as p_subcat_name, r.ret_name as p_ret_name FROM Product p "
                    + "LEFT JOIN Category C on p.p_cat_id = C.cat_id "
                    + "LEFT JOIN SubCategory SC on p.p_subcat_id = SC.subcat_id "
                    + "LEFT JOIN Retailer R on p.p_retailer_id = R.ret_id "
                    + "WHERE p.p_id = " + pID;

            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                int product_id = resultSet.getInt("p_id");
                int product_retailer_id = resultSet.getInt("p_retailer_id");
                String product_retailer_name = resultSet.getString("p_ret_name");
                int product_cat_id = resultSet.getInt("p_cat_id");
                String product_cat_name = resultSet.getString("p_cat_name");
                int product_subcat_id = resultSet.getInt("p_subcat_id");
                String product_subcat_name = resultSet.getString("p_subcat_name");
                double product_price = resultSet.getDouble("p_price");
                String product_name = resultSet.getString("p_name");
                String product_desc = resultSet.getString("p_desc");
                String product_weight = resultSet.getString("p_weight");
                String product_image = resultSet.getString("p_img");
                String product_org_price = resultSet.getString("p_org_price");
                boolean wasNull = resultSet.wasNull();

                if (!wasNull) {
                    double product_org_price_notnull = Double.parseDouble(product_org_price);
                    product = new Product(
                                    product_id,
                                    product_retailer_id,
                                    product_retailer_name,
                                    product_cat_id,
                                    product_cat_name,
                                    product_subcat_id,
                                    product_subcat_name,
                                    product_price,
                                    product_org_price_notnull,
                                    product_name,
                                    product_desc,
                                    Double.valueOf(product_weight),
                                    product_image
                    );
                }
                else {
                    product = new Product(
                                    product_id,
                                    product_retailer_id,
                                    product_retailer_name,
                                    product_cat_id,
                                    product_cat_name,
                                    product_subcat_id,
                                    product_subcat_name,
                                    product_price,
                                    null,
                                    product_name,
                                    product_desc,
                                    Double.valueOf(product_weight),
                                    product_image
                    );
                }

            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return product;
    }

}
