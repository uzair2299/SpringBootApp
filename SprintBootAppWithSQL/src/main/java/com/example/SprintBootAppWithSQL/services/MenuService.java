package com.example.SprintBootAppWithSQL.services;

import com.example.SprintBootAppWithSQL.dto.MenuDto;
import com.example.SprintBootAppWithSQL.dto.RoleDto;
import com.example.SprintBootAppWithSQL.entities.Menu;
import com.example.SprintBootAppWithSQL.entities.Role;
import com.example.SprintBootAppWithSQL.repository.MenuRepository;
import com.example.SprintBootAppWithSQL.repository.RoleRepository;
import com.example.SprintBootAppWithSQL.util.MapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class MenuService {
    @Autowired
    MenuRepository menuRepository;

    public List<MenuDto> getUserMenu() {
        List<Object[]> resultList = menuRepository.getMenuHierarchy();
        List<MenuDto> menuDtoList = new ArrayList<>();

        List<MenuDto> finalResult = new ArrayList<>();

        for (Object[] result : resultList) {
            MenuDto menuDto = new MenuDto();
            menuDto.setId((Long) result[0]);
            menuDto.setName((String) result[1]);
            menuDto.setLevel((Integer) result[2]);
            menuDto.setParent_id((Integer) Objects.requireNonNullElse(result[2], 0));
            menuDto.setLink((String) result[4]);
            menuDtoList.add(menuDto);
        }

        for (MenuDto menuDto : menuDtoList) {
            if (menuDto.getParent_id() == 0) {
                buildMenuTree(menuDto, menuDtoList);
                finalResult.add(menuDto);
            }
        }

        return finalResult;
    }

    private void buildMenuTree(MenuDto parentMenu,  List<MenuDto> menuDtoList) {
        List<MenuDto> children = new ArrayList<>();
        for (MenuDto menuDto : menuDtoList) {
            if (menuDto.getParent_id() != 0 && menuDto.getParent_id() == (parentMenu.getId())) {
                buildMenuTree(menuDto, menuDtoList);
                children.add(menuDto);
            }
        }
        parentMenu.setChildren(children);
    }
    public Menu saveMenu(Menu menu) {
        return menuRepository.save(menu);
    }

    public List<Menu> saveAllMenuItem(List<Menu> menuList) {
        return menuRepository.saveAll(menuList);
    }

//
//    public List<Role> getAllRoles_() {
//        List<Role> roleList = roleRepository.findAll();
//        return roleList;
//    }
//


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