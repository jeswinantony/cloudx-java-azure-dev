package com.chtrembl.petstore.pet.service;

import com.chtrembl.petstore.pet.model.Pet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

interface PetRepository extends JpaRepository<Pet, Long> {
    List<Pet> findByStatusIn(List<Pet.Status> statuses);


}
