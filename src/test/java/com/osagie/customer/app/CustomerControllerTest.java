package com.osagie.customer.app;

import com.osagie.customer.app.controllers.CustomerController;
import com.osagie.customer.app.model.Customer;
import com.osagie.customer.app.services.CustomerService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

/**
 * Created by OSAGIE on 5/2/2021
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CustomerControllerTest {
    MockMvc mockMvc;
    @Autowired
    private WebApplicationContext context;
    @Mock
    private CustomerController customerController;

    @Autowired
    private TestRestTemplate template;

    @MockBean
    private CustomerService customerService;

    private Customer customer = new Customer();

    private final String validAccountNumber = "100000001-0L";
    private final String invalidAccountNumber = "22222222";
    private String firstName = "ab";
    private String lastname = "cd";
    private String validEmail = "ab@cd.com";

    @Before
    public void setUp() {
        when(customerService.createCustomer(customer)).thenReturn(MockData.mockCustomerResponse());
        when(customerService.getCustomer(invalidAccountNumber)).thenReturn(MockData.invalidAccountNumberResponse());
        when(customerService.getCustomer(validAccountNumber)).thenReturn(MockData.validAccountNumberResponse());
        when(customerService.getAllCustomers()).thenReturn(MockData.allCustomerResponse());

        mockMvc = MockMvcBuilders
                .webAppContextSetup(context).build();
    }

    @Test
    public void validAccountNumberTest(){
        HttpEntity<Object> entityRequest = getHttpEntity(
                null);
        ResponseEntity<?> response =
                template.exchange("/customers/" + validAccountNumber, HttpMethod.GET,
                        new HttpEntity<>(entityRequest.getHeaders()), Customer.class);
        Assert.assertEquals(200,response.getStatusCode().value());
    }

    @Test
    public void createCustomerEmptyDataTest(){
        HttpEntity<Object> entityRequest = getHttpEntity(customer);
        ResponseEntity<?> response =
                template.exchange("/customers" , HttpMethod.POST,
                        new HttpEntity<>(entityRequest.getHeaders()), Customer.class);
        Assert.assertEquals(400,response.getStatusCode().value());
    }

    @Test
    public void createCustomerValidDataTest(){
        customer.setLastName(lastname);
        customer.setFirstName(firstName);
        customer.setEmail(validEmail);
        HttpEntity<Object> entityRequest = getHttpEntity(customer);
        ResponseEntity<?> response =
                template.exchange("/customers" , HttpMethod.POST,
                        entityRequest, Customer.class);
        Assert.assertEquals(200,response.getStatusCode().value());
    }

    @Test
    public void allCustomerTest(){
        HttpEntity<Object> entityRequest = getHttpEntity(
                null);
        ResponseEntity<?> response =
                template.exchange("/customers" , HttpMethod.GET,
                        new HttpEntity<>(entityRequest.getHeaders()), Customer.class);
        Assert.assertEquals(200,response.getStatusCode().value());
    }

    @Test
    public void invalidAccountNumberTest(){
        HttpEntity<Object> entityRequest = getHttpEntity(
                null);
        ResponseEntity<?> response =
                template.exchange("/customers/" + invalidAccountNumber, HttpMethod.GET,
                        new HttpEntity<>(entityRequest.getHeaders()), Customer.class);
        Assert.assertEquals(404,response.getStatusCode().value());
    }

    private HttpEntity<Object> getHttpEntity(Customer customer) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new HttpEntity<>(customer, headers);
    }
}
