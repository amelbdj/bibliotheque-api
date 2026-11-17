package com.example.bibliotheque_api.controllers;
import com.example.bibliotheque_api.dto.LivreRequestDto;
import com.example.bibliotheque_api.dto.LivreResponseDto;
import com.example.bibliotheque_api.models.Livre;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.bibliotheque_api.services.LivreService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
@RequestMapping("/livres")


public class LivreController {

    private final LivreService livreService;

    public LivreController(LivreService livreService) {
        this.livreService = livreService;
    }

    @GetMapping
    public List<LivreResponseDto> getAllLivres() {
        return livreService.getAllLivresResponse();
    }

    @GetMapping("/{id}")
    public LivreResponseDto getLivreResponseById(@PathVariable Long id) {
       return livreService.getLivreResponseById(id);
       
    }

    

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public LivreResponseDto createLivre(@Valid @RequestBody LivreRequestDto livre) {
        
        return livreService.createLivre(livre);
    }
    
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteLivre(@PathVariable Long id){
        livreService.deleteLivre(id);
    }

    @PutMapping("/{id}")
    public LivreResponseDto updateLivre(@PathVariable Long id, @Valid @RequestBody LivreRequestDto livre) {        
        return livreService.updateLivre(id,livre);
    }
    

}
