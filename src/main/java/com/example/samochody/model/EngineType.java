package com.example.samochody.model;

import lombok.*;

import javax.persistence.*;
import java.util.*;

@Data
@EqualsAndHashCode
@Entity
public class EngineType {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Long id;
    private String engineName;
    private String capacity;
    private String fuelType;

    @ManyToMany
    private Set<CarModel> carModels = new HashSet<>();

    public EngineType() {
    }
    public EngineType(String engineName, String capacity, String fuelType) {
        this.engineName = engineName;
        this.capacity = capacity;
        this.fuelType = fuelType;
    }
}
