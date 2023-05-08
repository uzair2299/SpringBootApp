package com.example.SprintBootAppWithSQL.entities;

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
public class Designation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private String department;
    private Double minSalary;
    private Double maxSalary;
    private Integer minExperience;
    private Integer maxExperience;
    private String minEducationLevel;
    //private List<String> requiredQualifications;
    //private List<String> requiredCertifications;
    //private List<DesignationDTO> reportsTo;
    public Designation(String name){
        this.name = name;
    }

    @OneToMany(mappedBy = "designation")
    private List<Employee> employees;

}
