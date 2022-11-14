package com.concessionaria.concessionaria.controler;


import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
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

    




}





