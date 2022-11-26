package com.concessionaria.concessionaria.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.concessionaria.concessionaria.model.Veiculos;

@Repository
public interface RepositoryVeiculos extends JpaRepository<Veiculos, Long> {
    
    @Query ("SELECT v from Veiculos v WHERE" + 
    " CONCAT (v.modelo, v.ano) " + 
    " LIKE %?1%" ) 
    List<Veiculos> findVeiculosByModeloLike(String modelo);
}
