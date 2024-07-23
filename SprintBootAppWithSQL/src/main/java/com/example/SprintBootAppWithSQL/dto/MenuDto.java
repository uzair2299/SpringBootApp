package com.example.SprintBootAppWithSQL.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter

public class MenuDto {
    private Long id;
    private String name;
    private String link;
    private int parent_id;
    private int level;
    private String icon;
    private List<MenuDto> children;
}
