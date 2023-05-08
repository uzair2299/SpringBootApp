package com.example.SprintBootAppWithSQL.dto;

import com.example.SprintBootAppWithSQL.entities.Document;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DocumentTypeDto {

    private Long id;
    private String name;
    private String description;

    public DocumentTypeDto(String name, String description){
        this.name = name;
        this.description = description;
    }

}
