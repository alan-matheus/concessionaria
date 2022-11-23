package com.concessionaria.concessionaria.services;

import java.util.List;
import com.concessionaria.concessionaria.model.Categoria;

public interface ServicesCategoria {
    List<Categoria> findAll();
    Categoria findById(long id);
    Categoria save(Categoria categoria);
    Categoria deleteById(long id);
}
