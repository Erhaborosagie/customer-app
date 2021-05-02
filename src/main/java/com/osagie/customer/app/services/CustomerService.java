package com.osagie.customer.app.services;

import com.osagie.customer.app.model.Customer;
import org.springframework.dao.DataIntegrityViolationException;

import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Optional;

/**
 * Created by OSAGIE on 5/1/2021
 */
public interface CustomerService {

    Customer createCustomer(Customer customer) throws ConstraintViolationException, DataIntegrityViolationException;

    Customer getCustomer(String id);

    List<Customer> getAllCustomers();
}
