package com.osagie.customer.app.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Created by OSAGIE on 5/1/2021
 */
@Getter
@Setter
@Entity
@Table(name = "bills")
public class Bill {
    @Id
    private Long id;
    @ManyToOne
    @JoinColumn(name = "accountNumber")
    private Customer customer;
    private Double tariff;
    private LocalDateTime createdAt = LocalDateTime.now();
}
