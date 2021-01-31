package com.example.samochody.repositories;

import com.example.samochody.model.Concern;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ConcernRepository extends CrudRepository<Concern, Long> {
    Optional<Concern> getConcernByName(String name);
}
