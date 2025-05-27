package com.septp0402.productservice.model;

public record Product(
        int p_id,
        int p_retailer_id,
        String p_retailer_name,
        int p_cat_id,
        String p_cat_name,
        int p_sub_cat,
        String p_sub_cat_name,
        Double p_price,
        Double p_org_price,
        String p_name,
        String p_desc,
        Double p_weight,
        String p_img
) {}
