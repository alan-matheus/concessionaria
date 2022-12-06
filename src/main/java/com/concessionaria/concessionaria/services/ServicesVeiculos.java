package com.concessionaria.concessionaria.services;

import java.util.List;

import com.concessionaria.concessionaria.model.Veiculos;

public interface ServicesVeiculos {
    List<Veiculos> findAll();
    Veiculos findById(long id);
    Veiculos save(Veiculos veiculos);
    Veiculos deleteById(long id);
    List<Veiculos> findVeiculosByModeloLike(String modelo);
    
    
    
    
}
