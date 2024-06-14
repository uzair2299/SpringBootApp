package com.example.SprintBootAppWithSQL.services;

import com.example.SprintBootAppWithSQL.dto.PermissionDto;
import com.example.SprintBootAppWithSQL.entities.Permission;
import com.example.SprintBootAppWithSQL.entities.User;
import com.example.SprintBootAppWithSQL.repository.PermissionRepository;
import com.example.SprintBootAppWithSQL.util.MapperUtil;
import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class PermissionService {
    @Autowired
    PermissionRepository permissionRepository;

    @Autowired
    private EntityManager entityManager;

    //    @Cacheable(value = "allDataCache")
    public List<PermissionDto> getAllNonActive() {
        log.info(String.format("Entering method in PermissionService.getAllPermissions()"));

        List<PermissionDto> permissionDtoList;
        List<Permission> permissionList = permissionRepository.getAllNonActive();
        permissionDtoList = MapperUtil.mapList(permissionList, PermissionDto.class);
        log.info(String.format("Leaving method in PermissionService.getAllPermissions()"));
        return permissionDtoList;
    }

    public List<PermissionDto> getAll() {
        log.info(String.format("Entering method in PermissionService.getAllPermissions()"));

        List<PermissionDto> permissionDtoList;
        List<Permission> permissionList = permissionRepository.getAll();
        permissionDtoList = MapperUtil.mapList(permissionList, PermissionDto.class);
        log.info(String.format("Leaving method in PermissionService.getAllPermissions()"));
        return permissionDtoList;
    }

    public List<PermissionDto> getAllActive() {
        log.info(String.format("Entering method in PermissionService.getAllPermissions()"));

        List<PermissionDto> permissionDtoList;
        List<Permission> permissionList = permissionRepository.getAllActive();
        permissionDtoList = MapperUtil.mapList(permissionList, PermissionDto.class);
        log.info(String.format("Leaving method in PermissionService.getAllPermissions()"));
        return permissionDtoList;
    }

//    public PermissionDto getPermissionById(int permissionId) {
//        Optional<Permission> permissionOptional = permissionRepository.findById(Long.valueOf(permissionId));
//        if (permissionOptional.isPresent()) {
//            Permission permission = permissionOptional.get();
//            PermissionDto permissionDto = MapperUtil.mapObject(permission, PermissionDto.class);
//            return permissionDto;
//        } else {
//            return null;
//        }
//    }

    public PermissionDto getPermissionById(int permissionId) {
       Permission permissionOptional = permissionRepository.getActivePermissionById(Long.valueOf(permissionId));
        if (permissionOptional!=null) {
            PermissionDto permissionDto = MapperUtil.mapObject(permissionOptional, PermissionDto.class);
            return permissionDto;
        } else {
            return null;
        }
    }

    public List<Permission> saveAll(List<Permission> role) {
        return permissionRepository.saveAll(role);
    }


    public PermissionDto updatePermissionIsDeleted(PermissionDto permissionDto){
        permissionDto.setDeleted(true);
        permissionDto.setUpdatedAt(System.currentTimeMillis());
        Permission permission = MapperUtil.mapObject(permissionDto, Permission.class);
        permission =  this.save(permission);
        PermissionDto mapObject = MapperUtil.mapObject(permission, PermissionDto.class);
        return mapObject;

    }
    public Permission save(Permission permission) {
        return permissionRepository.save(permission);
    }

    public PermissionDto save(PermissionDto permissionDto) {

        Permission permission = MapperUtil.mapObject(permissionDto, Permission.class);
        permission =  this.save(permission);
        permission =  permissionRepository.save(permission);
        PermissionDto mapObject = MapperUtil.mapObject(permission, PermissionDto.class);
        return mapObject;
    }

    public void hardDeletePermissionById(long permissionId){
        permissionRepository.hardDeletePermissionById(permissionId);
    }
}