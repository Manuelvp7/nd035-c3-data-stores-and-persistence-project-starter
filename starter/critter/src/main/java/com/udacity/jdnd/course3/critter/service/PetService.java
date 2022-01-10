package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.controller.model.CustomerDTO;
import com.udacity.jdnd.course3.critter.controller.model.PetDTO;
import com.udacity.jdnd.course3.critter.error.CustomerNotFound;
import com.udacity.jdnd.course3.critter.repository.CustomerRepository;
import com.udacity.jdnd.course3.critter.repository.PetRepository;
import com.udacity.jdnd.course3.critter.repository.model.Customer;
import com.udacity.jdnd.course3.critter.repository.model.Pet;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class PetService {

    private final PetRepository petRepository;
    private final CustomerRepository customerRepository;

    public PetService(PetRepository petRepository, CustomerRepository customerRepository) {
        this.petRepository = petRepository;
        this.customerRepository = customerRepository;
    }

    public List<PetDTO> getPets(){
        return petRepository.findAll().stream().map(pet -> pet.toPetDTO()).collect(Collectors.toList());
    }

    public PetDTO savePet(PetDTO petDTO) throws CustomerNotFound {

        Pet pet =  Pet.fromPetDTO(petDTO);
        customerRepository.findById(petDTO.getOwnerId()).ifPresentOrElse(
            customer -> pet.setCustomer(customer),
            () -> {throw new CustomerNotFound();});


        Pet petSaved = petRepository.save(pet);
        Customer customer = petSaved.getCustomer();

        customer.getPets().add(petSaved);
        customerRepository.save(customer);

        return petSaved.toPetDTO();

    }

    public PetDTO getPetById(long petId){
        return petRepository.findById(petId).get().toPetDTO();
    }

    public List<PetDTO> getPetByOwnerId(long ownerId){
        return petRepository.findByCustomerId(ownerId).stream().map(Pet::toPetDTO).collect(Collectors.toList());
    }

    public CustomerDTO getOwnerByPet(Long petId){
        Long id =  Optional.ofNullable(petRepository.findById(petId).get().getCustomer().getId())
            .orElseThrow(
                () -> new CustomerNotFound()
            );
        return customerRepository.findById(id).get().toCustomerDTO();

    }

}
