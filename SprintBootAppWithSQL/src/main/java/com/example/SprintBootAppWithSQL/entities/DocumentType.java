package com.example.SprintBootAppWithSQL.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Entity
public class DocumentType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description",length = 500)
    private String description;

    @JsonIgnore
    @OneToMany(mappedBy = "type")
    private List<Document> documents;

    public DocumentType(String name,String description){
        this.name = name;
        this.description = description;
    }

}
