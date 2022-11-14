package com.concessionaria.concessionaria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.concessionaria.concessionaria.model.Categoria;

@Repository
public interface RepositoryCategoria extends JpaRepository<Categoria, Integer> {
    
}