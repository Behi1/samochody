package com.example.samochody.repositories;

import com.example.samochody.model.Brand;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface BrandRepository extends CrudRepository<Brand, Long> {
    Optional<Brand> getFirstByBrandName(String brandName);
}
