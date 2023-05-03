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
public class TaxYear {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int taxYear;
    private String changesToTaxLawsOrRates;
    private String other;
    @OneToMany(mappedBy = "taxYear")
    private List<TaxSlab> taxSlabs;
}
