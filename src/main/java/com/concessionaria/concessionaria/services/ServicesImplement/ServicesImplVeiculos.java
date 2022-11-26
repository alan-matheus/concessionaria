package com.concessionaria.concessionaria.services.ServicesImplement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.concessionaria.concessionaria.model.Veiculos;
import com.concessionaria.concessionaria.repository.RepositoryVeiculos;
import com.concessionaria.concessionaria.services.ServicesVeiculos;

public class ServicesImplVeiculos implements ServicesVeiculos {
    @Autowired
    RepositoryVeiculos repository;

    @Override
    public List<Veiculos> findAll() {
        
        
        return repository.findAll();
    }

    @Override
    public Veiculos findById(long id) {
        
        return repository.findById(id).get();
    }

    @Override
    public Veiculos save(Veiculos veiculos) {
        
        return repository.save(veiculos);
    }

    @Override
    public Veiculos deleteById(long id) {
        
        return deleteById(id);
    }

    @Override
    public List<Veiculos> findVeiculosByModeloLike(String modelo) {
        // TODO Auto-generated method stub
        return repository.findVeiculosByModeloLike(modelo);
    }

    
    
}
