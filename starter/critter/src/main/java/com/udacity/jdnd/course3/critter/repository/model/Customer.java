package com.udacity.jdnd.course3.critter.repository.model;

import com.udacity.jdnd.course3.critter.controller.model.CustomerDTO;
import org.hibernate.annotations.Nationalized;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Nationalized
    private String name;
    private String phoneNumber;
    @Column(length = 500)
    private String notes;
    @OneToMany(mappedBy = "customer", fetch = FetchType.EAGER)
    private List<Pet> pets;

    public Customer() {
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getNotes() {
        return notes;
    }

    public List<Pet> getPets() {
        return pets;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public void setPets(List<Pet> pets) {
        this.pets = pets;
    }

    public static Customer fromCustomerDTO(CustomerDTO customerDTO){


        Customer customer = new Customer();
        customer.setName(customerDTO.getName());
        customer.setNotes(customerDTO.getNotes());
        customer.setPhoneNumber(customerDTO.getPhoneNumber());


        return customer;

    }

    public CustomerDTO toCustomerDTO(){

        List<Pet> pets = Optional.ofNullable(this.getPets()).orElse(Collections.EMPTY_LIST);
        List<Long> petIds =  pets.stream().map(pet -> pet.getId()).collect(Collectors.toList());



        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setId(this.getId());
        customerDTO.setName(this.getName());
        customerDTO.setPhoneNumber(this.getPhoneNumber());
        customerDTO.setNotes(this.getNotes());
        customerDTO.setPetIds(petIds);

        return customerDTO;

    }
}