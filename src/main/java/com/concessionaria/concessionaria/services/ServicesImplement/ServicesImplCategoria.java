package com.concessionaria.concessionaria.services.ServicesImplement;

import java.util.List;
import com.concessionaria.concessionaria.model.Categoria;
import com.concessionaria.concessionaria.repository.RepositoryCategoria;
import com.concessionaria.concessionaria.services.ServicesCategoria;

public class ServicesImplCategoria implements ServicesCategoria {

    RepositoryCategoria repository;

    @Override
    public List<Categoria> findAll() {
        
        return repository.findAll();
    }

    @Override
    public Categoria findById(int id) {
    
        return repository.findById(id).get();
    }

    @Override
    public Categoria save(Categoria categoria) {
        
        return repository.save(categoria);
    }

    @Override
    public Categoria deleteById(int id) {
        
        return deleteById(id);
    }
    
}
