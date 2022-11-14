package com.concessionaria.concessionaria.services;

import java.util.List;

import com.concessionaria.concessionaria.model.Veiculos;

public interface ServicesVeiculos {
    List<Veiculos> findaAll();
    Veiculos findById(long id);
    Veiculos save(Veiculos veiculos);
    Veiculos deleteById(long id);

    
    
}
