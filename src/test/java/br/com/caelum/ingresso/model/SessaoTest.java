package br.com.caelum.ingresso.model;

import java.time.LocalTime;

import br.com.caelum.ingresso.model.Filme;
import br.com.caelum.ingresso.model.Sala;
import br.com.caelum.ingresso.model.Sessao;
import java.time.Duration;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class SessaoTest {
    @Test
    public void oPrecoDaSessaoDeveSerIgualASomaDoPrecoDaSalaMaisOPrecoDoFilme() {
        Sala sala = new Sala("Eldorado - IMax", new BigDecimal("22.5"));
        Filme filme = new Filme("Rogue One", Duration.ofMinutes(120),
        "SCI-FI", new BigDecimal("12.0"));
        BigDecimal somaDosPrecosDaSalaEFilme = sala.getPreco().add(filme.getPreco());
        Sessao sessao = new Sessao(LocalTime.parse("10:00:00"), filme, sala);
        Assert.assertEquals( somaDosPrecosDaSalaEFilme, sessao.getPreco() );
    }
}