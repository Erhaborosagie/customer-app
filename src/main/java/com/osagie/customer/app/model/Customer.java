package com.osagie.customer.app.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by OSAGIE on 5/1/2021
 */
@Getter
@Setter
@Entity
@Table(name = "customers")
public class Customer {
    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull
    @Email
    @Column(unique = true)
    private String email;

    private LocalDateTime createdAt = LocalDateTime.now();

    @OneToMany
    private List<Bill> bills;

    @Id
    @GenericGenerator(name = "account_gen", strategy = "com.osagie.customer.app.helpers.AccountNumberGenerator")
    @GeneratedValue(generator = "account_gen")
    private String accountNumber;

}
