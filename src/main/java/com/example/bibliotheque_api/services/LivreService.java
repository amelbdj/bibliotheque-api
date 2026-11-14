package com.example.bibliotheque_api.services;
import com.example.bibliotheque_api.models.Livre;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.bibliotheque_api.repository.LivreRepository;


@Service
public class LivreService {

    private final LivreRepository livreRepository;

    public LivreService(LivreRepository livreRepository) {
        this.livreRepository = livreRepository;
    }

    public List<Livre> getAllLivres() {
        return livreRepository.findAll();
    }

 public Livre getLivreById(Long id) {
    return livreRepository.findById(id)
        .orElseThrow(() -> new ResponseStatusException(
            HttpStatus.NOT_FOUND,
            "Livre introuvable"
        ));
}

public Livre createLivre(Livre livre){
    return livreRepository.save(livre);
}

public Livre updateLivre(Long id, Livre livre) {

    Livre existingLivre = getLivreById(id);

    existingLivre.setAuthor(livre.getAuthor());
    existingLivre.setPrice(livre.getPrice());
    existingLivre.setTitle(livre.getTitle());

    return livreRepository.save(existingLivre);

}

public void deleteLivre(Long id) {

    if(!livreRepository.existsById(id)){
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Livre introuvable");
    }

    livreRepository.deleteById(id);

}

}
