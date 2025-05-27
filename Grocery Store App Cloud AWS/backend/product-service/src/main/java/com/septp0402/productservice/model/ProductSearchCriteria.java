package com.septp0402.productservice.model;

import java.util.ArrayList;

public record ProductSearchCriteria(
        String searchTerm,
        ArrayList<String> categoryFilter,
        ArrayList<String> retailerFilter,
        Boolean discountFilter,
        String sortingFilter
) {}
