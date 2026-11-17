package com.example.bibliotheque_api.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record LivreRequestDto(

    @NotBlank(message = "titre est obligatoire")
    @Size(min = 2, max = 100)
    String title,

    @NotBlank(message = "auteur est obligatoire")
    String author,

    @DecimalMin(value = "0.1")
    double price

){}

