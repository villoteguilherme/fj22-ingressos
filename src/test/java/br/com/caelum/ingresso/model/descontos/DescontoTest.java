package br.com.caelum.ingresso.model.descontos;

import java.time.LocalTime;
import java.time.Duration;

import br.com.caelum.ingresso.model.Filme;
import br.com.caelum.ingresso.model.Sala;
import br.com.caelum.ingresso.model.Sessao;
import br.com.caelum.ingresso.model.Ingresso;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

public class DescontoTest {
    @Test
    public void naoDeveConcederDescontoParaIngressoNormal() {
        Sala sala = new Sala("Eldorado - IMAX", new BigDecimal("20.5"));
        Filme filme = new Filme("Rogue One", Duration.ofMinutes(120),
        "SCI-FI", new BigDecimal("12"));
        Sessao sessao = new Sessao(LocalTime.parse("10:00:00"), filme, sala);
        Ingresso ingresso = new Ingresso(sessao, new SemDesconto());
        BigDecimal precoEsperado = new BigDecimal("32.50");
        Assert.assertEquals(precoEsperado, ingresso.getPreco());
    }
}