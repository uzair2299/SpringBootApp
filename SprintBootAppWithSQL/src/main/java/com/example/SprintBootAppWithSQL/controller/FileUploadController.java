package com.example.SprintBootAppWithSQL.controller;

import com.example.SprintBootAppWithSQL.dto.FileUploadDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
public class FileUploadController {
    @Autowired
    private ResourceLoader resourceLoader;
    @PostMapping("/api/v1/uploadFile/")
    public String handleFileUpload(@ModelAttribute FileUploadDto fileUploadDTO) throws IOException {
        //Resource folderResource = resourceLoader.getResource("classpath:uploads/");

        //Path filePath = Paths.get(folderResource.getURI()).resolve(fileUploadDTO.getFile().getName());
        //String storagePath = folderResource.getFile().getAbsolutePath();


        MultipartFile file = fileUploadDTO.getFile();

        if (!file.isEmpty()) {
            String originalFilename = file.getOriginalFilename();
            String contentType = file.getContentType();
            byte[] fileBytes = file.getBytes();
            Path targetPath = Paths.get("F:/SpringBoot/SprintBootAppWithSQL/SprintBootAppWithSQL/src/main/resources/uploads/" + originalFilename);
            //File targetFile = new File(, file.getName());
            Files.write(targetPath, fileBytes);
//            file.transferTo(targetFile);
            // Save the file
        }
        // Access other fields if needed, e.g., fileUploadDTO.getUserId()

        // Process the file and other fields here
        // ...


        String folderPath = "/path/to/folder"; // Specify the folder path

//        File folder = new File(folderPath);
//        if (!folder.exists()) {
//            boolean folderCreated = folder.mkdirs();
//            if (folderCreated) {
//                System.out.println("Folder created successfully.");
//            } else {
//                System.out.println("Failed to create the folder.");
//            }
//        } else {
//            System.out.println("Folder already exists.");
//        }

        return "File uploaded successfully";
    }
}
