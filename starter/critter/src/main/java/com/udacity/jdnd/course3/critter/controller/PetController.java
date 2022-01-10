package com.udacity.jdnd.course3.critter.controller;

import com.udacity.jdnd.course3.critter.controller.model.PetDTO;
import com.udacity.jdnd.course3.critter.service.PetService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Handles web requests related to Pets.
 */
@RestController
@RequestMapping("/pet")
public class PetController {

    private final PetService petService;

    public PetController(PetService petService) {
        this.petService = petService;
    }

    @PostMapping
    public PetDTO savePet(@RequestBody PetDTO petDTO) {
        return petService.savePet(petDTO);
    }

    @GetMapping("/{petId}")
    public PetDTO getPet(@PathVariable long petId) {
        return petService.getPetById(petId);
    }

    @GetMapping
    public List<PetDTO> getPets(){
        return petService.
    }

    @GetMapping("/owner/{ownerId}")
    public List<PetDTO> getPetsByOwner(@PathVariable long ownerId) {
        return petService.getPetByOwnerId(ownerId);
    }
}
