package com.example.SprintBootAppWithSQL.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class FileUploadDto {
    private long id;

    private MultipartFile file;
}
