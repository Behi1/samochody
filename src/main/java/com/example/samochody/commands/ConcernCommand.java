package com.example.samochody.commands;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class ConcernCommand {
    private Long id;
    private String name;
    private String year;
    private String address;
}
