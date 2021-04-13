package br.com.caelum.ingresso.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.com.caelum.ingresso.model.Sala;
import br.com.caelum.ingresso.model.Sessao;
import br.com.caelum.ingresso.model.Filme;

@Repository
public class SessaoDao {
    
    @PersistenceContext
    private EntityManager manager;
    
    public void save(Sessao sessao){
        manager.persist(sessao);
    }
    
    public List<Sessao> buscaSessoesDaSala(Sala sala) {
        
        String jpql = "select s from Sessao s where s.sala = :sala";
        
        return manager.createQuery(jpql, Sessao.class)
            .setParameter("sala", sala)
            .getResultList();
    }
    
    public List<Sessao> buscaSessoesDoFilme(Filme filme) {
        return manager.createQuery("select s from Sessao s where s.filme = :filme", Sessao.class)
        .setParameter("filme", filme)
        .getResultList();
    }
    
}