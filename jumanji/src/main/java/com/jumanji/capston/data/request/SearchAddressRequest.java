package com.jumanji.capston.data.request;

import lombok.Data;

@Data
public class SearchAddressRequest {
    private String currentPage;
    private String countPerPage;
    private String keyword;
}