package com.example.demo.repo;

import com.example.demo.entity.Customer;
import com.example.demo.entity.Status;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by yusuf on 4/2/2018.
 */
public interface CustomerRepo extends CrudRepository<Customer, Long> {

    Customer findByIdAndStatusIsNot(Long id, Status status);
    List<Customer> findAllByStatusIsNotOrderById(Status status);
}
