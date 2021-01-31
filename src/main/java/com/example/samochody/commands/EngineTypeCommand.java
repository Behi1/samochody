package com.example.samochody.commands;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class EngineTypeCommand {
    private Long id;
    private String engineName;
    private String capacity;
    private String fuelType;
    private Long carModelId;
}
