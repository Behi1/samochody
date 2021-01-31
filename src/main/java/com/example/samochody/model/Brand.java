package com.example.samochody.model;

import lombok.*;
import javax.persistence.*;
import java.util.*;

@Data
@EqualsAndHashCode(of = {"id"})
@Entity
public class Brand {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Long id;
    private String brandName;
    private String brandLevel;

    @ManyToMany(mappedBy = "brands")
    private Set<CarModel> carModels = new HashSet<>();

    public Brand() {
    }
    public Brand(String brandName, String brandLevel) {
        this.brandName = brandName;
        this.brandLevel = brandLevel;
    }
}
