package com.api.pokedex.model;

import lombok.Data;

@Data
public class Meta {
    private int pageNumber;
    private int pageSize;
    private int totalPage;
    private long totalElements;
}
