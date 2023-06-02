package com.example.SprintBootAppWithSQL.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.UUID;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@NamedStoredProcedureQuery(
        name = "GetUserById",
        procedureName = "GetEmployeesByDepartment"
)
public class Role implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String roleName;
    private String description;
    private Date createdAt;
    private Date updatedAt;

    @JsonIgnore
    @ManyToMany(mappedBy = "roles")
    private List<User> users;
}
