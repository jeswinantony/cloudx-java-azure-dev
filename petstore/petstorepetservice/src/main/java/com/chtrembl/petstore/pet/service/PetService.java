package com.chtrembl.petstore.pet.service;

import com.chtrembl.petstore.pet.model.DataPreload;
import com.chtrembl.petstore.pet.model.Pet;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class PetService {

//    private final DataPreload dataPreload;
    private final PetRepository petRepository;

    public List<Pet> findPetsByStatus(List<String> status) {
        log.info("Finding pets with status: {}", status);

        var statuses = status.stream().map(Pet.Status::valueOf).toList();
        return petRepository.findByStatusIn(statuses);
    }

    public Optional<Pet> findPetById(Long petId) {
        log.info("Finding pet with id: {}", petId);

        return petRepository.findById(petId);
    }

    public List<Pet> getAllPets() {
        log.info("Getting all pets");
        return petRepository.findAll();
    }

    public int getPetCount() {
        return (int) petRepository.count();
    }
}