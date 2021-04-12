package br.com.caelum.ingresso.model.form;

import java.time.LocalTime;

import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import br.com.caelum.ingresso.dao.SalaDao;
import br.com.caelum.ingresso.dao.FilmeDao;
import br.com.caelum.ingresso.model.Sessao;
import br.com.caelum.ingresso.model.Filme;
import br.com.caelum.ingresso.model.Sala;


public class SessaoForm { //DTO
    
    @NotNull
    private Integer salaId;
    @NotNull
    private Integer filmeId;
    @NotNull
    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime horario;
    
    public Sessao toSessao(SalaDao salaDao, FilmeDao filmeDao){
        Filme filme = filmeDao.findOne(filmeId);
        Sala sala = salaDao.findOne(salaId);
        Sessao sessao = new Sessao(this.horario, filme, sala);
        return sessao;
    }
    
    public Integer getSalaId() {
        return this.salaId;
    }
    
    public void setSalaId(Integer salaId) {
        this.salaId = salaId;
    }
    
    public Integer getFilmeId() {
        return this.filmeId;
    }
    
    public void setFilmeId(Integer filmeId) {
        this.filmeId = filmeId;
    }
    
    public LocalTime getHorario() {
        return this.horario;
    }
    
    public void setHorario(LocalTime horario) {
        this.horario = horario;
    }
}