package com.concessionaria.concessionaria.controler;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.concessionaria.concessionaria.model.Veiculos;
import com.concessionaria.concessionaria.repository.RepositoryVeiculos;

@Controller
public class ControllerVeiculos {


    
    @Autowired
    RepositoryVeiculos repository;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String inicio() {
        return "index";
    }

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String inicioadm() {
        return "home";
    }

    
    @RequestMapping(value="adm/veiculos/lista", method=RequestMethod.GET)
	public ModelAndView getVeiculos() {
		ModelAndView mv = new ModelAndView("adm/veiculos/lista");
		List<Veiculos> veiculos = repository.findAll();
		
		mv.addObject("veiculos", veiculos);
		return mv;
	}
    
    @RequestMapping(value="*/adm/veiculos/{id}", method=RequestMethod.GET)
	public ModelAndView getVeiculo(@PathVariable("id") long id) {
		ModelAndView mv = new ModelAndView("listarVeiculo");
		Optional<Veiculos> veiculos = repository.findById(id);
		mv.addObject("id", veiculos.get().getId_veiculos());
        mv.addObject("placa", veiculos.get().getPlaca());
        mv.addObject("cor", veiculos.get().getCor());
		mv.addObject("modelo", veiculos.get().getModelo());
        mv.addObject("marca", veiculos.get().getMarca());
        mv.addObject("ano", veiculos.get().getAno());
        mv.addObject("id_categoria", veiculos.get().getId_categoria());
        mv.addObject("valor", veiculos.get().getValor());

        return mv;
	}

    @RequestMapping(value = "adm/veiculos/cadastro", method = RequestMethod.GET)
    public String save(){
        return "adm/veiculos/cadastro";
    }

    @RequestMapping(value = "/adm/veiculos/cadastro", method = RequestMethod.POST)
    public String savePost (@Valid Veiculos veiculo, BindingResult result, RedirectAttributes atributos, @RequestParam("file") MultipartFile imagem){

        if(result.hasErrors()){
            atributos.addFlashAttribute("mensagem", "Verifique os campos obrigatórios");
            return "redirect:/save";
        }

        try {
            if(!imagem.isEmpty()){
                byte[] bytes = imagem.getBytes();
                String nomeImagem = LocalDate.now()+ imagem.getOriginalFilename();
                Path caminho = Paths.get("./src/main/resources/static/img/"+ nomeImagem);
                Files.write(caminho, bytes);
                veiculo.setImagem(nomeImagem);
            }
        } catch (IOException e) {
            System.out.println("erro na imagem, tente novamente");
        }

        repository.save(veiculo);
        return "redirect:/adm/veiculos/lista";

        
    }

    @RequestMapping (value = "/adm/veiculos/remover/{id}", method = RequestMethod.GET)
    public String deleteById(@PathVariable("id") long id, RedirectAttributes atributos){
        repository.deleteById(id);
        atributos.addFlashAttribute("mensagem", "deletado com sucesso");
        return "redirect:/adm/veiculos/lista";
    }

    @RequestMapping (value="/adm/veiculos/update/{id}", method=RequestMethod.GET)
    public ModelAndView update(@PathVariable("id") long id){
        ModelAndView mv = new ModelAndView("/adm/veiculos/update");
        Optional<Veiculos> veiculos = repository.findById(id);
        mv.addObject("id_veiculos", veiculos.get().getId_veiculos());
        mv.addObject("placa", veiculos.get().getPlaca());
        mv.addObject("cor", veiculos.get().getCor());
        mv.addObject("marca", veiculos.get().getMarca());
        mv.addObject("modelo", veiculos.get().getModelo());
        mv.addObject("ano", veiculos.get().getAno());
        mv.addObject("id_categoria", veiculos.get().getId_categoria());
        mv.addObject("valor", veiculos.get().getValor());
        mv.addObject("imagem", veiculos.get().getImagem());

        return mv;
    }

    @RequestMapping (value="/adm/veiculos/update/{id}", method=RequestMethod.POST)
    public String updatePost(Veiculos veiculos, @RequestParam("file") MultipartFile imagem){
        
        Veiculos carregando = repository.findById(veiculos.getId_veiculos()).orElse(null);
        
        carregando.setPlaca(veiculos.getPlaca());
        carregando.setCor(veiculos.getCor());
        carregando.setMarca(veiculos.getMarca());
        carregando.setModelo(veiculos.getModelo());
        carregando.setAno(veiculos.getAno());
        carregando.setId_categoria(veiculos.getId_categoria());
        carregando.setValor(veiculos.getValor());

        try {
            
            if(!imagem.isEmpty()){
                byte[] bytes = imagem.getBytes();
                String nomeImagem = LocalDate.now()+ imagem.getOriginalFilename();
                Path caminho = Paths.get("./src/main/resources/static/img/"+ nomeImagem);
                Files.write(caminho, bytes);
                carregando.setImagem(nomeImagem);
            }
        } catch (IOException e) {
            System.out.println("erro na imagem, tente novamente");
        }

        repository.save(carregando); 
        
        
        return "redirect:/adm/veiculos/lista";

    }
}

