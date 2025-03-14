package org.example.ecommerce_be.response;

import java.util.List;

public class ProductListResponse {

    int pageSize;
    int total;
    int currentPage;
    List<ParentProductResponse> products;
}
