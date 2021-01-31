package com.example.samochody.model;

import lombok.*;

import javax.persistence.*;
import java.util.*;

@Data
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"})
@Entity
public class CarModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Long id;
    private String modelName;
    private String yearProduced;
    private String generation;

    @ManyToOne
    private Concern concern;

    @ManyToMany
    private Set<Brand> brands = new HashSet<>();

    @ManyToMany(mappedBy = "carModels")
    private Set<EngineType> engineTypes = new HashSet<>();

    public CarModel(String modelName, String yearProduced, String generation, Concern concern) {
        this.modelName = modelName;
        this.yearProduced = yearProduced;
        this.generation = generation;
        this.concern = concern;
    }

}
