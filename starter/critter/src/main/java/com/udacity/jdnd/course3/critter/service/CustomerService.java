package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.controller.model.CustomerDTO;
import com.udacity.jdnd.course3.critter.repository.CustomerRepository;
import com.udacity.jdnd.course3.critter.repository.PetRepository;
import com.udacity.jdnd.course3.critter.repository.model.Customer;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final PetRepository petRepository;

    public CustomerService(CustomerRepository customerRepository, PetRepository petRepository) {
        this.customerRepository = customerRepository;
        this.petRepository = petRepository;
    }

    public Customer saveCustomer(CustomerDTO customerDTO){

        Customer customer = Customer.fromCustomerDTO(customerDTO);
        customer.setPets(new ArrayList<>());
        return customerRepository.save(customer);
    }

    public List<CustomerDTO> getAllCustomers(){
        List<Customer> customers =  customerRepository.findAll();
        return customers.stream()
            .map( customer -> customer.toCustomerDTO() )
            .collect(Collectors.toList());
    }


}
