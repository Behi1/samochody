package com.example.samochody.commands;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class CarModelCommand {
    private Long id;
    private String modelName;
    private String yearProduced;
    private String generation;
    private Long concernId;
    private Long brandId;
}
