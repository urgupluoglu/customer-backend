package com.example.demo.service;

import com.example.demo.entity.Customer;
import com.example.demo.entity.Status;
import com.example.demo.repo.CustomerRepo;
import com.example.demo.serializable.CustomerObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by yusuf on 4/2/2018.
 *
 * Services can either return serializable objects
 * or entity so rest classes do the casting.
 *
 * This is an example of service always returns entity (Customer, in this case).
 * It is a good practice if you plan to use these services other parts of the code.
 *
 * There is another practice that encourages developers to use entities with json annotations.
 * In this case, you have one class both an entity and serializable. Only serializable fields are annotated.
 * Some think this method makes the code unreadable.
 *
 */
@Component
public class CustomerService {

    @Autowired
    CustomerRepo customerRepo;

    public List<Customer> getCustomerList() {
        return customerRepo.findAllByStatusIsNotOrderById(Status.NON_ACTIVE);
    }

    public Customer getCurrentOrProspectCustomerById(long id) {
        if (id == 0)
            return null;
        return customerRepo.findByIdAndStatusIsNot(id, Status.NON_ACTIVE);
    }

    public CustomerObject castCustomerToCustomerObject(Customer customer) {
        if (customer == null)
            return null;
        return new CustomerObject(customer.getId(), customer.getStatus().name(),
                new Date(customer.getCreationDate()), customer.getName(), customer.getAddress(), customer.getPhone());

    }

    public List<CustomerObject> castCustomerListToCustomerObjectList(List<Customer> customerList) {
        if (customerList == null)
            return null;

        List<CustomerObject> customerObjectList = new ArrayList<>();
        customerList.forEach(customer -> customerObjectList.add(castCustomerToCustomerObject(customer)));
        return customerObjectList;
    }
}
