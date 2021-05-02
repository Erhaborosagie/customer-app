package com.osagie.customer.app.controllers;

import com.osagie.customer.app.model.Customer;
import com.osagie.customer.app.services.CustomerService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by OSAGIE on 5/1/2021
 */
@RestController
@RequestMapping("customers")
public class CustomerController {

    private CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public ResponseEntity<?> createCustomer(@RequestBody Customer customer, BindingResult bindingResult){

        try {
            return ResponseEntity.ok(customerService.createCustomer(customer));
        } catch ( ConstraintViolationException e) {

            List<String> collect = e.getConstraintViolations().stream()
                    .map(o -> String.format("%s %s", o.getPropertyPath(), o.getMessage())).collect(Collectors.toList());

            return ResponseEntity.badRequest().body(collect);
        }catch (DataIntegrityViolationException e){
            return ResponseEntity.status(409).body(String.format("%s exist already", customer.getEmail()));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCustomer(@PathVariable("id") String id) {
        Customer customer = customerService.getCustomer(id);
        if (customer == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(customer);
    }

    @GetMapping
    public ResponseEntity<?> getAllCustomers(){
        return ResponseEntity.ok(customerService.getAllCustomers());
    }
}
