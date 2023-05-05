package com.example.SprintBootAppWithSQL.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String link;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id", foreignKey = @ForeignKey(name = "fk_parent_menu_id"))
    private Menu parentMenu;
    private int status;

    public Menu(Long id, String name, String link, int status) {
        this.id = id;
        this.name = name;
        this.link = link;
        this.status = status;
    }
}
