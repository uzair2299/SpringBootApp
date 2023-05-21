package com.example.SprintBootAppWithSQL.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate orderDate;
    private BigDecimal totalAmount;
    private String status;
    private String shippingAddress;

    @ManyToOne
    @JoinColumn(name = "distributor_id")
    private Distributor distributor;

    @OneToMany(mappedBy = "order")
    private List<OrderDetail> orderDetail;

    @OneToMany(mappedBy = "order")
    private List<PaymentDetails> payments;

}
