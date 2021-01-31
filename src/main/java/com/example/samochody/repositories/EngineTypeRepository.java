package com.example.samochody.repositories;

import com.example.samochody.model.CarModel;
import com.example.samochody.model.EngineType;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EngineTypeRepository extends CrudRepository<EngineType, Long> {
    List<EngineType> getAllByCarModelsIsContaining(CarModel carModel);
}
