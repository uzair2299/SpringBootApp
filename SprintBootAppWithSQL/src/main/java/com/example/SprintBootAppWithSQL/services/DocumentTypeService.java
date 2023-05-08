package com.example.SprintBootAppWithSQL.services;

import com.example.SprintBootAppWithSQL.dto.DocumentTypeDto;
import com.example.SprintBootAppWithSQL.dto.RoleDto;
import com.example.SprintBootAppWithSQL.entities.Department;
import com.example.SprintBootAppWithSQL.entities.Document;
import com.example.SprintBootAppWithSQL.entities.DocumentType;
import com.example.SprintBootAppWithSQL.repository.DocumentTypeRepository;
import com.example.SprintBootAppWithSQL.util.MapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DocumentTypeService {
    @Autowired
    DocumentTypeRepository documentRepository;

//    public List<Department> getAllRoles() {
//        List<Menu> menuList = departmentRepository.findAll();
//        return menuList;
//    }

//    public Menu saveMenu(Menu menu) {
//        return menuRepository.save(menu);
//    }

    public List<DocumentType> saveAllDocumentType(List<DocumentType> documents) {
        return documentRepository.saveAll(documents);
    }


    public List<DocumentTypeDto> GetAllDocumentTypes() {
        List<DocumentType> documentTypes = documentRepository.findAll();
        List<DocumentTypeDto> documentTypeDtoList = MapperUtil.mapList(documentTypes, DocumentTypeDto.class);
        return documentTypeDtoList;
    }



//    public RoleDto createRole(RoleDto roleDto) {
//        Role role =  MapperUtil.mapObject(roleDto,Role.class);
//        Role savedRole = roleRepository.save(role);
//        RoleDto savedRoleDto = MapperUtil.mapObject(savedRole, RoleDto.class);
//        return savedRoleDto;
//    }
//
//    public List<Role> saveAll(List<Role> role) {
//        return roleRepository.saveAll(role);
//    }
//
//    public Long getRolesCount() {
//        return roleRepository.count();
//    }
//
//    public List<Role> getEntitiesByIds(List<Long> ids) {
//        List<Role> roles = roleRepository.findByIdIn(ids);
//        return roles;
//    }

}