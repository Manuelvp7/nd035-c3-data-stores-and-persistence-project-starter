package com.udacity.jdnd.course3.critter.repository;

import com.udacity.jdnd.course3.critter.repository.model.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PetRepository extends JpaRepository<Pet, Long> {

    public List<Pet> findByCustomerId(long id);

}
