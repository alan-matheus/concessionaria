package com.concessionaria.concessionaria.services;

import java.util.List;
import com.concessionaria.concessionaria.model.Categoria;

public interface ServicesCategoria {
    List<Categoria> findAll();
    Categoria findById(int id);
    Categoria save(Categoria categoria);
    Categoria deleteById(int id);
}
