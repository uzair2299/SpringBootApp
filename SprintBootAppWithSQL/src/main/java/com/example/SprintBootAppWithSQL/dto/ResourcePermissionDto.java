package com.example.SprintBootAppWithSQL.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;


//When you use @JsonInclude(JsonInclude.Include.NON_NULL) on a class or property, it tells Jackson to skip any properties that have null values during serialization. This can help reduce the size of the JSON output and avoid sending unnecessary null values in the API responses.
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResourcePermissionDto {

    private Long resource_Id;
    @JsonProperty("id")
    private long permission_Id;
    @JsonProperty("name")
    private String permissionName;
    @JsonProperty("completed")
    private boolean isMarked;
}
