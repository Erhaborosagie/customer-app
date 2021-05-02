package com.osagie.customer.app;

import com.osagie.customer.app.model.Customer;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by OSAGIE on 5/2/2021
 */
public class MockData {
    public static Customer mockCustomerResponse(){
        Customer customer = new Customer();

        customer.setAccountNumber("1000000001-0L");
        customer.setCreatedAt(LocalDateTime.now());
        customer.setEmail("ab@example.com");
        customer.setFirstName("ab");
        customer.setLastName("cd");

        return customer;
    }

    public static Customer invalidAccountNumberResponse() {
        return null;
    }

    public static Customer validAccountNumberResponse() {
        return new Customer();
    }

    public static List<Customer> allCustomerResponse() {
        List<Customer> customerList = new ArrayList<>();
        return customerList;
    }
}
