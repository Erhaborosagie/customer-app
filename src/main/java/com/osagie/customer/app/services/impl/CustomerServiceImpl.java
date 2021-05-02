package com.osagie.customer.app.services.impl;

import com.osagie.customer.app.model.Customer;
import com.osagie.customer.app.repositories.CustomerRepository;
import com.osagie.customer.app.services.CustomerService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Optional;

/**
 * Created by OSAGIE on 5/2/2021
 */
@Service
public class CustomerServiceImpl implements CustomerService {
    private CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer createCustomer(Customer customer) throws ConstraintViolationException, DataIntegrityViolationException {
        try {
            return customerRepository.saveAndFlush(customer);
        }catch (ConstraintViolationException e){
            throw new ConstraintViolationException(e.getMessage(), e.getConstraintViolations());
        }catch (DataIntegrityViolationException e){
            throw new DataIntegrityViolationException(e.getLocalizedMessage());
        }
    }

    @Override
    public Customer getCustomer(String id) {
        return customerRepository.findCustomerByAccountNumber(id);
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }
}
