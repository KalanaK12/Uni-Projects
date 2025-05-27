package com.septp0402.productservice.model;

public record UserReview(
        int review_id,
        int c_id,
        int p_id,
        String review_det,
        double review_rat
) {}
