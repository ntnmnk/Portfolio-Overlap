package com.geektrust.backend.Entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import java.util.Set;
import org.springframework.stereotype.Component;

@Getter

@AllArgsConstructor
@RequiredArgsConstructor

public class Funds {
    private String name;
    private Set<String> stocks;
}
