package com.example.demo.rest;

import com.example.demo.serializable.CustomerObject;
import com.example.demo.service.CustomerService;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by yusuf on 4/2/2018.
 *
 * This class has a single responsiblity: getting customer list (active or prospective)
 * This another way to handle api links. CustomerDetail is a seperate class and handles its own business.
 * This method is good if you have too many entry points regarding to a single service.
 *
 */
@RestController
public class CustomerDetail {

    private Logger LOGGER = LogManager.getLogger(CustomerDetail.class);

    @Autowired
    CustomerService customerService;

    @RequestMapping(value = "/customerDetail", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity listCustomers(@RequestBody CustomerObject customer) {

        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        CustomerObject customerObject = customerService.castCustomerToCustomerObject(
                customerService.getCurrentOrProspectCustomerById(customer.getId()));
        try {
            return ResponseEntity.status(HttpStatus.OK).body(mapper.writeValueAsString(customerObject));
        } catch (JsonProcessingException e) {
            LOGGER.error(e);
            // TODO: create a multi-language supported file under resources something like error_messages_en.properties
            // TODO Later: add more languages
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("This should be an error message from properties file");
        }
    }
}
