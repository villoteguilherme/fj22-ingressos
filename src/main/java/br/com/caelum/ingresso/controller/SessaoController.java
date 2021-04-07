package br.com.caelum.ingresso.controller;

import br.com.caelum.ingresso.dao.FilmeDao;
import br.com.caelum.ingresso.model.Filme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Optional;

@Controller
public class SessaoController {
    
    @Autowired
    private SalaDao salaDao;
    
    @Autowired
    private FilmeDao filmeDao;
    
    @GetMapping("/admin/sessao")
    public ModelAndView form(@RequestParam("salaId") Integer salaId) {
        
        ModelAndView modelAndView = new ModelAndView("sessao/sessao");
        
        modelAndView.addObject("sala", salaDao.findOne(salaId));
        modelAndView.addObject("filmes", filmeDao.findAll());

        return modelAndView;
    }
    
    
}