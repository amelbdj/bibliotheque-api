package com.example.bibliotheque_api.mapper;

import com.example.bibliotheque_api.models.Livre;
import org.mapstruct.Mapper;

import com.example.bibliotheque_api.dto.LivreRequestDto;
import com.example.bibliotheque_api.dto.LivreResponseDto;


@Mapper(componentModel = "spring")
public interface LivreMapper {

    LivreResponseDto toDto(Livre livre);

    Livre toEntity(LivreRequestDto dto);
}

