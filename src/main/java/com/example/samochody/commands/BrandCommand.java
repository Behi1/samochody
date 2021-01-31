package com.example.samochody.commands;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class BrandCommand {
    private Long id;
    private String brandName;
    private String brandLevel;
}
