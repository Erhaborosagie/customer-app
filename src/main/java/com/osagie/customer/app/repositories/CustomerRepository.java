package com.osagie.customer.app.repositories;

import com.osagie.customer.app.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Created by OSAGIE on 5/1/2021
 */
public interface CustomerRepository extends JpaRepository<Customer,String> {
    Customer findCustomerByAccountNumber(String accountNumber);
}
