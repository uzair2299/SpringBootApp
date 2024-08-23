package com.example.SprintBootAppWithSQL.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PaginatedResponse<T> {
    List<T> items;
    private Long totalItems;
    private int pageIndex;
    private int pageSize;
}
