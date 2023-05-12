package com.example.SprintBootAppWithSQL.dto;

import com.example.SprintBootAppWithSQL.entities.Employee;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
public class DesignationDto {
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
//    public DesignationDto(String name){
//        this.name = name;
//    }

}
