package com.concessionaria.concessionaria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.concessionaria.concessionaria.model.Veiculos;

@Repository
public interface RepositoryVeiculos extends JpaRepository<Veiculos, Long> {
    
}
