package com.septp0402.productservice.repository;

public class JDBCConnection {
    public String DATABASE = "jdbc:sqlite:backend/product-service/database/product.db";

    public JDBCConnection() {
        System.out.println("Created JDBC Connection Object");
    }
}
