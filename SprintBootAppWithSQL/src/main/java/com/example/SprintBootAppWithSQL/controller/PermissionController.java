package com.example.SprintBootAppWithSQL.controller;

import com.example.SprintBootAppWithSQL.dto.PermissionDto;
import com.example.SprintBootAppWithSQL.services.PermissionService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api/v1/permission")
public class PermissionController {
    Logger logger = LoggerFactory.getLogger(PermissionController.class);
    @Autowired
    PermissionService permissionService;


    @GetMapping("/getAllNonActive")
    public ResponseEntity<List<PermissionDto>> getAllNonActive() {
        try {

            logger.info(String.format("Executing getRoles request"));
            List<PermissionDto> permissionDtoList = permissionService.getAllNonActive();
            if (permissionDtoList.isEmpty()) {
                logger.info(String.format("Leaving getRoles request - roles list is empty"));
                //When you use ResponseEntity.noContent().build(), it creates a ResponseEntity with the HTTP status code 204 No Content and no body.
                return ResponseEntity.noContent().build();
            }
            logger.info(String.format("Leaving getRoles request - with roles list"));
            return ResponseEntity.ok().body(permissionDtoList);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/getAllActive")
    public ResponseEntity<List<PermissionDto>> getAllActivePermissions() {
        try {

            logger.info(String.format("Executing getRoles request"));
            List<PermissionDto> permissionDtoList = permissionService.getAllActive();
            if (permissionDtoList.isEmpty()) {
                logger.info(String.format("Leaving getRoles request - roles list is empty"));
                return ResponseEntity.noContent().build();
            }
            logger.info(String.format("Leaving getRoles request - with roles list"));
            return ResponseEntity.ok().body(permissionDtoList);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<PermissionDto>> getAll() {
        try {

            logger.info(String.format("Executing getRoles request"));
            List<PermissionDto> permissionDtoList = permissionService.getAll();
            if (permissionDtoList.isEmpty()) {
                logger.info(String.format("Leaving getRoles request - roles list is empty"));
                return ResponseEntity.noContent().build();
            }
            logger.info(String.format("Leaving getRoles request - with roles list"));
            return ResponseEntity.ok().body(permissionDtoList);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }


    @GetMapping("/{id}")
    public ResponseEntity<PermissionDto> getPermission(@PathVariable("id") int permissionId) {
        log.info("permission id receive - {}", permissionId);
        PermissionDto permissionDto = permissionService.getPermissionById(permissionId);

        return ResponseEntity.ok().body(permissionDto);
    }


    @RequestMapping(value = "/CreateNewPermission", method = RequestMethod.POST)
    public ResponseEntity<PermissionDto> createNewPermission(@RequestBody PermissionDto permissionDto) {
        try {
            logger.info(String.format("permissionDto - " + permissionDto));
            permissionDto.setCreatedAt(System.currentTimeMillis());
            PermissionDto permissionObj = permissionService.save(permissionDto);
            return new ResponseEntity<>(permissionObj, HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @RequestMapping(value = "/updatePermission/{id}", method = RequestMethod.PUT)
    public ResponseEntity<String> updatePermission(@PathVariable Long id, @RequestBody PermissionDto permissionDto) {
      permissionService.updatePermission(id,permissionDto);

//write logic for update
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<String> softDeletePermission(@PathVariable("id") int permissionId) {
        try {
            logger.info("permission id receive - {}", permissionId);
            PermissionDto permissionDto = permissionService.getPermissionById(permissionId);
            if (permissionDto != null) {
                permissionService.updatePermissionIsDeleted(permissionDto);
                return ResponseEntity.ok().build();
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(String.format("Resource with ID %s not found", permissionId));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @RequestMapping(value = "HardDeletePermission/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> DeletePermission(@PathVariable("id") long permissionId) {
        try {
            logger.info("permission id receive - {}", permissionId);
            permissionService.hardDeletePermissionById(permissionId);
            return ResponseEntity.ok().body(String.format("Resource with ID %s delete successfully", permissionId));


        }
        catch (Exception e) {
            log.error("Going to log exception [{}]",e);
            return ResponseEntity.internalServerError().build();
        }
    }
}
