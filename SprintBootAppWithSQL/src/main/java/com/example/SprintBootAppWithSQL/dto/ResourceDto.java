package com.example.SprintBootAppWithSQL.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;


//When you use @JsonInclude(JsonInclude.Include.NON_NULL) on a class or property, it tells Jackson to skip any properties that have null values during serialization. This can help reduce the size of the JSON output and avoid sending unnecessary null values in the API responses.
@Data
public class ResourceDto {

    private Long resourceId;

    private String resourceName;

    private String resourceEndpoint;

    private String version;

    private boolean isActive;

    private String methodType;  // GET, POST, PUT, DELETE, etc.

    private String description;

    private boolean isAuthRequired;

    private Integer rateLimit;

    private boolean isDeprecated;

    private String documentationUrl;

    private String owner;

    private List<PermissionDto> permissions = new ArrayList<>();
}
