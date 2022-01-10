package com.udacity.jdnd.course3.critter.repository.model;

import com.udacity.jdnd.course3.critter.controller.model.PetDTO;
import org.hibernate.annotations.Nationalized;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import java.time.LocalDate;


@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Enumerated(EnumType.STRING)
    private PetType type;
    @Nationalized
    private String name;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Customer customer;
    @Column(name = "birth_date", nullable = true)
    private LocalDate birthDate;
    @Column(length = 500)
    private String notes;

    public Pet() {
    }

    public Pet(Long id, PetType type, String name, Customer customer, LocalDate birthDate, String notes) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.customer = customer;
        this.birthDate = birthDate;
        this.notes = notes;
    }

    public Long getId() {
        return id;
    }

    public PetType getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public Customer getCustomer() {
        return customer;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public String getNotes() {
        return notes;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setType(PetType type) {
        this.type = type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public static Pet fromPetDTO(PetDTO petDTO){

        Pet pet = new Pet();
        pet.setBirthDate(petDTO.getBirthDate());
        pet.setNotes(petDTO.getNotes());
        pet.setType(petDTO.getType());
        pet.setName(petDTO.getName());

        return pet;
    }

    public PetDTO toPetDTO(){
        PetDTO petDTO = new PetDTO();
        petDTO.setId(this.getId());
        petDTO.setName(this.getName());
        petDTO.setNotes(this.getNotes());
        petDTO.setType(this.getType());
        petDTO.setBirthDate(this.getBirthDate());
        petDTO.setOwnerId(this.getCustomer().getId());

        return petDTO;
    }
}