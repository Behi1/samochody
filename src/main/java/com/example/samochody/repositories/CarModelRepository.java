package com.example.samochody.repositories;

import com.example.samochody.model.Brand;
import com.example.samochody.model.CarModel;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CarModelRepository extends CrudRepository<CarModel, Long> {
    List<CarModel> getAllByBrandsIsContaining(Brand brand);
}
