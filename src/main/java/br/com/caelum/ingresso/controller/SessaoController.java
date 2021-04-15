package br.com.caelum.ingresso.controller;

import java.util.List;

import javax.validation.Valid;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.*;

import br.com.caelum.ingresso.dao.FilmeDao;
import br.com.caelum.ingresso.dao.SalaDao;
import br.com.caelum.ingresso.dao.SessaoDao;
import br.com.caelum.ingresso.model.Filme;
import br.com.caelum.ingresso.model.Sala;
import br.com.caelum.ingresso.model.Sessao;
import br.com.caelum.ingresso.model.ImagemCapa;
import br.com.caelum.ingresso.model.TipoDeIngresso;
import br.com.caelum.ingresso.model.form.SessaoForm;
import br.com.caelum.ingresso.validacao.GerenciadorDeSessao;
import br.com.caelum.ingresso.rest.OmdbClient;

@Controller
public class SessaoController {
    
    @Autowired
    private SalaDao salaDao;
    
    @Autowired
    private FilmeDao filmeDao;
    
    @Autowired
    private SessaoDao sessaoDao;
    
    @Autowired
    private OmdbClient client;
    
    @GetMapping("/admin/sessao")
    public ModelAndView form(@RequestParam("salaId") Integer salaId) {
        
        ModelAndView modelAndView = new ModelAndView("sessao/sessao");
        
        modelAndView.addObject("sala", salaDao.findOne(salaId));
        modelAndView.addObject("filmes", filmeDao.findAll());

        return modelAndView;
    }
    
    @Transactional
    @PostMapping("/admin/sessao")
    public ModelAndView salvaSessao(@Valid SessaoForm sessaoForm, BindingResult bidingResult){
        if (bidingResult.hasErrors()) {
            return form(sessaoForm.getSalaId());
        }   
        Sala sala = salaDao.findOne(sessaoForm.getSalaId());
        Filme filme = filmeDao.findOne(sessaoForm.getFilmeId());
        
        Sessao novaSessao = new Sessao(sessaoForm.getHorario(), filme, sala);
        
        List<Sessao> sessoesDaSala = sessaoDao.buscaSessoesDaSala(novaSessao.getSala());
        
        GerenciadorDeSessao gerenciador = new GerenciadorDeSessao(sessoesDaSala);
        
        if (gerenciador.cabe(novaSessao)) {
            
            sessaoDao.save(novaSessao);
            
            return new ModelAndView("redirect:/admin/sala/" + sala.getId() + "/sessoes");
        }
        return form(sala.getId());
    }

    @GetMapping("/sessao/{id}/lugares")
    public ModelAndView lugaresNaSessao(@PathVariable("id") Integer sessaoId){
        ModelAndView modelAndView = new ModelAndView("sessao/lugares");
        Sessao sessao = sessaoDao.findOne(sessaoId);
        Optional<ImagemCapa> imagemCapa = client.request(sessao.getFilme(), ImagemCapa.class);
        modelAndView.addObject("sessao", sessao);
        modelAndView.addObject("imagemCapa", imagemCapa.orElse(new ImagemCapa()));
        modelAndView.addObject("tiposDeIngressos", TipoDeIngresso.values());
        return modelAndView;
    }

}