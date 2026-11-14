package com.example.bibliotheque_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.bibliotheque_api.models.Livre;

public interface LivreRepository extends JpaRepository<Livre, Long> {
    
}
