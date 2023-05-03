package com.example.SprintBootAppWithSQL.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class TaxSlab {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(columnDefinition = "decimal")
    private BigDecimal minimumRange;
    @Column(columnDefinition = "decimal")
    private BigDecimal maximumRange;
    private double taxRate;
    private String other;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "taxYearId", nullable = false)
    private TaxYear taxYear;
}
