package com.example.SprintBootAppWithSQL.services;

import com.example.SprintBootAppWithSQL.dto.PermissionDto;
import com.example.SprintBootAppWithSQL.dto.ResourceDto;
import com.example.SprintBootAppWithSQL.dto.ResourcePermissionDto;
import com.example.SprintBootAppWithSQL.entities.Resource;
import com.example.SprintBootAppWithSQL.repository.ResourcesRepository;
import com.example.SprintBootAppWithSQL.util.MapperUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Slf4j
public class ResourcesService {
    @Autowired
    ResourcesRepository resourcesRepository;
    @Autowired
    PermissionService permissionService;

    public List<ResourceDto> getAll(){
        List<Object[]> resources = resourcesRepository.getAll();
        List<ResourceDto> permissionDtoList = new ArrayList<>();
        for(Object[] object : resources) {
            ResourceDto resourceDto = new ResourceDto();
            resourceDto.setResourceId(Long.valueOf((Integer) object[0]));
            resourceDto.setResourceName((String) object[1]);
            resourceDto.setResourceEndpoint((String) object[2]);
            resourceDto.setVersion((String) object[3]);
            //isactive
            resourceDto.setMethodType((String) object[5]);
            permissionDtoList.add(resourceDto);
        }

        log.info(String.format("Leaving method in PermissionService.getAllPermissions()"));
        return permissionDtoList;
    }


    public Map<Long, ResourceDto> getResourceByIdWithPermissions(long resourceId) {
        List<Object[]> list  =  resourcesRepository.getResourceByIdWithPermissions(resourceId);

        List<PermissionDto> permissionDtoList = permissionService.getAllActive();

        Map<Long, ResourceDto> resourceMap = new HashMap<>();
        for (Object[] object : list) {
            ResourceDto resourceDto = new ResourceDto();
            PermissionDto permissionDto = new PermissionDto();
            resourceDto.setResourceId(Long.valueOf((Integer) object[0]));
            resourceDto.setResourceEndpoint((String) object[1]);
            resourceDto.setVersion((String) object[2]);
            resourceDto.setMethodType((String) object[3]);

            ResourceDto resource = resourceMap.getOrDefault(resourceDto.getResourceId(), resourceDto);

            for(PermissionDto item : permissionDtoList){

                if (object.length > 2 && object[4] != null) {
                    if(item.getId().equals(Long.valueOf(String.valueOf(object[4])))){
                        item.setChecked(true);
                    }
                }
            }
            resourceDto.setPermissions(permissionDtoList);
//            if (object.length > 2 && object[4] != null) {
//                permissionDto.setId(Long.valueOf(String.valueOf(object[4])));
//            }
//
//            if (object.length > 3 && object[5] != null) {
//                permissionDto.setPermissionName(String.valueOf(object[5]));
//            }

            resourceMap.put(resourceDto.getResourceId(), resource);
        }
        return resourceMap;

    }

    public void deleteResourcePermissionById(long resourceId){
        resourcesRepository.deleteResourcePermissionById(resourceId);
    }

    @Transactional
    public void assignResourcePermission(Long resourceId ,List<ResourcePermissionDto> resourcePermissionDto){
        deleteResourcePermissionById(resourceId);
        for(ResourcePermissionDto item : resourcePermissionDto){
            if(item.isMarked()){
                resourcesRepository.assignResourcePermission(item.getPermission_Id(),resourceId);
            }
        }
    }

}