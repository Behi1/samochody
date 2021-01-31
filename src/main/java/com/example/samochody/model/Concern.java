package com.example.samochody.model;

import lombok.*;
import javax.persistence.*;

@Data
@EqualsAndHashCode(of = {"id"})
@Entity
public class Concern {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String year;
    private String address;

    public Concern() {

    }
    public Concern(String name) {
        this.name = name;
    }
    public Concern(String name, String year, String address) {
        this.name = name;
        this.year = year;
        this.address = address;
    }
}
