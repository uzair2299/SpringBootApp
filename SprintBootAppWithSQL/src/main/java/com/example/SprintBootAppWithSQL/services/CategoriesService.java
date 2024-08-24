package com.example.SprintBootAppWithSQL.services;

import com.example.SprintBootAppWithSQL.dto.CategoriesDto;
import com.example.SprintBootAppWithSQL.dto.MenuDto;
import com.example.SprintBootAppWithSQL.entities.Menu;
import com.example.SprintBootAppWithSQL.repository.CategoriesRepository;
import com.example.SprintBootAppWithSQL.repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class CategoriesService {
    @Autowired
    MenuRepository menuRepository;

    @Autowired
    CategoriesRepository categoriesRepository;

    public List<CategoriesDto> getCategoryHierarchy() {
        List<Object[]> resultList = categoriesRepository.getCategoryHierarchy();
        List<CategoriesDto> menuDtoList = new ArrayList<>();

        List<CategoriesDto> finalResult = new ArrayList<>();

        for (Object[] result : resultList) {
            CategoriesDto categoriesDto = new CategoriesDto();
            categoriesDto.setId((Long) result[0]);
            categoriesDto.setName((String) result[1]);
            //categoriesDto.setLevel((Integer) result[2]);
            categoriesDto.setParent_id((Integer) Objects.requireNonNullElse(result[2], 0));
            //categoriesDto.setLink((String) result[4]);
            //categoriesDto.setIcon((String) result[5]);
            menuDtoList.add(categoriesDto);
        }

        for (CategoriesDto menuDto : menuDtoList) {
            if (menuDto.getParent_id() == 0) {
                buildMenuTree(menuDto, menuDtoList);
                finalResult.add(menuDto);
            }
        }

        return finalResult;
    }

    private void buildMenuTree(CategoriesDto parentMenu,  List<CategoriesDto> menuDtoList) {
        List<CategoriesDto> children = new ArrayList<>();
        for (CategoriesDto menuDto : menuDtoList) {
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


}