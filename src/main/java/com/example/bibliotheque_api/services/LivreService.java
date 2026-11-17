package com.example.bibliotheque_api.services;
import com.example.bibliotheque_api.dto.LivreRequestDto;
import com.example.bibliotheque_api.dto.LivreResponseDto;
import com.example.bibliotheque_api.mapper.LivreMapper;
import com.example.bibliotheque_api.models.Livre;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.bibliotheque_api.repository.LivreRepository;


@Service
public class LivreService {

    private final LivreRepository livreRepository;
    private final LivreMapper livreMapper;

    public LivreService(LivreRepository livreRepository, LivreMapper livreMapper) {
        this.livreRepository = livreRepository;
        this.livreMapper = livreMapper;
    }

    public List<Livre> getAllLivres() {
        return livreRepository.findAll();
    }
 public List<LivreResponseDto> getAllLivresResponse() {

        return getAllLivres().stream()
                .map(livre -> livreMapper.toDto(livre))
                .collect(Collectors.toList());
    }


 public Livre getLivreById(Long id) {
    return livreRepository.findById(id)
        .orElseThrow(() -> new ResponseStatusException(
            HttpStatus.NOT_FOUND,
            "Livre introuvable"
        ));
}

public LivreResponseDto getLivreResponseById(Long id) {
    Livre livre = getLivreById(id);

    return livreMapper.toDto(livre);
}

public Livre createLivre(Livre livre){
    return livreRepository.save(livre);
}

public LivreResponseDto updateLivre(Long id, LivreRequestDto livre) {

    Livre existingLivre = getLivreById(id);

    existingLivre.setAuthor(livre.author());
    existingLivre.setPrice(livre.price());
    existingLivre.setTitle(livre.title());

    return livreMapper.toDto(livreRepository.save(existingLivre));

}

public void deleteLivre(Long id) {

    if(!livreRepository.existsById(id)){
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Livre introuvable");
    }

    livreRepository.deleteById(id);

}

public LivreResponseDto createLivre(LivreRequestDto dto) {

    Livre livre = livreMapper.toEntity(dto);

    Livre savedLivre = livreRepository.save(livre);

    return livreMapper.toDto(savedLivre);

    
}
}