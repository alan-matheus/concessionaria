package com.concessionaria.concessionaria.controler;


import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.concessionaria.concessionaria.model.Categoria;
import com.concessionaria.concessionaria.repository.RepositoryCategoria;

@Controller
public class ControllerCategorias {

    @Autowired
    RepositoryCategoria repository;

    @RequestMapping (value = "adm/categorias/cadastro", method = RequestMethod.GET)
    public String save(){
        return "adm/categorias/cadastro";
    }

    @RequestMapping (value = "adm/categorias/cadastro", method = RequestMethod.POST)
    public String saveCategoria(@Valid Categoria categoria, BindingResult result, RedirectAttributes atributos){
        if(result.hasErrors()){
            atributos.addFlashAttribute("mensagem","verifique os campos obrigatórios");
            return ("redirect:/save");
        }
        repository.save(categoria);
        return("redirect:/adm/categorias/lista");
    }

    @RequestMapping (value = "/adm/categorias/lista", method = RequestMethod.GET)
    public ModelAndView getCategorias(){
        ModelAndView mv = new ModelAndView("adm/categorias/lista");
        List<Categoria> categoria = repository.findAll();

        mv.addObject("categoria", categoria);

        return mv;

    }
    
    @RequestMapping (value = "/adm/categorias/remover/{id}", method = RequestMethod.GET)
    public String deleteById(@PathVariable("id") int id, RedirectAttributes atributos){
        repository.deleteById(id);
        atributos.addFlashAttribute("mensagem", "deletado com sucesso");

        return "redirect:/adm/categorias/lista";
    }

    @RequestMapping (value = "/adm/categorias/update/{id}", method = RequestMethod.GET)
    public ModelAndView update(@PathVariable("id") int id){
        ModelAndView mv = new ModelAndView("adm/categorias/update");
        Optional<Categoria> categorias = repository.findById(id);
        mv.addObject("id_categoria", categorias.get().getId_categoria());
        mv.addObject("nome", categorias.get().getNome());

        return mv;
    }
    
    @RequestMapping (value = "adm/categorias/update/{id}", method = RequestMethod.POST)
    public String updateCategoria(Categoria categoria){
        Categoria agora = repository.findById(categoria.getId_categoria()).orElse(null);

        agora.setNome(categoria.getNome());

        repository.save(agora);

        return "redirect:/adm/categorias/lista";
    }




}





