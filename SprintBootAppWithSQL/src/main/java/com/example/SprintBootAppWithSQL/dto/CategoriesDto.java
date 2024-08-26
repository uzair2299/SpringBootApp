package com.example.SprintBootAppWithSQL.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter

public class CategoriesDto {
    private Long id;
    private String name;
    private Long parent_id;
    private String description;
    private List<CategoriesDto> children;
}
