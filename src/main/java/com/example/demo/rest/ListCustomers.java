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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by yusuf on 4/2/2018.
 *
 */
@RestController
public class ListCustomers {

    private Logger LOGGER = LogManager.getLogger(ListCustomers.class);

    @Autowired
    CustomerService customerService;

    @RequestMapping(value = "/customerList", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity listCustomers() {

        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        List<CustomerObject> customerObjectList = customerService.castCustomerListToCustomerObjectList(customerService.getCustomerList());
        try {
            return ResponseEntity.status(HttpStatus.OK).body(mapper.writeValueAsString(customerObjectList));
        } catch (JsonProcessingException e) {
            LOGGER.error(e);
            // TODO: create a multi-language supported file under resources something like error_messages_en.properties
            // TODO Later: add more languages
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("This should be an error message from properties file");
        }
    }
}
