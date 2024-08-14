package com.example.SprintBootAppWithSQL.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Data
@Getter
@Setter
public class UserRoleDto {
    private Long userId;
    @JsonProperty("id")
    private long roleId;
    @JsonProperty("name")
    private String roleName;
    @JsonProperty("completed")
    private boolean isMarked;
}
