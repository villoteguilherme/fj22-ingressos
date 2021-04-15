package br.com.caelum.ingresso.model;

import java.time.LocalTime;

import java.util.Map;
import java.util.List;
import java.util.Set;
import java.util.HashSet;

import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;
import java.math.RoundingMode;

@Entity
public class Sessao {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer  id;
    private LocalTime horario;
    
    @ManyToOne
    private Sala sala;
    
    @ManyToOne
    private Filme filme;
    
    private BigDecimal preco;
    
    @OneToMany(mappedBy = "sessao", fetch = FetchType.EAGER)
    private Set<Ingresso> ingressos = new HashSet<>();
    
    public Sessao() {}
    
    public Sessao(LocalTime horario, Filme filme, Sala sala) {
        this.horario = horario;
        this.filme = filme;
        this.sala = sala;
        this.preco = sala.getPreco().add(filme.getPreco());
    }
    
    public BigDecimal getPreco() {
        return preco.setScale(2, RoundingMode.HALF_UP);
    }
    
    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }
    
    public Integer getId(){
        return this.id;
    }
    
    public void setId(Integer id){
        this.id = id;
    }
    
    public LocalTime getHorario(){
        return this.horario;
    }
    
    public void setHorario(LocalTime horario){
        this.horario=horario;
    }
    
    public Sala getSala(){
        return this.sala;
    }
    
    public void setSala(Sala sala){
        this.sala = sala;
    }
    
    public Filme getFilme(){
        return this.filme;
    }
    
    public void setFilme(Filme filme){
        this.filme = filme;
    }
    
    public Map<String, List<Lugar>> getMapaDeLugares(){
        return sala.getMapaDeLugares();
    }
    
    public Set<Ingresso> getIngressos(){
        return ingressos;
    }
    
    public void setIngressos(Set<Ingresso> ingressos){
        this.ingressos = ingressos;
    }
    
    public boolean isDisponivel(Lugar lugarSelecionado) {
        return ingressos.stream().map(Ingresso::getLugar).noneMatch(lugar -> lugar.equals(lugarSelecionado));
    }
}