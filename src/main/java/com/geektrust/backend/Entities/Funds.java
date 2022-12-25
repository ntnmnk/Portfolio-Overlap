package com.geektrust.backend.Entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import java.util.Set;

@Getter
@AllArgsConstructor
@RequiredArgsConstructor

public class Funds {
    private String name;
    private Set<String> stocks;
}
